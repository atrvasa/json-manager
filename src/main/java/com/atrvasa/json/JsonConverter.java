package com.atrvasa.json;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.infra.StringTools;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;
import com.atrvasa.json.exceptions.JsonParserException;
import com.google.gson.JsonObject;

public class JsonConverter extends JsonModel {

    public JsonConverter(String jsonString) throws JsonParserException, JsonObjectIsNullException {
        if (StringTools.isNullOrEmpty(jsonString))
            throw AenahInitializer.init(
                    new JsonObjectIsNullException(),
                    new AenahDetail()
                            .type(JsonConverter.class)
            );

        this.setJsonObject(toJson(jsonString));
    }

    public JsonConverter(JsonObject jsonObject) throws JsonObjectIsNullException {
        if (jsonObject == null)
            throw AenahInitializer.init(
                    new JsonObjectIsNullException(),
                    new AenahDetail()
                            .type(JsonConverter.class)
            );

        this.setJsonObject(jsonObject);

    }


}
