import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {DetalleTarea} from '../../model/DetalleTarea';
import {Avatar} from '../../../comun/components/avatar/avatar';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES, PRIORIDAD_TAREA_LABELS} from '../../model/PrioridadTarea';
import {CATEGORIA_TAREA_COLORES, CATEGORIA_TAREA_LABELS} from '../../model/CategoriaTarea';
import {DatePipe, NgClass} from '@angular/common';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-detalle-modal-tarea',
  imports: [
    Avatar,
    NgClass,
    ReactiveFormsModule,
    DatePipe,
  ],
  templateUrl: './detalle-modal-tarea.html',
  styleUrl: './detalle-modal-tarea.scss',
})
export class DetalleModalTarea implements OnChanges {
  @Input()
  tarea?: DetalleTarea;

  /**
   * AGREGAMOS INPUT PARA LOS COMENTARIOS
   */

  @Input()
  dataLoaded: boolean = false;

  @Output() agregarComentarioEvent = new EventEmitter<{tareaId: number; mensaje: string}>();
  @Output() eliminarComentarioEvent = new EventEmitter<number>();

  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;
  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;
  protected readonly PRIORIDAD_TAREA_LABELS = PRIORIDAD_TAREA_LABELS;
  protected readonly CATEGORIA_TAREA_COLORES = CATEGORIA_TAREA_COLORES;
  protected readonly CATEGORIA_TAREA_LABELS = CATEGORIA_TAREA_LABELS;
  mostrandoFormularioComentario = false;
  formularioComentario: any;

  constructor(private readonly formBuilder: FormBuilder) {
    // TODO: Crear el campo mensaje del formulario, como requerido y con longitud máxima de 1000 caracteres
    this.formularioComentario = this.formBuilder.group({

    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['tarea']) {
      this.mostrandoFormularioComentario = false;
      this.resetFormularioComentario();
    }
  }

  mostrarFormularioComentario(): void {
    this.mostrandoFormularioComentario = true;
  }

  enviarComentario(): void {
    if (!this.tarea) {
      return;
    }

    this.formularioComentario.markAllAsTouched();
    if (this.formularioComentario.invalid) {
      return;
    }

    const formRaw = this.formularioComentario.getRawValue();
    this.agregarComentarioEvent.emit({
      tareaId: this.tarea.id,
      mensaje: formRaw.mensaje.trim(),
    });

    this.mostrandoFormularioComentario = false;
    this.resetFormularioComentario();
  }

  private resetFormularioComentario(): void {
    this.formularioComentario.reset({
      mensaje: '',
    });
  }

  protected borrarComentario(id: number) {
    // TODO: Emitir el evento eliminarComentarioEvent al componente padre
  }
}
