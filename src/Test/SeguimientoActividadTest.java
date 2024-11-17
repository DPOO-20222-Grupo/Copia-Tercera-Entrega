package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Tarea;
import seguimientoEstudiantes.SeguimientoTarea;
import user.Estudiante;
import user.Profesor;

class SeguimientoActividadTest {
	
	private SeguimientoTarea seg;
	private Tarea tarea;
	private Estudiante estudiante;
	
	@BeforeEach
	void setUp() {

		estudiante = new Estudiante("login", "password", "nombre");
		
		Profesor profesor = new Profesor("login", "password", "nombre");
		
		List<String> objetivos = new ArrayList<String>();
		objetivos.add("Objetivo 1");
		
		Date fecha = new Date();
		
		tarea = new Tarea("Tarea1", "Descripción", objetivos, "Nivel de Dificultad", 0, fecha, profesor);	
		
		seg = new SeguimientoTarea(tarea, estudiante);
		
	}
	
	@Test
	void testEstado() {
		assertEquals("Incompleta", seg.getEstado());
		seg.actualizarEstadoCompletado();
		assertEquals("Completada", seg.getEstado());
	}
	
	@Test
	void testGetActividadSeguimiento() {
		assertEquals(tarea, seg.getActividadSeguimiento());
	}
	
	@Test
	void testGetEstudianteSeguimiento() {
		assertEquals(estudiante, seg.getEstudianteSeguimiento());
	}
	
	@Test
	void testTiempoTotal() {
		assertEquals(0, seg.getTiempoTotal());
		seg.setTiempoTotal(1);
		assertEquals(1, seg.getTiempoTotal());
	}
}
