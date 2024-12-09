package persistenciaDatos;

import com.google.gson.*;
import seguimientoEstudiantes.SeguimientoLearningPath;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LearningPathsAdapter implements JsonDeserializer<Map<String, SeguimientoLearningPath>>, JsonSerializer<Map<String, SeguimientoLearningPath>> {

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

    @Override
    public JsonElement serialize(Map<String, SeguimientoLearningPath> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, SeguimientoLearningPath> entry : src.entrySet()) {
            jsonObject.add(entry.getKey(), context.serialize(entry.getValue()));
        }
        return jsonObject;
    }
}
