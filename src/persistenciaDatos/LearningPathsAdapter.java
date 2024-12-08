package persistenciaDatos;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import seguimientoEstudiantes.SeguimientoLearningPath;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LearningPathsAdapter implements JsonDeserializer<Map<String, SeguimientoLearningPath>> {
    @Override
    public Map<String, SeguimientoLearningPath> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        Map<String, SeguimientoLearningPath> map = new HashMap<>();
        if (json != null && json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                SeguimientoLearningPath seguimiento = context.deserialize(entry.getValue(), SeguimientoLearningPath.class);
                map.put(entry.getKey(), seguimiento);
            }
        }
        return map;
    }
}