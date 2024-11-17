package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Tarea;
import user.Profesor;

class TareaTest {
	private Tarea tarea;
	private List<String> objetivos;
	private Date fecha;
	private Profesor profesor;
	

	@BeforeEach
	void SetUp() {
		profesor = new Profesor("c.espinosag", "password", "César Espinosa");
		
		objetivos = new ArrayList<String>();
		objetivos.add("Aprender ASM");
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, 2004);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 2);
		
		fecha = calendario.getTime();
		
		tarea = new Tarea("Tarea1", "Traduzca de C a ASM.", objetivos, "Medio", 120, fecha, profesor);
	}
	
	@Test
	void testGetTipo() {
		assertEquals("Tarea", tarea.getTipoActividad());
	}

}
