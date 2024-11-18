package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Tarea;
import user.Estudiante;
import user.Profesor;
import seguimientoEstudiantes.SeguimientoTarea;

class SeguimientoTareaTest {

    private Tarea tarea;
    private Estudiante estudiante;
    private SeguimientoTarea seguimientoTarea;

    @BeforeEach
    void setUp() {
    	List<String> objetivos = new ArrayList<String>();
    	Date fecha = new Date();
    	Profesor profesor = new Profesor("login", "password", "nombre");
        tarea = new Tarea("tarea1", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor);
        estudiante = new Estudiante("login", "password", "nombre");
        seguimientoTarea = new SeguimientoTarea(tarea, estudiante);
    }

    @Test
    void testSetAndGetMetodoEnvio() {
        seguimientoTarea.setMetodoEnvio("Correo Electrónico");
        assertEquals("Correo Electrónico", seguimientoTarea.getMetodoEnvio(), "El método de envío no se actualizó correctamente.");
    }

    @Test
    void testActualizarEstadoEnviado() {
        seguimientoTarea.actualizarEstadoEnviado();
        assertEquals("Enviado", seguimientoTarea.getEstado(), "El estado no se actualizó correctamente a 'Enviado'.");
    }

    @Test
    void testActualizarEstadoFinalExitoso() {
        seguimientoTarea.actualizarEstadoFinal(true);
        assertEquals("Exitoso", seguimientoTarea.getEstado(), "El estado no se actualizó correctamente a 'Exitoso'.");
    }

    @Test
    void testActualizarEstadoFinalNoExitoso() {
        seguimientoTarea.actualizarEstadoFinal(false);
        assertEquals("No exitoso", seguimientoTarea.getEstado(), "El estado no se actualizó correctamente a 'No exitoso'.");
    }
}

