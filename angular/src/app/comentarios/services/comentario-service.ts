import { Injectable } from '@angular/core';
import {BaseService} from '../../comun/base-service';

@Injectable({
  providedIn: 'root',
})
export class ComentarioService extends BaseService {

  protected URL_SERVICIO: string = 'comentarios';

  public crearComentario(idTarea: number,contenido: any) {
    return this.construirPeticionPost(`${idTarea}`, contenido);
  }

  /**
   * CREAMOS METODO DEL SERVICIO PARA OBTENER LOS COMENTARIOS DE UNA TAREA
   * @param idTarea
   */

  public eliminarComentario(id: number) {
    return this.construirPeticionDelete(`${id}`);
  }
}
