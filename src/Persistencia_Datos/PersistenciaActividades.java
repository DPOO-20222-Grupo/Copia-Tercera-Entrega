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
import User.Profesor;

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
				
			for(Entry<String, Encuesta> entry: EncuestaMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Examen> entry: ExamenMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Quiz> entry: QuizMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, RevisarRecurso> entry: RevisarMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Tarea> entry: TareaMap.entrySet()) {
				jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
			}
		
			try (FileWriter writer = new FileWriter(archivo)){
				gson.toJson(jsonObject, writer);
				System.out.printf("Actividades guardadas correctamente en archivo JSON: '%s'.", archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static HashMap<String, Examen> cargarExamen(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Examen> ExamenMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject userObject = entry.getValue().getAsJsonObject();
					String tipo = userObject.get("TIPO").getAsString();
					
					if("Examen".equals(tipo)) {
						Examen examen = gson.fromJson(userObject, Examen.class);
						ExamenMap.put(entry.getKey(), examen);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return ExamenMap;
		}
		
		public static HashMap<String, Encuesta> cargarEncuesta(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Encuesta> EncuestaMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject userObject = entry.getValue().getAsJsonObject();
					String tipo = userObject.get("TIPO").getAsString();
					
					if("Encuesta".equals(tipo)) {
						Encuesta encuesta = gson.fromJson(userObject, Encuesta.class);
						EncuestaMap.put(entry.getKey(), encuesta);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return EncuestaMap;
		}
		
		public static HashMap<String, Quiz> cargarQuiz(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Quiz> QuizMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject userObject = entry.getValue().getAsJsonObject();
					String tipo = userObject.get("TIPO").getAsString();
					
					if("Quiz".equals(tipo)) {
						Quiz quiz = gson.fromJson(userObject, Quiz.class);
						QuizMap.put(entry.getKey(), quiz);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return QuizMap;
		}
		
		public static HashMap<String, RevisarRecurso> cargarRecurso(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, RevisarRecurso> RecursosMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject userObject = entry.getValue().getAsJsonObject();
					String tipo = userObject.get("TIPO").getAsString();
					
					if("Recurso".equals(tipo)) {
						RevisarRecurso recurso = gson.fromJson(userObject, RevisarRecurso.class);
						RecursosMap.put(entry.getKey(), recurso);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return RecursosMap;
		}
		
		public static HashMap<String, Tarea> cargarTarea(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Tarea> TareaMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject userObject = entry.getValue().getAsJsonObject();
					String tipo = userObject.get("TIPO").getAsString();
					
					if("Tarea".equals(tipo)) {
						Tarea tarea = gson.fromJson(userObject, Tarea.class);
						TareaMap.put(entry.getKey(), tarea);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return TareaMap;
		}
	
}
