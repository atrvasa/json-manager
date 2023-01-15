package com.atrvasa.json;

import com.atrvasa.json.exceptions.FieldKeyNullException;
import com.atrvasa.json.exceptions.FieldNotFoundException;
import com.atrvasa.json.exceptions.FieldValueNullException;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;

public abstract class JsonDigit extends JsonString {
    public long getFieldAsLong(String key) throws FieldValueNullException, FieldKeyNullException,
            FieldNotFoundException, JsonObjectIsNullException {
        return Long.parseLong(getFieldAsString(key));
    }

    public long getFieldAsLong(String fieldKey, long defaultValue) {
        try {
            return getFieldAsLong(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }

    public double getFieldAsDouble(String key) throws FieldValueNullException,
            FieldKeyNullException, FieldNotFoundException, JsonObjectIsNullException {
        return Double.parseDouble(getFieldAsString(key));
    }

    public double getFieldAsDouble(String fieldKey, double defaultValue)  {
        try {
            return getFieldAsDouble(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }

    public int getFieldAsInt(String key) throws FieldValueNullException, FieldKeyNullException,
            FieldNotFoundException, JsonObjectIsNullException {
        return Integer.parseInt(getFieldAsString(key));
    }

    public int getFieldAsInt(String fieldKey, int defaultValue)  {
        try {
            return getFieldAsInt(fieldKey);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }
}
