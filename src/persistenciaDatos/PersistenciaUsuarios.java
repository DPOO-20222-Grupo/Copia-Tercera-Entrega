package persistenciaDatos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import actividades.Actividad;
import preguntas.PreguntaCerrada;
import seguimientoEstudiantes.SeguimientoActividad;
import user.Estudiante;
import user.Profesor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PersistenciaUsuarios {
	
	public static void persistirUsuarios(HashMap<String, Estudiante> studentMap, HashMap<String, Profesor> profMap, String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(Actividad.class, new ActividadAdapter())
				.registerTypeAdapter(SeguimientoActividad.class, new SeguimientoActividadAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.create();
		
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

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(Actividad.class, new ActividadAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.registerTypeAdapter(SeguimientoActividad.class, new SeguimientoActividadAdapter())	

				.create();
		
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
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())


				.registerTypeAdapter(SeguimientoActividad.class, new SeguimientoActividadAdapter())

				.registerTypeAdapter(Actividad.class, new ActividadAdapter())
				.create();
		
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
	
	