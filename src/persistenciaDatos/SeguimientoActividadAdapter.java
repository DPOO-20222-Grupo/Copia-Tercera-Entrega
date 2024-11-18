package persistenciaDatos;

import com.google.gson.*;
import seguimientoEstudiantes.*;

import java.lang.reflect.Type;

public class SeguimientoActividadAdapter implements JsonSerializer<SeguimientoActividad>, JsonDeserializer<SeguimientoActividad> {

    @Override
    public JsonElement serialize(SeguimientoActividad seguimiento, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(seguimiento).getAsJsonObject();
        jsonObject.addProperty("tipoSeguimiento", seguimiento.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public SeguimientoActividad deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if (!jsonObject.has("tipoSeguimiento")) {
            throw new JsonParseException("El campo 'tipoSeguimiento' está ausente en el JSON.");
        }

        String typeName = jsonObject.get("tipoSeguimiento").getAsString();

        try {
            Class<?> clazz = Class.forName("seguimientoEstudiantes." + typeName); // Ruta del paquete de las subclases
            return context.deserialize(jsonObject, clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Tipo desconocido: " + typeName, e);
        }
    }
}
