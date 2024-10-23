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
import java.util.HashMap;
import java.util.Map.Entry;

public class PersistenciaActividades {
		
		public static void persistirActividades(HashMap<String, Examen> ExamenMap, 
				HashMap<String, Encuesta> EncuestaMap,HashMap<String, Quiz> QuizMap,
				HashMap<String, RevisarRecurso> RevisarMap,HashMap<String, Tarea> TareaMap,
				String archivo) {
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonObject = new JsonObject();
			JsonObject encuestas = new JsonObject();
			JsonObject examenes = new JsonObject();
			JsonObject quices = new JsonObject();
			JsonObject recursos = new JsonObject();
			JsonObject tareas = new JsonObject();
				
			
			for(Entry<String, Encuesta> entry: EncuestaMap.entrySet()) {
				encuestas.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Examen> entry: ExamenMap.entrySet()) {
				examenes.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Quiz> entry: QuizMap.entrySet()) {
				quices.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, RevisarRecurso> entry: RevisarMap.entrySet()) {
				recursos.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
			}
			
			for(Entry<String, Tarea> entry: TareaMap.entrySet()) {
				tareas.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
			}
			
			jsonObject.add("Tareas", tareas);
			jsonObject.add("Examenes", examenes);
			jsonObject.add("Revisar Recursos", recursos);
			jsonObject.add("Quices", quices);
			jsonObject.add("Encuestas", encuestas);
		
			try (FileWriter writer = new FileWriter(archivo)){
				gson.toJson(jsonObject, writer);
				System.out.printf("Actividades guardadas correctamente en archivo JSON: '%s'.\n", archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static HashMap<String, Examen> cargarExamen(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Examen> examenMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);	
				JsonObject examenes = jsonObject.getAsJsonObject("Examenes");
				
				if(examenes != null) {
					for (Entry<String, JsonElement> entry: examenes.entrySet()) {
						Examen examen = gson.fromJson(entry.getValue().getAsJsonObject(), Examen.class);
						examenMap.put(entry.getKey(), examen);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return examenMap;
		}
		
		public static HashMap<String, Encuesta> cargarEncuesta(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Encuesta> encuestaMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);	
				JsonObject encuestas = jsonObject.getAsJsonObject("Encuestas");
				
				if(encuestas != null) {
					for (Entry<String, JsonElement> entry: encuestas.entrySet()) {
						Encuesta encuesta = gson.fromJson(entry.getValue().getAsJsonObject(), Encuesta.class);
						encuestaMap.put(entry.getKey(), encuesta);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return encuestaMap;
		}
		
		public static HashMap<String, Quiz> cargarQuiz(String archivo) {
			Gson gson = new Gson();
			
			HashMap<String, Quiz> QuizMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject actObject = entry.getValue().getAsJsonObject();
					String tipo = actObject.get("TIPO").getAsString();
					
					if("Quiz".equals(tipo)) {
						Quiz quiz = gson.fromJson(actObject, Quiz.class);
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
					JsonObject actObject = entry.getValue().getAsJsonObject();
					String tipo = actObject.get("TIPO").getAsString();
					
					if("Recurso".equals(tipo)) {
						RevisarRecurso recurso = gson.fromJson(actObject, RevisarRecurso.class);
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
					JsonObject actObject = entry.getValue().getAsJsonObject();
					String tipo = actObject.get("TIPO").getAsString();
					
					if("Tarea".equals(tipo)) {
						Tarea tarea = gson.fromJson(actObject, Tarea.class);
						TareaMap.put(entry.getKey(), tarea);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return TareaMap;
		}
	
}
