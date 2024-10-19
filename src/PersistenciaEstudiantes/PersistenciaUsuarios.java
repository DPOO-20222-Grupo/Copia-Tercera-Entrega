package PersistenciaEstudiantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import User.Estudiante;
import User.Profesor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PersistenciaUsuarios {
	
	public static void persistirEstudiantes(HashMap<String, Estudiante> studentMap, HashMap<String, Profesor> profMap, String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			mp.writeValue(new File(archivo), studentMap);
			System.out.println("Estudiantes guardados correctamente en archivo JSON.");
		} catch (IOException e) {
			e.printStackTrace();
		}
}