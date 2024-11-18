package Test;

import actividades.RevisarRecurso;
import seguimientoEstudiantes.SeguimientoRecurso;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.Estudiante;
import user.Profesor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class SeguimientoRecursoTest {

    private RevisarRecurso recurso1;
    private Estudiante estudiante;
    private SeguimientoRecurso seg;

    @BeforeEach
    void setUp() {
    	Date fecha = new Date();
    	Profesor profesor = new Profesor("login", "password", "nombre");
    	List<String> objetivos = new ArrayList<String>();
    	
        recurso1 = new RevisarRecurso("titulo", "descripción", objetivos, "nivel de dificultad", 0,
        		fecha, "tipo de recurso", profesor, "enlace");
        
        estudiante = new Estudiante("user123", "password123", "Juan Pérez");

        seg = new SeguimientoRecurso(recurso1, estudiante);
    }

    @Test
    void testRecurso() {
        assertEquals("tipo de recurso", seg.getRecurso(),
                "El recurso no fue asignado correctamente.");
    }
}
