import {Component, Input} from '@angular/core';
import {DetalleTarea} from '../../model/DetalleTarea';

@Component({
  selector: 'app-header-modal-tarea',
  imports: [],
  templateUrl: './header-modal-tarea.html',
  styleUrl: './header-modal-tarea.scss',
})
export class HeaderModalTarea {
  @Input()
  modo: 'detalle' | 'formulario' = 'detalle';

  @Input()
  tarea?: DetalleTarea;
}
