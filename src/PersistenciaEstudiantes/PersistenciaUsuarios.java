package PersistenciaEstudiantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import User.Estudiante;
import User.Profesor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PersistenciaUsuarios {
	
	public static void persistirEstudiantes(HashMap<String, Estudiante> studentMap, String archivo) {
		ObjectMapper mp = new ObjectMapper();
		
		try {
			mp.writeValue(new File(archivo), studentMap);
			System.out.println("Estudiantes guardados correctamente en archivo JSON.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void persistirProfesores(HashMap<String, Profesor> profMap, String archivo) {
		
		
	}

}