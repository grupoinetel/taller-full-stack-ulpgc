import {Component, EventEmitter, Input, Output} from '@angular/core';
import {PRIORIDAD_TAREA_COLORES} from '../../model/PrioridadTarea';
import {PreviewTarea} from '../../model/PreviewTarea';
import {CurrencyPipe, DatePipe, UpperCasePipe} from '@angular/common';
import {NumeroTareaPipe} from '../../pipes/numero-tarea-pipe';

@Component({
  selector: 'app-preview-tarea',
  imports: [
    DatePipe,
    CurrencyPipe,
    UpperCasePipe,
    NumeroTareaPipe
  ],
  templateUrl: './preview-tarea.html',
  styleUrl: './preview-tarea.scss',
})
export class TarjetaPreviewTarea {
  @Input()
  tarea!: PreviewTarea;

  @Output()
  eventoClick: EventEmitter<number> = new EventEmitter();

  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;

  protected abrirModalTarea(id: number) {
    this.eventoClick.emit(id);
  }
}
