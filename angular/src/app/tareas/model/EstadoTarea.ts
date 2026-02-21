export enum EstadoTarea {
  PENDIENTE = 'PENDIENTE',
  EN_PROGRESO = 'EN_PROGRESO',
  EN_PRUEBAS = 'EN_PRUEBAS',
  HECHO = 'HECHO',
}

export const ESTADO_TAREA_LABELS: Record<EstadoTarea, string> = {
  PENDIENTE: 'Pendiente',
  EN_PROGRESO: 'En progreso',
  EN_PRUEBAS: 'En pruebas',
  HECHO: 'Hecho',
};

export const ESTADO_TAREA_COLORES: Record<EstadoTarea, string> = {
  PENDIENTE: 'secondary',
  EN_PROGRESO: 'primary',
  EN_PRUEBAS: 'warning',
  HECHO: 'success'

}
