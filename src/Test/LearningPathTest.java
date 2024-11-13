package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Actividad;
import actividades.RevisarRecurso;
import exceptions.ModificarObjetivosException;
import learningPath.LearningPath;
import user.Profesor;

public class LearningPathTest {

	private LearningPath learningPath;
	
	
	@BeforeEach
	
	public void setUp() {
		ArrayList<String> objActividad = new ArrayList<String>();
		objActividad.add("Objetivo 1");
		Date fecha = new Date();
		Profesor prof = new Profesor("s.munozm234", "prueba1", "Santiago");
		RevisarRecurso act1 = new RevisarRecurso("Actividad Prueba", "Esto es una prueba", objActividad, "Bajo", 20, fecha,"pdf", prof, "Enlace Prueba");
		
		ArrayList<String> objLearningPath = new ArrayList<String>();
		objLearningPath.add("Objetivo 1 LP");
		
		HashMap<String, Boolean> mapaAct = new HashMap<String, Boolean>();
		
		ArrayList<Actividad> listActividades = new ArrayList<Actividad>();
		
		listActividades.add(act1);
		
		LearningPath nuevoLP = new LearningPath("LP prueba", "Esto es una prueba de LP", objLearningPath, "Medio", prof, listActividades, mapaAct);
		
		this.learningPath = nuevoLP;
		
	}
	
	@Test
	
	public void modificarTitulo() {
		
		learningPath.modificarTitulo("Nuevo Titulo");
		
		assertEquals("Nuevo Titulo", learningPath.getTitulo(), "Titulo modificado incorrectamente");
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
	}
	
	@Test
	
	public void modificarDescripcionTest() {
		
		learningPath.modificarDescripcion("Nueva Descripcion");
		
		assertEquals("Nueva Descripcion", learningPath.getDescripcion(), "Descripcion modificada incorrectamente");
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
		Date fechaHoy = new Date();
		
		
	}
	
	@Test
	
	public void agregarObjetivoTest() throws ModificarObjetivosException {
		
	
		String nuevoObj = "Nuevo Obj";
		learningPath.agregarObjetivo(nuevoObj);
		
		//Comportamiento esperado
		assertEquals(2, learningPath.getObjetivos().size(), "Lista de objetivos modificada incorrectamente");
		assertEquals("Nuevo Obj", learningPath.getObjetivos().get(1), "Lista de objetivos modificada incorrectamente" );
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
		//Manejo de excepcion
		String objOriginal = "Objetivo 1 LP";
		assertThrows(ModificarObjetivosException.class,() -> learningPath.agregarObjetivo(objOriginal));
		
		try {
			learningPath.agregarObjetivo(objOriginal);
			fail("Debe haber una excepcion de objetivo");
		}
		
		catch (ModificarObjetivosException e) {
			assertEquals("El objetivo 'Objetivo 1 LP' ya está en los objetivos del Learning Path", e.getMessage(), "Mensaje de excepcion incorrecto");
		}
		
	}


	@Test

	public void eliminarObjetivoTest() throws ModificarObjetivosException {
		learningPath.eliminarObjetivo("Objetivo 1 LP");
		
		//Comportamiento normal
		assertEquals(0, learningPath.getObjetivos().size(), "Lista de objetivos modificada incorrectamente");
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
		//Manejo de Excepcion
		
		assertThrows(ModificarObjetivosException.class, () -> learningPath.eliminarObjetivo("Objetivo 1 LP"));
		
		try {
			learningPath.eliminarObjetivo("Objetivo 1 LP");
			fail("Debe haber una excepcion de objetivo");
		}
		
		catch (ModificarObjetivosException e) {
			assertEquals("El objetivo 'Objetivo 1 LP' no está en los objetivos del Learning Path", e.getMessage(), "Mensaje de excepcion incorrecto");
		}
	
	}

	@Test
	
	public void actualizarDificultadTest() {
		
		learningPath.actualizarDificultad("Bajo");
		
		assertEquals("Bajo", learningPath.getNivelDificultad(), "Dificultad actualizada incorrectamente");
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
	}
	
	public void actualizarRatingTest() {
		
		assertEquals(0, learningPath.getRating(), "Rating actualizado incorrectamente");
		
		learningPath.actualizarRating(4.8);
		
		assertEquals(4.8, learningPath.getRating(), "Rating actualizado incorrectamente");
		
		learningPath.actualizarRating(0);
		
		assertEquals(2.4, learningPath.getRating(), "Rating actualizado incorrectamente");
	}
	
	
	
	
	
}
   