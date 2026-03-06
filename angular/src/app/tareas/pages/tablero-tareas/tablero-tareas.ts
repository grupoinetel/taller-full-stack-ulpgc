import {Component, OnInit, signal, ViewChild, WritableSignal} from '@angular/core';
import {Usuario} from '../../../usuarios/model/Usuario';
import {DetalleTarea} from '../../model/DetalleTarea';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS, EstadoTarea} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES} from '../../model/PrioridadTarea';
import {ModalTarea} from '../../components/modal-tarea/modal-tarea';
import {FormularioTarea} from '../../model/FormularioTarea';
import {TareaService} from '../../services/tarea-service';
import {UsuarioService} from '../../../usuarios/services/usuario-service';

@Component({
  selector: 'app-tablero-tareas',
  imports: [
    ModalTarea
  ],
  templateUrl: './tablero-tareas.html',
  styleUrl: './tablero-tareas.scss',
})
export class TableroTareasComponent implements OnInit {
  @ViewChild('modalTarea')
  modalTarea?: ModalTarea;

  estados: EstadoTarea[] = Object.values(EstadoTarea);
  tareas: DetalleTarea[] = [];

  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;
  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;

  usuarios: Usuario[] = [];

  tareasAgrupadasPorEstado: WritableSignal<Record<EstadoTarea, DetalleTarea[]>> = signal({
    PENDIENTE: [],
    EN_PROGRESO: [],
    EN_PRUEBAS: [],
    HECHO: [],
  });

  private siguienteId = 1;
  private siguienteNumero = 123001;

  constructor(private _tareaService: TareaService,
              private _usuarioService: UsuarioService) {
  }

  ngOnInit() {
    //TODO: Llamar a la API para obtener las tareas. Solo sería necesario agrupar las tareas por estado.

    this._tareaService.obtenerTodasLasTareas().subscribe((tareas: any[]) => {
      this.tareas = tareas;

      this.siguienteId = Math.max(...this.tareas.map((tarea) => tarea.id)) + 1;
      this.siguienteNumero = Math.max(...this.tareas.map((tarea) => tarea.numero)) + 1;

      this.agruparTareasPorEstado();

    })

    this._usuarioService.obtenerTodosLosUsuarios().subscribe((usuarios: any[]) => {
      this.usuarios = usuarios;
    })
  }

  protected agruparTareasPorEstado(): void {
    let agrupadas: Record<EstadoTarea, DetalleTarea[]> = {
      PENDIENTE: [],
      EN_PROGRESO: [],
      EN_PRUEBAS: [],
      HECHO: [],
    };

    this.tareas.forEach((tarea) => {
      agrupadas[tarea.estado].push(tarea);
    });

    this.tareasAgrupadasPorEstado.set(agrupadas);
  }

  protected crearTarea(): void {
    this.modalTarea?.abrirModal('formulario', undefined);
  }

  protected formatearFechaLimite(fechaLimite?: string): string {
    //TODO: Cambiar esto por un pipe que formatee la fecha y se use directamente en el HTML
    if (!fechaLimite) {
      return 'Sin fecha límite';
    }

    const fecha = new Date(fechaLimite);
    if (Number.isNaN(fecha.getTime())) {
      return fechaLimite;
    }

    return new Intl.DateTimeFormat('es-ES', {
      dateStyle: 'medium',
      timeStyle: 'short',
    }).format(fecha);
  }

  protected abrirModalTarea(idTarea: number): void {
    // TODO: Hacer la llamada a la API para obtener la tarea por ID.
    const tarea = this.tareas.find((t) => t.id === idTarea);
    if (tarea) {
      this.modalTarea?.abrirModal('detalle', tarea);
    }
  }

  protected editarDesdeDetalle(tarea: DetalleTarea): void {
    this.modalTarea?.abrirModal('formulario', tarea);
  }

  protected eliminarTarea(idTarea: number): void {
    this.tareas = this.tareas.filter((tarea) => tarea.id !== idTarea);
    this.agruparTareasPorEstado();
  }

  protected guardarTarea(evento: {id?: number; data: FormularioTarea}): void {
    if (evento.id) {
      this.actualizarTarea(evento.id, evento.data);
    } else {
      this.nuevaTarea(evento.data);
    }

    this.agruparTareasPorEstado();
  }

  private nuevaTarea(data: FormularioTarea): void {
    /* TODO:
        -Hacer la llamada a la API para crear la tarea.
        - Eliminar del cliente la responsabilidad de crear el ID, el número y la fecha de creación
        - Refrescar la vista de Angular para mostrar los nuevos datos
        - Dejaría algo de este código para que en la interfaz los datos se tengan desde que se guarda.
     */

    data.autorId = this.usuarios[0].id;

    this._tareaService.crearTarea(data).subscribe((tarea: any) => {
      this.tareas.push(tarea);
    })
  }

  private actualizarTarea(idTarea: number, data: FormularioTarea): void {
    /* TODO:
         - Hacer la llamada a la API para actualizar la tarea.
         - Dejaría algo del código a continuación para tener el cambio de manera instantánea
     */

    return this._tareaService.actualizarTarea(idTarea, data).subscribe((tarea: any) => {
      return tarea;
    });
  }

  protected agregarComentario(evento: {tareaId: number; mensaje: string}): void {
    /* TODO:
        -Cambiar por la llamada de agregar comentario
        -Se asume que todos los comentarios están hechos por un usuario
        - Quitar de la UI la responsabilidad de generar el ID del comentario

     */
    const autor = this.usuarios[0];
    const mensaje = evento.mensaje.trim();
    if (!mensaje) {
      return;
    }

    this.tareas = this.tareas.map((tarea) => {
      if (tarea.id !== evento.tareaId) {
        return tarea;
      }

      const siguienteComentarioId = tarea.comentarios.length
        ? Math.max(...tarea.comentarios.map((comentario) => comentario.id)) + 1
        : 1;

      return {
        ...tarea,
        comentarios: [
          ...tarea.comentarios,
          {
            id: siguienteComentarioId,
            autor,
            contenido: mensaje,
            fecha: this.formatearFechaHora(new Date()),
          },
        ],
      };
    });

    const tareaActualizada = this.tareas.find((tarea) => tarea.id === evento.tareaId);
    if (tareaActualizada) {
      this.modalTarea?.abrirModal('detalle', tareaActualizada);
    }
  }

  private obtenerAsignados(asignadosIds: number[]): Usuario[] {
    return this.usuarios.filter(usuario => asignadosIds.includes(usuario.id));
  }

  private formatearFechaHora(fecha: Date): string {
    //TODO: Cambiar por el pipe
    const anio = fecha.getFullYear();
    const mes = String(fecha.getMonth() + 1).padStart(2, '0');
    const dia = String(fecha.getDate()).padStart(2, '0');
    const horas = String(fecha.getHours()).padStart(2, '0');
    const minutos = String(fecha.getMinutes()).padStart(2, '0');

    return `${anio}-${mes}-${dia} ${horas}:${minutos}`;
  }
}
