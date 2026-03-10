import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  signal,
  ViewChild, WritableSignal
} from '@angular/core';
import {DetalleTarea} from '../../model/DetalleTarea';
import {HeaderModalTarea} from '../header-modal-tarea/header-modal-tarea';
import {DetalleModalTarea} from '../detalle-modal-tarea/detalle-modal-tarea';
import {FormularioModalTarea} from '../formulario-modal-tarea/formulario-modal-tarea';
import {Usuario} from '../../../usuarios/model/Usuario';
import {FormularioTarea} from '../../model/FormularioTarea';
import {TareaService} from '../../services/tarea-service';
import {ComentarioService} from '../../../comentarios/services/comentario-service';
import {Comentario} from '../../../comentarios/model/Comentario';

declare var bootstrap: any;

@Component({
  selector: 'app-modal-tarea',
  imports: [
    HeaderModalTarea,
    DetalleModalTarea,
    FormularioModalTarea,
  ],
  templateUrl: './modal-tarea.html',
  styleUrl: './modal-tarea.scss',
})
export class ModalTarea implements AfterViewInit {
  @ViewChild('modalTarea', { static: true })
  modalTarea?: ElementRef<HTMLElement>;
  @ViewChild(FormularioModalTarea)
  formularioModalTarea?: FormularioModalTarea;

  @Input() usuarios: Usuario[] = [];

  @Output() guardarEvent = new EventEmitter<{id?: number; data: FormularioTarea}>();
  @Output() editarEvent = new EventEmitter<number>();
  @Output() eliminarEvent = new EventEmitter<number>();
  @Output() agregarComentarioEvent = new EventEmitter<{tareaId: number; mensaje: string}>();

  tarea?: DetalleTarea;

  comentarios: Comentario[] = [];

  loadingTarea: WritableSignal<boolean> = signal(true);

  modo: 'detalle' | 'formulario' = 'detalle';

  private bsModal?: any;

  constructor(private _tareaService: TareaService,
              private _comentarioService: ComentarioService) {
  }

  ngAfterViewInit() {
    this.bsModal = new bootstrap.Modal(this.modalTarea?.nativeElement);
    this.modalTarea?.nativeElement.addEventListener('hidden.bs.modal', () => {this.cerrarModal();});
  }

  abrirModal(modo: 'detalle' | 'formulario', tareaId: number | null): void {
    this.modo = modo;
    if (tareaId === null) {
      this.bsModal?.show();
      this.loadingTarea.set(false);
    } else {
      this.cargarTarea(tareaId);
    }
  }

  /**
   * CREAMOS METODO PARA OBTENER LOS COMENTARIOS DE UNA TAREA
   */

  editarTarea(): void {
    if (this.tarea) {
      this.editarEvent.emit(this.tarea.id as number);
    }
  }

  eliminarTarea(): void {
    if (!this.tarea) {
      return;
    }

    const confirmar = window.confirm(`¿Seguro que quieres eliminar la tarea #${this.tarea.numero}?`);
    if (!confirmar) {
      return;
    }

    this.eliminarEvent.emit(this.tarea.id as number);
    this.cerrarModal();
  }

  agregarComentario($event: {tareaId: number, mensaje: string}): void {
    this.agregarComentarioEvent.emit($event);
  }

  guardarFormulario(evento: {id?: number; data: FormularioTarea}): void {
    this.guardarEvent.emit(evento);
    this.cerrarModal();
  }

  guardarDesdeFooter(): void {
    this.formularioModalTarea?.guardarFormulario();
  }

  private cerrarModal(): void {
    this.tarea = undefined;
    this.comentarios = [];
    this.bsModal?.hide();
  }

  protected eliminarComentario($event: { id: number }) {
    this._comentarioService.eliminarComentario($event.id).subscribe(() => {
      if (this.tarea) {
        this.cargarTarea(this.tarea.id as number);
        /**
         * OBTENER COMENTARIOS DE NUEVO
         */
      }
    });
  }

  cargarTarea(tareaId: number) {
    this.loadingTarea.set(true);
    this._tareaService.obtenerTareaPorId(tareaId).subscribe((tarea: DetalleTarea) => {
      this.tarea = tarea;
      this.bsModal?.show();
      this.loadingTarea.set(false);
    });
  }
}
