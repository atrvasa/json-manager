package com.atrvasa.json;

import com.atrvasa.json.exceptions.FieldKeyNullException;
import com.atrvasa.json.exceptions.FieldNotFoundException;
import com.atrvasa.json.exceptions.FieldValueNullException;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;

public abstract class JsonBoolean extends JsonDigit {
    public boolean getFieldAsBoolean(String key) throws FieldValueNullException,
            FieldKeyNullException, FieldNotFoundException, JsonObjectIsNullException {
        return Boolean.parseBoolean(getFieldAsString(key));
    }

    public boolean getFieldAsBoolean(String fieldKey, boolean defaultValue)  {
        try {
            return getFieldAsBoolean(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }
}
