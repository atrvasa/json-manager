package com.atrvasa.json;

import java.util.HashMap;
import java.util.Map;

public abstract class JsonMap extends JsonEnum {

    public Map<String, Object> jsonObjectToMapObject() {

        Map<String, Object> result = new HashMap<>();
        for (String key : this.getJsonObject().keySet()) {
            Object value = getFieldAsString(key, null);
            if (value != null)
                result.put(key, value);
        }

        return result;
    }
}
