package persistenciaDatos;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import preguntas.PreguntaCerrada;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;


public class PersistenciaActividades {
		
		public static void persistirActividades(HashMap<String, Examen> ExamenMap, 
				HashMap<String, Encuesta> EncuestaMap,HashMap<String, Quiz> QuizMap,
				HashMap<String, RevisarRecurso> RevisarMap,HashMap<String, Tarea> TareaMap,
				String archivo) {

			Gson gson = new GsonBuilder().setPrettyPrinting()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(Actividad.class, new ActividadAdapter())
					.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
					.create();
					
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
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(Actividad.class, new ActividadAdapter())
					.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
					.create();
					
			
			HashMap<String, Examen> examenMap = new HashMap<String, Examen>();			
			
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
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(Actividad.class, new ActividadAdapter())
					.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
					.create();
			
			HashMap<String, Encuesta> encuestaMap = new HashMap<String, Encuesta>();			
			
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
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(Actividad.class, new ActividadAdapter())
					.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
					.create();
			
			HashMap<String, Quiz> quizMap = new HashMap<String, Quiz>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);	
				JsonObject quices = jsonObject.getAsJsonObject("Quices");
				
				if(quices != null) {
					for (Entry<String, JsonElement> entry: quices.entrySet()) {
						Quiz quiz = gson.fromJson(entry.getValue().getAsJsonObject(), Quiz.class);
						quizMap.put(entry.getKey(), quiz);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return quizMap;
		}
		
		public static HashMap<String, RevisarRecurso> cargarRecurso(String archivo) {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(Actividad.class, new ActividadAdapter())
					.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
					.create();
			
			HashMap<String, RevisarRecurso> recursosMap = new HashMap<String, RevisarRecurso>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);	
				JsonObject recursos = jsonObject.getAsJsonObject("Revisar Recursos");
				
				if(recursos != null) {
					for (Entry<String, JsonElement> entry: recursos.entrySet()) {
						RevisarRecurso recurso = gson.fromJson(entry.getValue().getAsJsonObject(), RevisarRecurso.class);
						recursosMap.put(entry.getKey(), recurso);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return recursosMap;
		}
		
		public static HashMap<String, Tarea> cargarTarea(String archivo) {
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
					.registerTypeAdapter(Actividad.class, new ActividadAdapter())
					.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
					.create();
			
			HashMap<String, Tarea> tareaMap = new HashMap<String, Tarea>();			
			
			try(FileReader reader = new FileReader(archivo)){
				JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);	
				JsonObject tareas = jsonObject.getAsJsonObject("Tareas");
				
				if(tareas != null) {
					for (Entry<String, JsonElement> entry: tareas.entrySet()) {
						Tarea tarea = gson.fromJson(entry.getValue().getAsJsonObject(), Tarea.class);
						tareaMap.put(entry.getKey(), tarea);
					}
				}
				
			} catch(IOException e){
				e.printStackTrace();			
			}
			
			return tareaMap;
		}
	
}
