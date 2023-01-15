package com.atrvasa.json;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.infra.StringTools;
import com.atrvasa.json.exceptions.FieldKeyNullException;
import com.atrvasa.json.exceptions.FieldNotFoundException;
import com.atrvasa.json.exceptions.FieldValueNullException;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class JsonString {
    private JsonObject jsonObject;

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonElement getElement(String fieldKey) throws JsonObjectIsNullException, FieldKeyNullException,
            FieldValueNullException, FieldNotFoundException {
        if (jsonObject == null)
            throw AenahInitializer.init(
                    new JsonObjectIsNullException(),
                    new AenahDetail().type("JsonString")
                            .function("getElement")
            );

        if (StringTools.isNullOrEmpty(fieldKey))
            throw AenahInitializer.init(
                    new FieldKeyNullException(),
                    new AenahDetail().type("JsonString")
                            .function("getElement")
            );

        JsonElement jsonElement = jsonObject.get(fieldKey);
        if (jsonElement == null)
            throw AenahInitializer.init(
                    new FieldNotFoundException(),
                    new AenahDetail().type("JsonString")
                            .function("getElement")
                            .addElement("field-key", fieldKey)
            );
        if (jsonElement.isJsonNull())
            throw AenahInitializer.init(
                    new FieldValueNullException(),
                    new AenahDetail()
                            .type("JsonTools")
                            .function("getElement")
                            .addElement("field-key", fieldKey)
            );

        return jsonElement;
    }

    public String getFieldAsString(String fieldKey) throws FieldKeyNullException,
            FieldValueNullException, FieldNotFoundException, JsonObjectIsNullException {

        JsonElement jsonElement = this.getElement(fieldKey);

        String value = null;
        try {
            value = jsonElement.getAsString();
        } catch (Exception e) {
            value = jsonElement.toString();
        }

        return value;
    }


    public String getFieldAsString(String fieldKey, String defaultValue)  {
        try {
            return getFieldAsString(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }
}
