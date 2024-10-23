package Persistencia_Datos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Preguntas.PreguntaAbierta;
import Preguntas.PreguntaSeleccionMultiple;

public class PersistenciaPreguntas {
	
	public static void persistirPreguntas(HashMap<String, PreguntaAbierta> abiertaMap, HashMap<String, PreguntaSeleccionMultiple> cerradaMap, String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject jsonObject = new JsonObject();
		JsonObject pAbiertas = new JsonObject();
		JsonObject pCerradas = new JsonObject();
		
		for(Entry<String, PreguntaAbierta> entry: abiertaMap.entrySet()) {
			pAbiertas.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		for(Entry<String, PreguntaSeleccionMultiple> entry: cerradaMap.entrySet()) {
			pCerradas.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		jsonObject.add("Preguntas Selección Múltiple", pCerradas);
		jsonObject.add("Preguntas Abiertas", pAbiertas);
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(jsonObject, writer);
			System.out.printf("Preguntas guardadas correctamente en archivo JSON: '%s'.\n", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, PreguntaAbierta> cargarAbiertas(String archivo) {
		Gson gson = new Gson();
		
		HashMap<String, PreguntaAbierta> abiertaMap = new HashMap<>();	
		
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
	
	public static HashMap<String, PreguntaSeleccionMultiple> cargarCerradas(String archivo) {
		Gson gson = new Gson();
		
		HashMap<String, PreguntaSeleccionMultiple> cerradaMap = new HashMap<>();			
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			JsonObject cerradas = jsonObject.getAsJsonObject("Preguntas Selección Múltiple");
			
			if(cerradas != null) {
				for (Entry<String, JsonElement> entry: cerradas.entrySet()) {
					PreguntaSeleccionMultiple pregunta = gson.fromJson(entry.getValue().getAsJsonObject(), PreguntaSeleccionMultiple.class);
					cerradaMap.put(entry.getKey(), pregunta);
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return cerradaMap;
	}

}
