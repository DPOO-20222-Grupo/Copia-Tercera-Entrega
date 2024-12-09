package persistenciaDatos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PersistenciaActividadesDiarias {
	
	public static void persistirActividades(HashMap<String, Integer> mapaActividadesCompletadas, String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.create();
		
		JsonObject actividades = new JsonObject();
		
		for(Entry<String, Integer> entry: mapaActividadesCompletadas.entrySet()) {
			actividades.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
		}
		
		
		try (FileWriter writer = new FileWriter(archivo)){
			gson.toJson(actividades, writer);
			System.out.printf("Conteo de actividades guardado correctamente en archivo JSON: '%s'.\n", archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, Integer> cargarActividadesCompletadas(String archivo) {

		Gson gson = new GsonBuilder()

				.create();
		
		HashMap<String, Integer> mapaActividades = new HashMap<String, Integer>();	
		
		try(FileReader reader = new FileReader(archivo)){
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			System.out.println(jsonObject);
			if (jsonObject != null) {
				for (Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
					int valorDiario = entry.getValue().getAsInt();
					mapaActividades.put(entry.getKey(), valorDiario);
				} 
			}
			
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		return mapaActividades;
	}
	

}
	
	