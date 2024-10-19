package Persistencia_Datos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PersistenciaActividades {
		
		public static void persistirActividades(HashMap<String, Examen> ExamenMap, 
				HashMap<String, Encuesta> EncuestaMap,HashMap<String, Quiz> QuizMap,
				HashMap<String, RevisarRecurso> RevisarMap,HashMap<String, Tarea> TareaMap,
				String archivo) {
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonObject = new JsonObject();
			
			String[] activs = {"Encuesta", "Quiz", "Examen", "RevisarRecurso", "Tarea"};
			
			List<String> acts = Arrays.asList(activs);
			
			for(String actividad: acts) {
				
			for(Entry<String, acts> entry: studentMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Profesor> entry: profMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
			}
		
			try (FileWriter writer = new FileWriter(archivo)){
				gson.toJson(jsonObject, writer);
				System.out.printf("Usuarios guardados correctamente en archivo JSON: '%s'.", archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void cargarActividades(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Estudiante> studentMap = new HashMap<>();
			HashMap<String, Profesor> profMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject userObject = entry.getValue().getAsJsonObject();
					String tipo = userObject.get("tipo").getAsString();
					
					if("Estudiante".equals(tipo)) {
						Estudiante estudiante = gson.fromJson(userObject, Estudiante.class);
						studentMap.put(entry.getKey(), estudiante);
					} else if ("Profesor".equals(tipo)) {
						Profesor profesor = gson.fromJson(userObject, Profesor.class);
						profMap.put(entry.getKey(), profesor);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
		}

	}

	
}
