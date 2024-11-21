package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Tarea;
import exceptions.ActividadPreviaCiclicoException;
import exceptions.ActividadSeguimientoCiclicoException;
import exceptions.ModificarActividadesPreviasException;
import exceptions.ModificarActividadesSeguimientoException;
import exceptions.ModificarObjetivosException;
import user.Profesor;

class ActividadTest {
	
	private Tarea tarea1;
	private Tarea tarea2;
	private Tarea tarea3;
	private Profesor profesor1;
	private List<String> objetivos;
	private Date fecha1;
	private Date fecha2;
	private Date fecha0;
	
	@BeforeEach
	void setUp() {
		
		profesor1 = new Profesor("c.espinosag", "password", "César Espinosa");
		
		objetivos = new ArrayList<String>();
		objetivos.add("Aprender ASM");
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, 2004);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 2);
		
		fecha1 = calendario.getTime();
		
		calendario.set(Calendar.YEAR, 2003);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 20);
		
		fecha2 = calendario.getTime();
		
		calendario.set(Calendar.YEAR, 2002);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 20);
		
		fecha0 = calendario.getTime();
		
		tarea1 = new Tarea("Tarea1", "Traduzca de c a asm.", objetivos, "Bajo", 120, fecha1, profesor1);
		tarea2 = new Tarea("Tarea2", "Utilice la instrucción goto.", objetivos, "Bajo", 20, fecha0, profesor1);
		tarea3 = new Tarea("Tarea3", "Registros en IA32.", objetivos, "Bajo", 140, fecha2, profesor1);
	}
	
	@Test
	void testTitulo() {
		assertEquals("Tarea1", tarea1.getTitulo());
		
		tarea1.setTitulo("TareaASM");
		assertEquals("TareaASM", tarea1.getTitulo());
	}
	
	@Test
	void testDescripcion() {
		assertEquals("Traduzca de c a asm.", tarea1.getDescripcion());
		
		tarea1.setDescripcion("TareaASM");
		assertEquals("TareaASM", tarea1.getDescripcion());
	}
	
	
	@Test
	void testObjetivos() throws ModificarObjetivosException {
		assertEquals(objetivos, tarea1.getObjetivos());
		
		tarea1.agregarObjetivo("Objetivo 2");
		assertTrue(tarea1.getObjetivos().contains("Objetivo 2" ));
		
		assertThrows(ModificarObjetivosException.class, () -> tarea1.agregarObjetivo("Objetivo 2"));
		
		tarea1.eliminarObjetivo("Objetivo 2");
		assertFalse(tarea1.getObjetivos().contains("Objetivo 2"));
		
		assertThrows(ModificarObjetivosException.class, () -> tarea1.eliminarObjetivo("Objetivo 2"));
		
	}
	
	@Test
	void testDificultad() {
		assertEquals("Bajo", tarea1.getNivelDificultad());
		tarea1.setNivelDificultad("Medio");
		assertEquals("Medio", tarea1.getNivelDificultad());
	}
	
	@Test
	void testDuración() {
		assertEquals(120, tarea1.getDuracionMinutos());
		tarea1.setDuracionMinutos(20);
		assertEquals(20, tarea1.getDuracionMinutos());
	}
	
	@Test
	void testResena() {
		assertEquals(0, tarea1.getResenas().size());		
		tarea1.agregarResena("Genial");
		assertEquals("Genial", tarea1.getResenas().getLast());
	}
	
	@Test
	void testFechaLimite() {
		assertEquals(fecha1, tarea1.getFechaLimite());
		tarea1.setFechaLimite(fecha2);
		assertEquals(fecha2, tarea1.getFechaLimite());
	}
	
	@Test
	void testRating() {
		assertEquals(0.0, tarea1.getRating());
		tarea1.actualizarRating(4.0);
		assertEquals(4.0, tarea1.getRating());
		tarea1.actualizarRating(5.0);
		assertEquals(4.5, tarea1.getRating());
	}
	
	@Test
	void testActividades() throws ModificarActividadesPreviasException, ModificarActividadesSeguimientoException, ActividadPreviaCiclicoException, ActividadSeguimientoCiclicoException {
		tarea1.agregarActividadPrevia(tarea3);
		assertEquals(tarea3, tarea1.getActividadesPrevias().getLast());
		assertThrows(ModificarActividadesPreviasException.class, () -> tarea1.agregarActividadPrevia(tarea3));
		
		tarea1.eliminarActividadPrevia(tarea3);
		assertEquals(0, tarea1.getActividadesPrevias().size());
		assertThrows(ModificarActividadesPreviasException.class, () -> tarea1.eliminarActividadPrevia(tarea3));
		
		tarea1.agregarActividadSeguimiento(tarea2);
		assertEquals(tarea2, tarea1.getActividadesSeguimiento().getLast());
		assertThrows(ModificarActividadesSeguimientoException.class, () -> tarea1.agregarActividadSeguimiento(tarea2));

		tarea1.eliminarActividadSeguimiento(tarea2);
		assertEquals(0, tarea1.getActividadesSeguimiento().size());
		assertThrows(ModificarActividadesSeguimientoException.class, () -> tarea1.eliminarActividadSeguimiento(tarea2));
		
		
	}

	
}
