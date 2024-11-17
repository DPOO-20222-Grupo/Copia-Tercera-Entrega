package Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Actividad;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ModificarActividadesLearningPathException;
import exceptions.ModificarEstudianteLearningPathException;
import exceptions.ModificarObjetivosException;
import learningPath.LearningPath;
import seguimientoEstudiantes.SeguimientoLearningPath;
import user.Estudiante;
import user.Profesor;

public class LearningPathTest {

	private LearningPath learningPath;
	private RevisarRecurso actOriginal;
	private Profesor profesor;
	
	
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
		mapaAct.put(act1.getIdActividad(), false);
		
		ArrayList<Actividad> listActividades = new ArrayList<Actividad>();
		
		listActividades.add(act1);
		
		LearningPath nuevoLP = new LearningPath("LP prueba", "Esto es una prueba de LP", objLearningPath, "Medio", prof, listActividades, mapaAct);
		
		this.learningPath = nuevoLP;
		this.actOriginal = act1;
		this.profesor = prof;
		
	}
	
	@Test
	public void idTest() {
		assertEquals("LP prueba - s.munozm234", learningPath.getIdLearnginPath(), "ID creado incorrectamente");
	}
	
	@Test
	
	public void modificarTitulo() {
		
		learningPath.modificarTitulo("Nuevo Titulo");
		
		assertEquals("Nuevo Titulo", learningPath.getTitulo(), "Titulo modificado incorrectamente");
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
		LocalDateTime fechaHoy = LocalDateTime.now();
		assertEquals(fechaHoy.getDayOfMonth(), learningPath.getFechaUltimaModificacion().getDayOfMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getMonth(), learningPath.getFechaUltimaModificacion().getMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getYear(), learningPath.getFechaUltimaModificacion().getYear(), "Fecha de modificacion actualizada incorrectamente");
		
	}
	
	@Test
	
	public void modificarDescripcionTest() {
		
		learningPath.modificarDescripcion("Nueva Descripcion");
		
		assertEquals("Nueva Descripcion", learningPath.getDescripcion(), "Descripcion modificada incorrectamente");
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		
		LocalDateTime fechaHoy = LocalDateTime.now();
		assertEquals(fechaHoy.getDayOfMonth(), learningPath.getFechaUltimaModificacion().getDayOfMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getMonth(), learningPath.getFechaUltimaModificacion().getMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getYear(), learningPath.getFechaUltimaModificacion().getYear(), "Fecha de modificacion actualizada incorrectamente");
		
		
		
		
	}
	
	@Test
	
	public void agregarObjetivoTest() throws ModificarObjetivosException {
		
	
		String nuevoObj = "Nuevo Obj";
		learningPath.agregarObjetivo(nuevoObj);
		
		//Comportamiento esperado
		assertEquals(2, learningPath.getObjetivos().size(), "Lista de objetivos modificada incorrectamente");
		assertEquals("Nuevo Obj", learningPath.getObjetivos().get(1), "Lista de objetivos modificada incorrectamente" );
		assertEquals(1.1, learningPath.getVersion(), "Version actualizada incorrectamente");
		LocalDateTime fechaHoy = LocalDateTime.now();
		assertEquals(fechaHoy.getDayOfMonth(), learningPath.getFechaUltimaModificacion().getDayOfMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getMonth(), learningPath.getFechaUltimaModificacion().getMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getYear(), learningPath.getFechaUltimaModificacion().getYear(), "Fecha de modificacion actualizada incorrectamente");
		
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
		
		LocalDateTime fechaHoy = LocalDateTime.now();
		assertEquals(fechaHoy.getDayOfMonth(), learningPath.getFechaUltimaModificacion().getDayOfMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getMonth(), learningPath.getFechaUltimaModificacion().getMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getYear(), learningPath.getFechaUltimaModificacion().getYear(), "Fecha de modificacion actualizada incorrectamente");
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
		
		LocalDateTime fechaHoy = LocalDateTime.now();
		assertEquals(fechaHoy.getDayOfMonth(), learningPath.getFechaUltimaModificacion().getDayOfMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getMonth(), learningPath.getFechaUltimaModificacion().getMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getYear(), learningPath.getFechaUltimaModificacion().getYear(), "Fecha de modificacion actualizada incorrectamente");
	}
	@Test
	public void actualizarRatingTest() {
		
		assertEquals(0, learningPath.getRating(), "Rating actualizado incorrectamente");
		
		learningPath.actualizarRating(4.8);
		
		assertEquals(4.8, learningPath.getRating(), "Rating actualizado incorrectamente");
		
		learningPath.actualizarRating(5);
		
		assertEquals(2, learningPath.getContadorRatings());
		assertEquals(4.9, learningPath.getRating(), "Rating actualizado incorrectamente");
		LocalDateTime fechaHoy = LocalDateTime.now();
		assertEquals(fechaHoy.getDayOfMonth(), learningPath.getFechaUltimaModificacion().getDayOfMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getMonth(), learningPath.getFechaUltimaModificacion().getMonth(), "Fecha de modificacion actualizada incorrectamente");
		assertEquals(fechaHoy.getYear(), learningPath.getFechaUltimaModificacion().getYear(), "Fecha de modificacion actualizada incorrectamente");
		assertTrue(fechaHoy.isAfter(learningPath.getFechaCreacion()), "Fecha de modificacion actualizada incorrectamente");
	}
	
	@Test
	
	public void agregarActividadTest() throws ModificarActividadesLearningPathException {
		
		
		Date fecha = new Date();
		ArrayList<String> objActividad = new ArrayList<String>();
		objActividad.add("Objetivo Actividad Nueva");
		
		Tarea nuevaActividad = new Tarea("Actividad Prueba 2", "Esto es una prueba", objActividad, "Bajo", 20, fecha, profesor);
		
		assertEquals(20, learningPath.getDuracionMinutos());
		
		learningPath.agregarActividad(nuevaActividad, false);
		
		assertEquals(2, learningPath.getActividades().size(), "Lista de actividades actualizada incorrectamente");
		assertEquals(40, learningPath.getDuracionMinutos(), "Lista de actividades actualizada incorrectamente");
		assertEquals(nuevaActividad, learningPath.getActividades().get(1), "Actividad incluida incorrectamente");
		assertEquals(2, learningPath.getMapaActividadesObligatorias().size(), "Mapa de obligatoriedad actualizado incorrectamente");
		assertFalse(learningPath.getMapaActividadesObligatorias().get(nuevaActividad.getIdActividad()), "Mapa de obligatoriedad actualizado incorrectamente");
		
		assertEquals(1.1, learningPath.getVersion(), "Version Actualizada Incorrectamente");
		
		try {
			
			learningPath.agregarActividad(nuevaActividad, false);
			fail("Debe haber excepcion de agregar una actividad que ya existe");
		}
		
		catch (ModificarActividadesLearningPathException e) {
			
			assertEquals("La actividad 'Actividad Prueba 2' del profesor Santiago (s.munozm234) ya se encuentra en el Learning Path",
					e.getMessage(), "Excepcion manejada incorrectamente");
			
		}
		
	}
	
	@Test
	
	public void eliminarActividadTest() throws ModificarActividadesLearningPathException {
		
		learningPath.eliminarActividad(actOriginal);
		
		assertEquals(0, learningPath.getActividades().size(), "Lista de actividades modificada incorrectamente");
		assertEquals(0, learningPath.getDuracionMinutos(),"Lista de actividades modificada incorrectamente" );
		assertEquals(0, learningPath.getMapaActividadesObligatorias().size(), "Mapa de obligatoriedad actualizado incorrectamente");
		
		try {
			learningPath.eliminarActividad(actOriginal);
			fail("Debe haber excepcion de eliminar una actividad que no existe");
		}
		
		catch (ModificarActividadesLearningPathException e){
			assertEquals("La actividad 'Actividad Prueba' del profesor Santiago (s.munozm234) no se encuentra en el Learning Path",
					e.getMessage(), "Excepcion manejada incorrectamente");
		}
		
	}
	
	@Test
	
	public void modificarActividadObligatoriaTest() {
		
		learningPath.modificarObligatoriedadActividad(actOriginal);
		
		assertTrue(learningPath.getMapaActividadesObligatorias().get(actOriginal.getIdActividad()), "Obligatoriedad actualizada incorrectamente");
		
	}
	
	@Test 
	public void inscribirEstudianteTest() throws ModificarEstudianteLearningPathException {
		
		Estudiante nuevoEstudiante = new Estudiante("c.espinosag", "BibliotecaEconomia", "Cesar");
		
		SeguimientoLearningPath seguimientoEstudiante = new SeguimientoLearningPath(learningPath, nuevoEstudiante);
		
		learningPath.inscribirEstudiante(nuevoEstudiante, seguimientoEstudiante);
		
		assertEquals(1, learningPath.getEstudiantesInscritos().size(), "Mapa de estudiantes inscritos actualizado incorrectamente");
		assertEquals(seguimientoEstudiante, learningPath.getEstudiantesInscritos().get(nuevoEstudiante.getLogin()), "Mapa de estudiantes inscritos actualizado incorrectamente");
		
		
		try {
			learningPath.inscribirEstudiante(nuevoEstudiante, seguimientoEstudiante);
			fail("Debe haber excepcion para inscribir un estudiante ya inscrito");
		}
		catch(ModificarEstudianteLearningPathException e) {
			assertEquals("El estudiante Cesar (c.espinosag) ya se encuentra inscrito en el Learning Path", e.getMessage(), "Excepcion manejada incorrectamente");
			
		}
		
		
	}
	
	
	@Test
	public void eliminarEstudianteTest() throws ModificarEstudianteLearningPathException {
		
		Estudiante nuevoEstudiante = new Estudiante("c.espinosag", "BibliotecaEconomia", "Cesar");
		
		SeguimientoLearningPath seguimientoEstudiante = new SeguimientoLearningPath(learningPath, nuevoEstudiante);
		
		learningPath.inscribirEstudiante(nuevoEstudiante, seguimientoEstudiante);
		
		learningPath.eliminarEstudiante(nuevoEstudiante);
		
		assertEquals(0, learningPath.getEstudiantesInscritos().size(),"Mapa de estudiantes inscritos actualizado incorrectamente" );
		
		try {
			learningPath.eliminarEstudiante(nuevoEstudiante);
			fail("Deberia haber excepcion para eliminar un estudiante que no esta inscrito en el Learning Path");
		}
		catch (ModificarEstudianteLearningPathException e) {
			assertEquals("El estudiante Cesar (c.espinosag) no se encuentra inscrito en el Learning Path", e.getMessage(), "Excepcion manejada incorrectamente");
		}
		
		
	}
	
	
	
	
	
}
   