package com.atrvasa.json;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.exception.exceptions.ClassTypeIsNullException;
import com.atrvasa.infra.DefaultPatterns;
import com.atrvasa.infra.TypeTools;
import com.atrvasa.json.annotation.AnnotationExclusionStrategy;
import com.atrvasa.json.exceptions.*;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonModel extends JsonGson {

    public static Gson getGson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(DefaultPatterns.DATE_PATTERN);
        gsonBuilder.setExclusionStrategies(new AnnotationExclusionStrategy());

        gsonBuilder.setPrettyPrinting();

        return gsonBuilder.create();
    }

    public static <T> JsonObject modelToJson(T model) throws JsonParserException {
        Gson gson = getGson();
        String jsonString = gson.toJson(model);
        JsonObject result = toJson(jsonString);
        if (!result.isJsonObject())
            throw AenahInitializer.init(
                    new JsonParserException(),
                    new AenahDetail()
                            .type("JsonTools")
                            .function("toJson")
            );

        return result.getAsJsonObject();
    }

    public static <T> T loadJsonToModel(Class<T> type, String stringJson) {
        try {
            Gson gson = getGson();
            return gson.fromJson(stringJson, type);
        } catch (JsonSyntaxException ex) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setExclusionStrategies(new AnnotationExclusionStrategy());
            gsonBuilder.setPrettyPrinting();
            Gson g = gsonBuilder.create();

            return g.fromJson(stringJson, type);
        }
    }

    public static <T> T loadJsonToModel(Class<T> type, JsonObject jsonObject) {
        return loadJsonToModel(type, jsonObject.toString());
    }


    public <T> List<T> getFieldAsList(String fieldKey, Class<T> type) throws FieldValueNullException,
            FieldNotFoundException, FieldKeyNullException, JsonObjectIsNullException, JsonParserException {
        String str = getFieldAsString(fieldKey);

        List<T> result = new ArrayList<>();
        JsonArray array = toJsonArray(str);
        if (TypeTools.equalsTypes(type, String.class)) {
            for (int i = 0; i < array.size(); i++) {
                result.add((T) array.get(i).getAsString());
            }
        } else {
            for (JsonObject obj : jsonArrayToList(array)) {
                result.add(loadJsonToModel(type, obj));
            }
        }

        return result;
    }

    public <T> List<T> getFieldAsList(String fieldKey, Class<T> type, List<T> defaultValue)
            throws JsonParserException {
        try {
            return getFieldAsList(fieldKey, type);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }


    public <T> T getFieldAsModel(String fieldKey, Class<T> type) throws ClassTypeIsNullException,
            FieldKeyNullException, FieldNotFoundException, FieldValueNullException, JsonObjectIsNullException {
        if (type == null)
            throw AenahInitializer.init(
                    new ClassTypeIsNullException(),
                    new AenahDetail()
                            .type("JsonTools")
                            .function("loadJson")
            );

        return loadJsonToModel(type, getFieldAsString(fieldKey));
    }

    public <T> T getFieldAsModel(String fieldKey, Class<T> type, T defaultValue) {
        try {
            return getFieldAsModel(fieldKey, type);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | ClassTypeIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }

}
