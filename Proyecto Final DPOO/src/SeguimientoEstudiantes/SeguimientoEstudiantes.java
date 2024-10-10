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
}

