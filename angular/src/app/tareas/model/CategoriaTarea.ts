export enum CategoriaTarea {
  ANALISIS = 'ANALISIS',
  DISENYO = 'DISENYO',
  DESARROLLO = 'DESARROLLO',
  MEJORAS = 'MEJORAS',
  DOCUMENTACION = 'DOCUMENTACION',
  GESTION = 'GESTION',
  PRUEBAS = 'PRUEBAS',
  SOPORTE = 'SOPORTE',
}

export const CATEGORIA_TAREA_LABELS: Record<CategoriaTarea, string> = {
  ANALISIS: 'Análisis',
  DISENYO: 'Diseño',
  DESARROLLO: 'Desarrollo',
  MEJORAS: 'Mejoras',
  DOCUMENTACION: 'Documentación',
  GESTION: 'Gestión',
  PRUEBAS: 'Pruebas',
  SOPORTE: 'Soporte',
};

export const CATEGORIA_TAREA_COLORES: Record<CategoriaTarea, string> = {
  ANALISIS: 'info',
  DISENYO: 'primary',
  DESARROLLO: 'dark',
  MEJORAS: 'warning',
  DOCUMENTACION: 'light',
  GESTION: 'secondary',
  PRUEBAS: 'success',
  SOPORTE: 'danger',
};
