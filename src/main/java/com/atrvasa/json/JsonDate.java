package com.atrvasa.json;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.infra.DateTools;
import com.atrvasa.json.exceptions.FieldKeyNullException;
import com.atrvasa.json.exceptions.FieldNotFoundException;
import com.atrvasa.json.exceptions.FieldValueNullException;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;

import java.util.Date;

public abstract class JsonDate extends JsonBoolean {
    public Date getFieldAsDate(String fieldKey, String fromCultureName)
            throws FieldValueNullException, FieldKeyNullException, FieldNotFoundException,
            JsonObjectIsNullException {
        String str = getFieldAsString(fieldKey);
        if (str.equals(""))
            throw AenahInitializer.init(
                    new FieldValueNullException(),
                    new AenahDetail()
                            .type("JsonTools")
                            .function("getFieldAsDate")
                            .addElement("property-key", fieldKey)
            );

        return DateTools.getDate(str, fromCultureName);
    }

    public Date getFieldAsDate(String fieldKey, String fromCultureName, Date defaultValue)  {
        try {
            return getFieldAsDate(fieldKey, fromCultureName);
        } catch (FieldValueNullException | FieldNotFoundException | JsonObjectIsNullException | FieldKeyNullException e) {
            if (defaultValue == null)
                return null;
            return defaultValue;
        }
    }
}
