import {Component, OnInit, ViewChild} from '@angular/core';
import {Usuario} from '../../../usuarios/model/Usuario';
import {DetalleTarea} from '../../model/DetalleTarea';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS, EstadoTarea} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES} from '../../model/PrioridadTarea';
import {ModalTarea} from '../../components/modal-tarea/modal-tarea';
import {FormularioTarea} from '../../model/FormularioTarea';
import {TareaService} from '../../services/tarea-service';
import {UsuarioService} from '../../../usuarios/services/usuario-service';
import {ComentarioService} from '../../../comentarios/services/comentario-service';
import {PreviewTarea} from '../../model/PreviewTarea';

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
  tareas: PreviewTarea[] = [];

  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;
  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;

  usuarios: Usuario[] = [];

  tareasAgrupadasPorEstado: Record<EstadoTarea, PreviewTarea[]> = {
    PENDIENTE: [],
    EN_PROGRESO: [],
    EN_PRUEBAS: [],
    HECHO: [],
  };

  private siguienteId = 1;
  private siguienteNumero = 123001;

  constructor(private _tareaService: TareaService,
              private _comentarioService: ComentarioService,
              private _usuarioService: UsuarioService) {
  }

  ngOnInit() {
    this.obtenerYAgruparTodasLasTareas();

    this._usuarioService.obtenerTodosLosUsuarios().subscribe((usuarios: any[]) => {
      this.usuarios = usuarios;
    })
  }

  protected agruparTareasPorEstado(): void {
    this.tareasAgrupadasPorEstado = {
      PENDIENTE: [],
      EN_PROGRESO: [],
      EN_PRUEBAS: [],
      HECHO: [],
    };

    this.tareas.forEach((tarea) => {
      this.tareasAgrupadasPorEstado[tarea.estado].push(tarea);
    });
  }

  protected crearTarea(): void {
    this.modalTarea?.abrirModal('formulario', null);
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
    if (idTarea) {
      this.modalTarea?.abrirModal('detalle', idTarea);
    }
  }

  protected editarDesdeDetalle(tarea: DetalleTarea): void {
    this.modalTarea?.abrirModal('formulario', tarea.id);
  }

  protected eliminarTarea(idTarea: number): void {
    this._tareaService.eliminarTarea(idTarea).subscribe(() => {
      this.obtenerYAgruparTodasLasTareas();
    })
  }

  protected guardarTarea(evento: {id?: number; data: FormularioTarea}): void {
    if (evento.id) {
      this.actualizarTarea(evento.id, evento.data);
    } else {
      this.nuevaTarea(evento.data);
    }
  }

  private nuevaTarea(data: FormularioTarea): void {
    data.autorId = this.usuarios[0].id;

    this._tareaService.crearTarea(data).subscribe((tarea: any) => {
      this.tareas.push(tarea);
    })
  }

  private actualizarTarea(idTarea: number, data: FormularioTarea): void {
    return this._tareaService.actualizarTarea(idTarea, data).subscribe((tarea: DetalleTarea) => {
      this.obtenerYAgruparTodasLasTareas();
      this.abrirModalTarea(tarea.id);
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

    this._comentarioService.crearComentario(evento.tareaId, {
      idAutor: autor.id,
      contenido: mensaje,
    }).subscribe(() => {
      this.obtenerYAgruparTodasLasTareas();
      this.abrirModalTarea(evento.tareaId);
    });
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

  private obtenerYAgruparTodasLasTareas() {
    this._tareaService.obtenerTodasLasTareas().subscribe((tareas: PreviewTarea[]) => {
      this.tareas = tareas;

      this.siguienteId = Math.max(...this.tareas.map((tarea) => tarea.id)) + 1;
      this.siguienteNumero = Math.max(...this.tareas.map((tarea) => tarea.numero)) + 1;

      this.agruparTareasPorEstado();

    });
  }
}
