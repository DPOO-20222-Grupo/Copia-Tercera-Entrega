package persistenciaDatos;

import com.google.gson.*;

import preguntas.PreguntaCerrada;

import java.lang.reflect.Type;

public class PreguntaCerradaAdapter implements JsonSerializer<PreguntaCerrada>, JsonDeserializer<PreguntaCerrada> {

    @Override
    public JsonElement serialize(PreguntaCerrada pregunta, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(pregunta).getAsJsonObject();
        jsonObject.addProperty("tipoSeguimiento", pregunta.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public PreguntaCerrada deserialize(JsonElement json, Type type, JsonDeserializationContext context)
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

