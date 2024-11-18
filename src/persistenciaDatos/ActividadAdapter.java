package persistenciaDatos;
import com.google.gson.*;

import actividades.Actividad;

import java.lang.reflect.Type;

public class ActividadAdapter implements JsonSerializer<Actividad>, JsonDeserializer<Actividad>{


    @Override
    public JsonElement serialize(Actividad actividad, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(actividad).getAsJsonObject();
        jsonObject.addProperty("tipoActividad", actividad.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public Actividad deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeName = jsonObject.get("tipoActividad").getAsString();

        try {
            Class<?> clase = Class.forName("actividades." + typeName);
            return context.deserialize(jsonObject, clase);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Tipo no identificado: " + typeName, e);
        }
    }
}