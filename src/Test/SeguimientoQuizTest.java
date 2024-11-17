package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Quiz;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Estudiante;
import user.Profesor;
import seguimientoEstudiantes.SeguimientoQuiz;

class SeguimientoQuizTest {

    private Quiz quiz;
    private Estudiante estudiante;
    private PreguntaSeleccionMultiple pregunta1;
    private PreguntaSeleccionMultiple pregunta2;
    private SeguimientoQuiz seguimientoQuiz;

    @BeforeEach
    void setUp() {
        pregunta1 = new PreguntaSeleccionMultiple("pregunta1", "titulo", "opcion1", "opcion2", "opcion3", "opcion4", 1);
        pregunta2 = new PreguntaSeleccionMultiple("pregunta2", "titulo", "opcion1", "opcion2", "opcion3", "opcion4", 2);
        List<PreguntaCerrada> preguntas = new ArrayList<PreguntaCerrada>();
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);
        Date fecha = new Date();
        Profesor profesor = new Profesor("login", "password", "nombre");
        List<String> objetivos = new ArrayList<String>();
        quiz = new Quiz("quiz1", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, 0.6d, preguntas);

        estudiante = new Estudiante("login", "password", "nombre");

        seguimientoQuiz = new SeguimientoQuiz(quiz, estudiante);
    }

    @Test
    void testConstructor() {
        assertEquals(-1, seguimientoQuiz.getNota(), "La nota inicial no es la esperada.");
        assertEquals(2, seguimientoQuiz.getNumPreguntas(), "El número de preguntas no es el esperado.");
        assertEquals(2, seguimientoQuiz.getRespuestas().size(), "El mapa de respuestas no tiene el tamaño esperado.");
        
        seguimientoQuiz.getRespuestas().forEach((PReguntaSeleccionMultiple, Respuesta) ->
                assertEquals(-1, Respuesta, "Todas las respuestas iniciales deberían ser -1."));
    } 	 	

    @Test
    void testAgregarRespuestaPregunta() {
        seguimientoQuiz.agregarRespuestaPregunta(pregunta1, 1);
        seguimientoQuiz.agregarRespuestaPregunta(pregunta2, 2);
        Map<PreguntaCerrada, Integer> respuestas = seguimientoQuiz.getRespuestas();
        assertEquals(1, respuestas.get(pregunta1), "La respuesta para pregunta 1 no fue actualizada correctamente.");
        assertEquals(2, respuestas.get(pregunta2), "La respuesta para pregunta 2 no fue actualizada correctamente.");
    }

    @Test
    void testCalcularNota() {
        seguimientoQuiz.agregarRespuestaPregunta(pregunta1, 1); 
        seguimientoQuiz.agregarRespuestaPregunta(pregunta2, 3);
        assertEquals(0.5d, seguimientoQuiz.calcularNota(), "La nota calculada no es la esperada.");
        
        seguimientoQuiz.agregarRespuestaPregunta(pregunta1, 3); 
        seguimientoQuiz.agregarRespuestaPregunta(pregunta2, 3);
        assertEquals(0d, seguimientoQuiz.calcularNota(), "La nota calculada no es la esperada.");
        
        seguimientoQuiz.agregarRespuestaPregunta(pregunta1, 1); 
        seguimientoQuiz.agregarRespuestaPregunta(pregunta2, 2);
        assertEquals(1d, seguimientoQuiz.calcularNota(), "La nota calculada no es la esperada.");
    }

    @Test
    void testActualizarNotaExitoso() {
        seguimientoQuiz.agregarRespuestaPregunta(pregunta1, 1);
        seguimientoQuiz.agregarRespuestaPregunta(pregunta2, 2);

        seguimientoQuiz.actualizarNota();
        assertEquals(1d, seguimientoQuiz.getNota(), "La nota no se actualizó correctamente.");
        assertEquals("Exitoso", seguimientoQuiz.getEstado(), "El estado no es el esperado para una nota exitosa.");
        
        seguimientoQuiz.agregarRespuestaPregunta(pregunta1, 3); 
        seguimientoQuiz.agregarRespuestaPregunta(pregunta2, 2);

        seguimientoQuiz.actualizarNota();
        assertEquals(0.5d, seguimientoQuiz.getNota(), "La nota no se actualizó correctamente.");
        assertEquals("No Exitoso", seguimientoQuiz.getEstado(), "El estado no es el esperado para una nota no exitosa.");

        
    }

}

