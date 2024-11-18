package persistenciaDatos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import preguntas.PreguntaAbierta;
import preguntas.PreguntaBoolean;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;

public class PersistenciaPreguntas {
	
	public static void persistirPreguntas(HashMap<String, PreguntaAbierta> abiertaMap, HashMap<String, PreguntaSeleccionMultiple> smMap, HashMap<String, PreguntaBoolean> bMap, String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				
				.create();
		JsonObject jsonObject = new JsonObject();
		JsonObject pAbiertas = new JsonObject();
		JsonObject pSM = new JsonObject();
		JsonObject pBool = new JsonObject();
		
		for(Entry<String, PreguntaAbierta> entry: abiertaMap.entrySet()) {
			pAbiertas.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		for(Entry<String, PreguntaSeleccionMultiple> entry: smMap.entrySet()) {
			pSM.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		for(Entry<String, PreguntaBoolean> entry: bMap.entrySet()) {
			pBool.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		
		jsonObject.add("Preguntas Selección Múltiple", pSM);
		jsonObject.add("Preguntas Verdadero Falso", pBool);
		jsonObject.add("Preguntas Abiertas", pAbiertas);
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(jsonObject, writer);
			System.out.printf("Preguntas guardadas correctamente en archivo JSON: '%s'.\n", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, PreguntaAbierta> cargarAbiertas(String archivo) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.create();
		
		HashMap<String, PreguntaAbierta> abiertaMap = new HashMap<String, PreguntaAbierta>();	
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			JsonObject abiertas = jsonObject.getAsJsonObject("Preguntas Abiertas");
			
			if(abiertas != null) {
				for (Map.Entry<String, JsonElement> entry: abiertas.entrySet()) {
					PreguntaAbierta pregunta = gson.fromJson(entry.getValue().getAsJsonObject(), PreguntaAbierta.class);
					abiertaMap.put(entry.getKey(), pregunta);
				} 
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return abiertaMap;
	}
	
	public static HashMap<String, PreguntaSeleccionMultiple> cargarSM(String archivo) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.create();
		
		HashMap<String, PreguntaSeleccionMultiple> smMap = new HashMap<String, PreguntaSeleccionMultiple>();			
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			JsonObject cerradas = jsonObject.getAsJsonObject("Preguntas Selección Múltiple");
			
			if(cerradas != null) {
				for (Entry<String, JsonElement> entry: cerradas.entrySet()) {
					PreguntaSeleccionMultiple pregunta = gson.fromJson(entry.getValue().getAsJsonObject(), PreguntaSeleccionMultiple.class);
					smMap.put(entry.getKey(), pregunta);
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return smMap;
	}
	
	public static HashMap<String, PreguntaBoolean> cargarBooleanas(String archivo) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeAdapter(PreguntaCerrada.class, new PreguntaCerradaAdapter())
				.create();
		
		HashMap<String, PreguntaBoolean> boolMap = new HashMap<String, PreguntaBoolean>();			
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			JsonObject cerradas = jsonObject.getAsJsonObject("Preguntas Verdadero Falso");
			
			if(cerradas != null) {
				for (Entry<String, JsonElement> entry: cerradas.entrySet()) {
					PreguntaBoolean pregunta = gson.fromJson(entry.getValue().getAsJsonObject(), PreguntaBoolean.class);
					boolMap.put(entry.getKey(), pregunta);
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return boolMap;
	}

}
