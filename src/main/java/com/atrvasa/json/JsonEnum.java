package com.atrvasa.json;

import com.atrvasa.json.exceptions.FieldKeyNullException;
import com.atrvasa.json.exceptions.FieldNotFoundException;
import com.atrvasa.json.exceptions.FieldValueNullException;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;

import java.lang.reflect.Field;

public abstract class JsonEnum extends JsonUUID {
    public <T extends Enum> T getFieldAsEnum(String fieldKey, Class<T> type, T defaultValue)  {
        try {
            return getFieldAsEnum(fieldKey, type);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            return defaultValue;
        }
    }

    public <T extends Enum> T getFieldAsEnum(String fieldKey, Class<T> type)
            throws FieldValueNullException, FieldKeyNullException, FieldNotFoundException, JsonObjectIsNullException {
        String str = getFieldAsString(fieldKey);

        T result = null;
        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equals(str)) {
                Enum e = Enum.valueOf(type, str);

                result = (T) e;
            }
        }

        return result;
    }
}
