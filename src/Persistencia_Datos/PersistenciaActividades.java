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
	
		// Función de Descarga
		
		public static void persistirActividades(HashMap<String, Examen> ExamenMap, 
				HashMap<String, Encuesta> EncuestaMap,HashMap<String, Quiz> QuizMap,
				HashMap<String, RevisarRecurso> RevisarMap,HashMap<String, Tarea> TareaMap,
				String archivo) {
			
			// Creación del Objeto Json y del Serializador
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonObject = new JsonObject();
				
			// Recprrridpp de los HashMaps y conversión de sus entrys a
			// objetos JSON.
			
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
			
			// Serialización del Objeto JSON
		
			try (FileWriter writer = new FileWriter(archivo)){
				gson.toJson(jsonObject, writer);
				System.out.printf("Actividades guardadas correctamente en archivo JSON: '%s'.", archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Funciones de Carga
		
		public static HashMap<String, Examen> cargarExamen(String archivo) {
			Gson gson = new Gson();
			
			// Creación de la Estructura: HashMap contenedor de exámenes
			
			HashMap<String, Examen> ExamenMap = new HashMap<>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);	
				
				// Recorrido del Objeto JSON y sus entradas
				// se utilizan condicionales, en caso de que la entrada sea de tipo Exámen se añade al mapa.
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject actObject = entry.getValue().getAsJsonObject();
					String tipo = actObject.get("TIPO").getAsString();
					
					if("Examen".equals(tipo)) {
						Examen examen = gson.fromJson(actObject, Examen.class);
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
			
			// Creación de la estructura: HashMap contenedor de Encuesta
			
			HashMap<String, Encuesta> EncuestaMap = new HashMap<>();	
			
			// Lectura del Objeto JSON y sus entradas. 
			// En caso de que la entrada sea del tipo esperado: "Encuesta", lo añade a la estructura principal: un HashMap
			
			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
				
				for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					JsonObject actObject = entry.getValue().getAsJsonObject();
					String tipo = actObject.get("TIPO").getAsString();
					
					if("Encuesta".equals(tipo)) {
						Encuesta encuesta = gson.fromJson(actObject, Encuesta.class);
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
