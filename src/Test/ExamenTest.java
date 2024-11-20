package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import actividades.Examen;
import exceptions.ModificarPreguntasAbiertasException;
import preguntas.PreguntaAbierta;
import user.Profesor;

class ExamenTest {

    private Examen examen;
    private PreguntaAbierta pregunta1;
    private PreguntaAbierta pregunta2;
    private PreguntaAbierta pregunta3;
    private Profesor profesor;

    @BeforeEach
    void setUp() {

        pregunta1 = new PreguntaAbierta("enunciado", "titulo");
        pregunta2 = new PreguntaAbierta("enunciado2", "titulo2");
        pregunta3 = new PreguntaAbierta("enunciado3", "titulo3");

        profesor = new Profesor("login", "password", "nombre");

        List<PreguntaAbierta> preguntas = new ArrayList<PreguntaAbierta>();
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);
        List<String> objetivos = new ArrayList<String>();
        examen = new Examen("titulo", "descripción", objetivos, "Medio", 60, new Date(), profesor, preguntas);
    }

    @Test
    void testConstructor() {
        assertEquals("titulo", examen.getTitulo(), "El título no es el esperado.");
        assertEquals("descripción", examen.getDescripcion(), "La descripción no es la esperada.");
        assertEquals(2, examen.getNumPreguntas(), "El número inicial de preguntas no es el esperado.");
        assertEquals("Examen", examen.getTipoActividad(), "El tipo de actividad no es el esperado.");
    }

    @Test
    void testGetPreguntas() {
        List<PreguntaAbierta> preguntas = examen.getPreguntas();
        assertEquals(2, preguntas.size(), "El número de preguntas inicial no es correcto.");
        assertTrue(preguntas.contains(pregunta1), "El examen debería contener pregunta1.");
        assertTrue(preguntas.contains(pregunta2), "El examen debería contener pregunta2.");
    }

    @Test
    void testAgregarPregunta() throws ModificarPreguntasAbiertasException {
        examen.agregarPregunta(pregunta3);
        assertEquals(3, examen.getPreguntas().size(), "El número de preguntas no se actualizó correctamente.");
        assertTrue(examen.getPreguntas().contains(pregunta3), "La pregunta3 no fue agregada correctamente.");
    }

    @Test
    void testAgregarPreguntaDuplicada() {
        assertThrows(ModificarPreguntasAbiertasException.class, () -> examen.agregarPregunta(pregunta1),
                "Se esperaba una excepción al intentar agregar una pregunta ya existente.");
    }

    @Test
    void testEliminarPregunta() throws ModificarPreguntasAbiertasException {
        examen.eliminarPregunta(pregunta2);
        assertEquals(1, examen.getPreguntas().size(), "El número de preguntas no se actualizó correctamente tras la eliminación.");
        assertFalse(examen.getPreguntas().contains(pregunta2), "La pregunta1 no fue eliminada correctamente.");
    }

    @Test
    void testEliminarPreguntaNoExistente() {
        assertThrows(ModificarPreguntasAbiertasException.class, () -> examen.eliminarPregunta(pregunta3),
                "Se esperaba una excepción al intentar eliminar una pregunta inexistente.");
    }
}

