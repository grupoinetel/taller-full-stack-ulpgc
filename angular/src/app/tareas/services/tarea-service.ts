import { Injectable } from '@angular/core';
import {BaseService} from '../../comun/base-service';
import {FormularioTarea} from '../model/FormularioTarea';

@Injectable({
  providedIn: 'root',
})
export class TareaService extends BaseService {

  protected override URL_SERVICIO: string = 'tareas';

  public obtenerTodasLasTareas() {
    return this.construirPeticionGet('');
  }

  public obtenerTareaPorId(id: number) {
    return this.construirPeticionGet(`/${id}`);
  }

  public crearTarea(data: FormularioTarea) {
    return this.construirPeticionPost('', data);
  }

  public actualizarTarea(id: number, data: FormularioTarea) {
    return this.construirPeticionPut(`${id}`, data);
  }

  public eliminarTarea(id: number) {
    return this.construirPeticionDelete(`/${id}`);
  }

  public obtenerComentariosDeTarea(id: number) {
    return this.construirPeticionGet(`comentarios/${id}`);
  }
}
