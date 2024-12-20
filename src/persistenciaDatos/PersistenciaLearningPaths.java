package persistenciaDatos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import actividades.Actividad;
import learningPath.LearningPath;
import preguntas.PreguntaCerrada;
import seguimientoEstudiantes.SeguimientoActividad;

public class PersistenciaLearningPaths {
	
	public static void persistirLearningPaths(HashMap<String, LearningPath> LPMap,
			String archivo) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(Actividad.class, new ActividadAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.registerTypeAdapter(SeguimientoActividad.class, new SeguimientoActividadAdapter())
				.create();
		JsonObject jsonObject = new JsonObject();
			
		for(Entry<String, LearningPath> entry: LPMap.entrySet()) {
			jsonObject.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(jsonObject, writer);
			System.out.printf("LearningPaths guardados correctamente en archivo JSON: '%s'.\n", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, LearningPath> cargarLP(String archivo) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(Actividad.class, new ActividadAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.registerTypeAdapter(SeguimientoActividad.class, new SeguimientoActividadAdapter())
				.create();
		
		HashMap<String, LearningPath> LPMap = new HashMap<String, LearningPath>();			
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
			
			for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
				JsonObject lpObject = entry.getValue().getAsJsonObject();
				LearningPath lp = gson.fromJson(lpObject, LearningPath.class);
				LPMap.put(entry.getKey(), lp);
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return LPMap;
	}
	
}
