import {Component, Input} from '@angular/core';
import {Usuario} from '../../../usuarios/model/Usuario';

@Component({
  selector: 'app-avatar',
  imports: [],
  templateUrl: './avatar.html',
  styleUrl: './avatar.scss',
})
export class Avatar {
  @Input()
  usuario?: Usuario;

  getIniciales(nombre?: string): string {
    if (!nombre) {
      return '?';
    }

    return nombre
      .split(' ')
      .filter(Boolean)
      .slice(0, 2)
      .map((parte) => parte[0]?.toUpperCase() ?? '')
      .join('');
  }
}
