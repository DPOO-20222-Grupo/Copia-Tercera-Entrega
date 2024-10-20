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
		
		for(Entry<String, PreguntaAbierta> entry: abiertaMap.entrySet()) {
			jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
		}
		
		for(Entry<String, PreguntaSeleccionMultiple> entry: cerradaMap.entrySet()) {
			jsonObject.add(archivo, gson.toJsonTree(entry.getValue()));
		}
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(jsonObject, writer);
			System.out.printf("Preguntas guardadas correctamente en archivo JSON: '%s'.", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, PreguntaAbierta> cargarAbiertas(String archivo) {
		Gson gson = new Gson();
		
		HashMap<String, PreguntaAbierta> abiertaMap = new HashMap<>();	
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);			
			
			for (Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
				JsonObject userObject = entry.getValue().getAsJsonObject();
				String tipo = userObject.get("TIPO").getAsString();
				
				if("Abierta".equals(tipo)) {
					PreguntaAbierta pregunta = gson.fromJson(userObject, PreguntaAbierta.class);
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
			
			for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
				JsonObject userObject = entry.getValue().getAsJsonObject();
				String tipo = userObject.get("TIPO").getAsString();
				
				if("Cerrada".equals(tipo)) {
					PreguntaSeleccionMultiple pregunta = gson.fromJson(userObject, PreguntaSeleccionMultiple.class);
					cerradaMap.put(entry.getKey(), pregunta);
				}
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return cerradaMap;
	}

}
