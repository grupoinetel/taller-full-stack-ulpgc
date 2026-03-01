import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {DetalleTarea} from '../../model/DetalleTarea';
import {NgClass, NgOptimizedImage} from '@angular/common';
import {CATEGORIA_TAREA_COLORES, CATEGORIA_TAREA_LABELS} from '../../model/CategoriaTarea';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES, PRIORIDAD_TAREA_LABELS} from '../../model/PrioridadTarea';
import {Avatar} from '../../../comun/components/avatar/avatar';

declare var bootstrap: any;

@Component({
  selector: 'app-modal-tarea',
  imports: [
    Avatar,
    NgClass
  ],
  templateUrl: './modal-tarea.html',
  styleUrl: './modal-tarea.scss',
})
export class ModalTarea implements AfterViewInit {
  @ViewChild('modalTarea', { static: true })
  modalTarea?: ElementRef<HTMLElement>;

  tarea!: DetalleTarea;
  private bsModal?: any;

  protected readonly CATEGORIA_TAREA_LABELS = CATEGORIA_TAREA_LABELS;
  protected readonly CATEGORIA_TAREA_COLORES = CATEGORIA_TAREA_COLORES;
  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;
  protected readonly PRIORIDAD_TAREA_LABELS = PRIORIDAD_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;

  ngAfterViewInit() {
    this.bsModal = new bootstrap.Modal(this.modalTarea?.nativeElement);
  }

  toggle(tarea: DetalleTarea) {
    this.bsModal?.show();
    this.tarea = tarea;
  }
}
