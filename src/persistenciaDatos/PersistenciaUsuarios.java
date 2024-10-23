package persistenciaDatos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import user.Estudiante;
import user.Profesor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PersistenciaUsuarios {
	
	public static void persistirUsuarios(HashMap<String, Estudiante> studentMap, HashMap<String, Profesor> profMap, String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonObject jsonObject = new JsonObject();
		JsonObject estudiantes = new JsonObject();
		JsonObject profesores = new JsonObject();
		
		for(Entry<String, Estudiante> entry: studentMap.entrySet()) {
			estudiantes.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		for(Entry<String, Profesor> entry: profMap.entrySet()) {
			profesores.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		
		jsonObject.add("estudiantes", estudiantes);
		jsonObject.add("profesores", profesores);
		
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(jsonObject, writer);
			System.out.printf("Usuarios guardados correctamente en archivo JSON: '%s'.\n", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, Estudiante> cargarEstudiantes(String archivo) {

		Gson gson = new Gson();
		
		HashMap<String, Estudiante> studentMap = new HashMap<String, Estudiante>();	
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
			JsonObject estudiantesJson = jsonObject.getAsJsonObject("estudiantes");
			if (estudiantesJson != null) {
				for (Map.Entry<String, JsonElement> entry: estudiantesJson.entrySet()) {
					Estudiante estudiante = gson.fromJson(entry.getValue().getAsJsonObject(), Estudiante.class);
					studentMap.put(entry.getKey(), estudiante);
				} 
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return studentMap;
	}
	
	public static HashMap<String, Profesor> cargarProfesores(String archivo) {
		Gson gson = new Gson();
		
		HashMap<String, Profesor> profMap = new HashMap<String, Profesor>();	
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
			JsonObject profesoresJson = jsonObject.getAsJsonObject("profesores");
			if (profesoresJson != null) {
				for (Map.Entry<String, JsonElement> entry: profesoresJson.entrySet()) {
					Profesor profesor = gson.fromJson(entry.getValue().getAsJsonObject(), Profesor.class);
					profMap.put(entry.getKey(), profesor);
				} 
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return profMap;
	}

}
	
	