import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PersistenciaJson {
	public static <T> void guardarEnJSON(List<T> inf, String filePath) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(filePath), inf);
	}
	
	public static <T> List<T> cargarJSON(String filePath, TypeReference<List<T>> typeReference) 
}
