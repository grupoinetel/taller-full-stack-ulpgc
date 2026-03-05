import {AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {DetalleTarea} from '../../model/DetalleTarea';
import {HeaderModalTarea} from '../header-modal-tarea/header-modal-tarea';
import {DetalleModalTarea} from '../detalle-modal-tarea/detalle-modal-tarea';
import {FormularioModalTarea} from '../formulario-modal-tarea/formulario-modal-tarea';
import {Usuario} from '../../../usuarios/model/Usuario';
import {FormularioTarea} from '../../model/FormularioTarea';

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
  @Output() editarEvent = new EventEmitter<DetalleTarea>();
  @Output() eliminarEvent = new EventEmitter<number>();
  @Output() agregarComentarioEvent = new EventEmitter<{tareaId: number; mensaje: string}>();

  tarea?: DetalleTarea;
  modo: 'detalle' | 'formulario' = 'detalle';

  private bsModal?: any;

  ngAfterViewInit() {
    this.bsModal = new bootstrap.Modal(this.modalTarea?.nativeElement);
  }

  abrirModal(modo: 'detalle' | 'formulario', tarea?: DetalleTarea): void {
    this.modo = modo;
    this.tarea = tarea;
    this.bsModal?.show();
  }

  editarTarea(): void {
    if (this.tarea) {
      this.editarEvent.emit(this.tarea);
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

    this.eliminarEvent.emit(this.tarea.id);
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
    this.bsModal?.hide();
  }
}
