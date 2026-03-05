import { Injectable } from '@angular/core';
import {BaseService} from '../../comun/base-service';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService extends BaseService {

  protected override URL_SERVICIO: string = 'usuarios';

  public obtenerTodosLosUsuarios() {
    return this.construirPeticionGet('');
  }

}
