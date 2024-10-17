package SeguimientoEstudiantes;

public class SeguimientoEstudiantes {
    private float progreso;
    private float totalTiempo;
    private float tasaExito;
    private float tasaFracaso;

    // Constructor
    public SeguimientoEstudiantes(float progreso, float totalTiempo, float tasaExito, float tasaFracaso) {
        this.progreso = progreso;
        this.totalTiempo = totalTiempo;
        this.tasaExito = tasaExito;
        this.tasaFracaso = tasaFracaso;
    }

    public float getProgreso() {
        return progreso;
    }

    public void setProgreso(float progreso) {
        this.progreso = progreso;
    }

    public float getTotalTiempo() {
        return totalTiempo;
    }

    public void setTotalTiempo(float totalTiempo) {
        this.totalTiempo = totalTiempo;
    }

    public float getTasaExito() {
        return tasaExito;
    }

    public void setTasaExito(float tasaExito) {
        this.tasaExito = tasaExito;
    }

    public float getTasaFracaso() {
        return tasaFracaso;
    }

    public void setTasaFracaso(float tasaFracaso) {
        this.tasaFracaso = tasaFracaso;
    }
    
    //Metodos adicionales
    
    // Calcula el porcentaje de progreso
    public float calcularPorcentajeProgreso() {
        return progreso * 100;
    }

    // Calcula la relación de éxito a fracaso
    public float calcularRelacionExitoFracaso() {
        if (tasaFracaso == 0) {
            return tasaExito; // Evita la división por cero si no hay fracasos.
        }
        return tasaExito / tasaFracaso;
    }

    // Calcula el tiempo promedio por actividad 
    public float calcularTiempoPromedioPorActividad() {
        if (progreso == 0) {
            return 0; // Evita la división por cero si no hay progreso registrado.
        }
        return totalTiempo / progreso;
    }

    // Método para verificar si el estudiante está en riesgo 
    public boolean estaEnRiesgo() {
        return tasaFracaso > 0.6;
    }

    // Método para actualizar el progreso y el tiempo total 
    public void actualizarProgreso(float nuevoProgreso, float tiempoAdicional) {
        this.progreso += nuevoProgreso;
        this.totalTiempo += tiempoAdicional;
    }

}

