import {Component, OnInit, signal, ViewChild, WritableSignal} from '@angular/core';
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
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-tablero-tareas',
  imports: [
    ModalTarea,
    DatePipe
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

  tareasAgrupadasPorEstado: WritableSignal<Record<EstadoTarea, PreviewTarea[]>> = signal({
    PENDIENTE: [],
    EN_PROGRESO: [],
    EN_PRUEBAS: [],
    HECHO: [],
  });

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
    let agrupadas: Record<EstadoTarea, PreviewTarea[]> = {
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
    this.modalTarea?.abrirModal('formulario', null);
  }

  protected abrirModalTarea(idTarea: number): void {
    if (idTarea) {
      this.modalTarea?.abrirModal('detalle', idTarea);
    }
  }

  protected editarDesdeDetalle(id: number): void {
    this.modalTarea?.abrirModal('formulario', id);
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
      this.obtenerYAgruparTodasLasTareas();
    })
  }

  private actualizarTarea(idTarea: number, data: FormularioTarea): void {
    return this._tareaService.actualizarTarea(idTarea, data).subscribe((tarea: DetalleTarea) => {
      this.obtenerYAgruparTodasLasTareas();
      this.abrirModalTarea(tarea.id);
    });
  }

  protected agregarComentario(evento: {tareaId: number; mensaje: string}): void {
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

  private obtenerYAgruparTodasLasTareas() {
    this._tareaService.obtenerTodasLasTareas().subscribe((tareas: PreviewTarea[]) => {
      this.tareas = tareas;

      this.agruparTareasPorEstado();

    });
  }
}
