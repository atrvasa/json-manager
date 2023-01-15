package com.atrvasa.json;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.json.exceptions.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonGson extends JsonMap {


    public static JsonArray toJsonArray(String value) throws JsonParserException {
        JsonArray result = (new JsonParser()).parse(value).getAsJsonArray();
        if (!result.isJsonArray())
            throw AenahInitializer.init(
                    new JsonParserException(),
                    new AenahDetail()
                            .type("JsonGson")
                            .function("toJsonArray")
            );
        return result.getAsJsonArray();
    }

    public static List<JsonObject> jsonArrayToList(JsonArray array) throws JsonParserException {
        List<JsonObject> result = new ArrayList<>();
        for (JsonElement jsonElement : array) {
            JsonObject obj = toJson(jsonElement.toString());
            if (!obj.isJsonObject())
                continue;

            result.add(obj);
        }
        return result;
    }

    /**
     * Convert a string to JsonObject
     *
     * @param stringJson's value must be a string JSON.
     * @return JsonObject
     * @throws JsonParserException: This function throws a JsonParserException
     * if there is any problem when parsing a string to JsonObject
     */
    public static JsonObject toJson(String stringJson) throws JsonParserException {
        JsonElement jsonElement = new JsonParser().parse(stringJson);

        if (!jsonElement.isJsonObject())
            throw AenahInitializer.init(
                    new JsonParserException(),
                    new AenahDetail().type("JsonGson").function("toJson")
            );
        return jsonElement.getAsJsonObject();
    }

    public JsonObject getFieldAsJsonObject(String key) throws FieldKeyNullException, FieldValueNullException,
            FieldNotFoundException, JsonObjectIsNullException, FieldNotJsonObjectException {

        JsonElement element = this.getElement(key);
        if (!element.isJsonObject())
            throw AenahInitializer.init(
                    new FieldNotJsonObjectException(),
                    new AenahDetail()
                            .type("JsonGson")
                            .function("getFieldAsJsonObject")
            );
        return element.getAsJsonObject();
    }

    public JsonObject getFieldAsJsonObject(String fieldKey, JsonObject defaultValue) {
        try {
            return getFieldAsJsonObject(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException | FieldNotJsonObjectException e) {
            return defaultValue;
        }
    }

    public JsonArray getFieldAsJsonArray(String key) throws FieldValueNullException, FieldKeyNullException,
            FieldNotJsonArrayException, FieldNotFoundException, JsonObjectIsNullException {
        JsonElement element = this.getElement(key);
        if (!element.isJsonArray())
            throw AenahInitializer.init(
                    new FieldNotJsonArrayException(),
                    new AenahDetail()
                            .type("JsonGson")
                            .function("getFieldAsJsonList")
            );
        return element.getAsJsonArray();
    }

    public JsonArray getFieldAsJsonArray(String fieldKey, JsonArray defaultValue) {
        try {
            return getFieldAsJsonArray(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException | FieldNotJsonArrayException e) {
            return defaultValue;
        }
    }

}
