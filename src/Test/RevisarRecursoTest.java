package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.RevisarRecurso;
import user.Profesor;

class RevisarRecursoTest {
	private RevisarRecurso recurso;
	
	
	@BeforeEach
	void setUp() {
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, 2000);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 2);
		
		Date fecha = calendario.getTime();
		
		Profesor profesor = new Profesor("c.espinosag", "password", "César Espinosa");
		
		List<String> objetivos = new ArrayList<String>();
		recurso = new RevisarRecurso("Juegos Repetidos", "Introducción a los Juegos Repetidos", objetivos, "Medio", 120,
				fecha, "Video", profesor, "https://www.youtube.com/watch?v=d1TzvhEPoi4&t=346s");
	}

	@Test
	void testTipoRecurso() {
		assertEquals("Video", recurso.getTipoRecurso());
		recurso.setTipoRecurso("Video de Youtube");
		assertEquals("Video de Youtube", recurso.getTipoRecurso());
		
	}
	
	@Test
	void testGetEnlaceRecurso() {
		assertEquals("https://www.youtube.com/watch?v=d1TzvhEPoi4&t=346s", recurso.getEnlaceRecurso());
		recurso.setEnlaceRecurso("d.com");
		assertEquals("d.com", recurso.getEnlaceRecurso());
	}

}
