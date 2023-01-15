package com.atrvasa.json;

import com.atrvasa.json.exceptions.FieldKeyNullException;
import com.atrvasa.json.exceptions.FieldNotFoundException;
import com.atrvasa.json.exceptions.FieldValueNullException;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;

import java.util.UUID;

public abstract class JsonUUID extends JsonDate {
    public UUID getFieldAsUUID(String key) throws FieldValueNullException,
            FieldKeyNullException, FieldNotFoundException, JsonObjectIsNullException {

        String value = getFieldAsString(key);
        return UUID.fromString(value);
    }

    public UUID getFieldAsUUID(String fieldKey, UUID defaultValue){
        try {
            return getFieldAsUUID(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }
}
