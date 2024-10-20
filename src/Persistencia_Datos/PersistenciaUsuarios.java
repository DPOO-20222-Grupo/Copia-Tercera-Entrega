package Persistencia_Datos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import User.Estudiante;
import User.Profesor;

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
		
		for(Entry<String, Estudiante> entry: studentMap.entrySet()) {
			jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
		}
		
		for(Entry<String, Profesor> entry: profMap.entrySet()) {
			jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
		}
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(jsonObject, writer);
			System.out.printf("Usuarios guardados correctamente en archivo JSON: '%s'.", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, Estudiante> cargarEstudiantes(String archivo) {
		Gson gson = new Gson();
		
		HashMap<String, Estudiante> studentMap = new HashMap<>();	
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
			
			for (Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
				JsonObject userObject = entry.getValue().getAsJsonObject();
				String tipo = userObject.get("tipo").getAsString();
				
				if("Estudiante".equals(tipo)) {
					Estudiante estudiante = gson.fromJson(userObject, Estudiante.class);
					studentMap.put(entry.getKey(), estudiante);
				} 
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return studentMap;
	}
	
	public static HashMap<String, Profesor> cargarProfesor(String archivo) {
		Gson gson = new Gson();
		
		HashMap<String, Profesor> profMap = new HashMap<>();			
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
			
			for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
				JsonObject userObject = entry.getValue().getAsJsonObject();
				String tipo = userObject.get("tipo").getAsString();
				
				if("Profesor".equals(tipo)) {
					Profesor profesor = gson.fromJson(userObject, Profesor.class);
					profMap.put(entry.getKey(), profesor);
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return profMap;
	}

}
	
	