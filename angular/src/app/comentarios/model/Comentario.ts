import { Usuario } from '../../usuarios/model/Usuario';

export interface Comentario {
  id: number;
  autor: Usuario;
  mensaje: string;
  fecha: string;
}
