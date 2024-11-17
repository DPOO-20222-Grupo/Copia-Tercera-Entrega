package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Examen;
import preguntas.PreguntaAbierta;
import seguimientoEstudiantes.SeguimientoExamen;
import user.Estudiante;
import user.Profesor;

class SeguimientoExamenTest {
	private SeguimientoExamen seg;

	@BeforeEach
	void setUp() {
		Estudiante estudiante = new Estudiante("login", "password", "nombre");
		List<String> objetivos = new ArrayList<String>();
		Profesor profesor = new Profesor("login", "password", "nombre");
		List<PreguntaAbierta> preguntas = new ArrayList<PreguntaAbierta>();
		
		Date fecha = new Date();
		
		Examen examen = new Examen("titulo", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, preguntas);
		seg = new SeguimientoExamen(examen, estudiante);
	}
	
	
	@Test
	void testNota() {
		seg.setNota(5f);
		assertEquals(5f, seg.getNota());
		}
	
	@Test
	void testEstado() {
		seg.actualizarEstadoEnviado();
		assertEquals("Enviado", seg.getEstado());
		seg.actualizarEstadoCompletado();
		assertEquals("Completado", seg.getEstado());
		seg.actualizarEstado(true);
		assertEquals("Exitoso", seg.getEstado());
		seg.actualizarEstado(false);
		assertEquals("No exitoso", seg.getEstado());
	}

}
