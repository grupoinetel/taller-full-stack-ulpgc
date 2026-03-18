import {Component, Input} from '@angular/core';
import {DetalleTarea} from '../../model/DetalleTarea';
import {NumeroTareaPipe} from '../../pipes/numero-tarea-pipe';

@Component({
  selector: 'app-header-modal-tarea',
  imports: [
    NumeroTareaPipe
  ],
  templateUrl: './header-modal-tarea.html',
  styleUrl: './header-modal-tarea.scss',
})
export class HeaderModalTarea {
  @Input()
  modo: 'detalle' | 'formulario' = 'detalle';

  @Input()
  tarea?: DetalleTarea;
}
