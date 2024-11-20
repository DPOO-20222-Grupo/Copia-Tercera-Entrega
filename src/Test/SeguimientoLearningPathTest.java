package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.*;
import learningPath.LearningPath;
import preguntas.PreguntaCerrada;
import seguimientoEstudiantes.SeguimientoLearningPath;
import user.Estudiante;
import user.Profesor;

public class SeguimientoLearningPathTest {

    private Estudiante estudiante;
    private Profesor profesor;
    private LearningPath learningPath;
    private SeguimientoLearningPath seguimiento;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante("estudiante", "password", "nombre");
        profesor = new Profesor("profesor", "password", "nombre");

        List<String> objetivos = new ArrayList<String>();
        List<PreguntaCerrada> pCerradas = new ArrayList<PreguntaCerrada>();
        
        Date fecha = new Date();
        
        Tarea tarea = new Tarea("Tarea", "descripcion", objetivos, "Medio", 30, fecha, profesor);
        Quiz quiz = new Quiz("Quiz", "descripcion", objetivos, "Bajo", 15, fecha, profesor, 7.0, pCerradas);
        RevisarRecurso recurso = new RevisarRecurso("Recurso", "descripcion", objetivos, "Bajo", 20, fecha, "video", profesor, "enlace");

        List<Actividad> actividades = new ArrayList<Actividad>();
        actividades.add(recurso);
        actividades.add(quiz);
        actividades.add(tarea);
        
        HashMap<String, Boolean> mapaObligatoriedad = new HashMap<String, Boolean>();
        
        learningPath = new LearningPath("titulo", "descripcion", objetivos, "Medio", profesor, actividades, mapaObligatoriedad);

        seguimiento = new SeguimientoLearningPath(learningPath, estudiante);
    }

    @Test
    void testConstructor() {
        assertNotNull(seguimiento, "El seguimiento no debería ser nulo.");
        assertEquals(estudiante.getLogin(), seguimiento.getLoginEstudiante(), "El login del estudiante debería coincidir.");
        assertEquals(learningPath.getIdLearnginPath(), seguimiento.getIdLearningPath(), "El ID del LearningPath debería coincidir.");
        assertEquals(3, seguimiento.getMapaSeguimientoActividades().size(), "Debería haber 3 actividades en el seguimiento.");
        assertEquals("nombre", seguimiento.getNombreEstudiante(), "El nombre del estudiante no es el esperado");
    }

    @Test
    void testCalcularTasaExito() {
        seguimiento.getMapaSeguimientoActividades().values().iterator().next().actualizarEstadoCompletado();
        seguimiento.actualizarTasaExito();
        assertEquals(1f/ 3, seguimiento.getTasaExito(), 0.001, "La tasa de éxito debería calcularse correctamente.");
    }

    @Test
    void testActualizarProgreso() {
        seguimiento.actualizarProgreso();
        assertEquals(1.0 / 3, seguimiento.getProgreso(), 0.001, "El progreso debería actualizarse correctamente.");

        seguimiento.actualizarProgreso();
        assertEquals(2.0 / 3, seguimiento.getProgreso(), 0.001, "El progreso debería incrementarse correctamente.");
    }

    @Test
    void testActualizarTiempoTotal() {
        seguimiento.actualizarTiempoTotal(30);
        assertEquals(30, seguimiento.getTotalTiempo(), "El tiempo total debería actualizarse correctamente.");

        seguimiento.actualizarTiempoTotal(15);
        assertEquals(45, seguimiento.getTotalTiempo(), "El tiempo total debería incrementarse correctamente.");
    }

    @Test
    void testCalcularTiempoPromedioPorActividad() {
    	assertEquals(0, seguimiento.calcularTiempoPromedioPorActividad(), "El tiempo promedio debería ser 0.");
    	
        seguimiento.actualizarTiempoTotal(90); 
        seguimiento.actualizarProgreso();
        
        assertEquals(90, seguimiento.calcularTiempoPromedioPorActividad(), "El tiempo promedio por actividad debería ser 90.");

        seguimiento.actualizarProgreso();     
        assertEquals(45, seguimiento.calcularTiempoPromedioPorActividad(), "El tiempo promedio por actividad debería actualizarse correctamente.");
    }

    @Test
    void testEstaEnRiesgo() {
    	assertFalse(seguimiento.estaEnRiesgo(), "El estudiante no debería estar en riesgo");
    	
        seguimiento.getMapaSeguimientoActividades().values().forEach(s -> s.setEstado("No Exitoso"));
        seguimiento.actualizarTasaExito();

        assertTrue(seguimiento.estaEnRiesgo(), "El estudiante debería estar en riesgo por alta tasa de fracaso.");
    }

    @Test
    void testCalcularPorcentajeProgreso() {
        seguimiento.actualizarProgreso(); // Completa una actividad
        assertEquals(33.33, seguimiento.calcularPorcentajeProgreso(), 0.01, "El porcentaje de progreso debería calcularse correctamente.");

        seguimiento.actualizarProgreso(); // Completa otra actividad
        assertEquals(66.67, seguimiento.calcularPorcentajeProgreso(), 0.01, "El porcentaje de progreso debería incrementarse correctamente.");
    }
}

