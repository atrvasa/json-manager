package com.atrvasa.json;

import com.atrvasa.exception.Aenah;
import com.atrvasa.exception.exceptions.ClassTypeIsNullException;
import com.atrvasa.json.enumeration.Type;
import com.atrvasa.json.exceptions.*;
import com.atrvasa.json.model.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {

    @org.junit.jupiter.api.Test
    void modelToJson() {
        try {
            Book book = new Book("Chekhov", "Keykhosrow");
            JsonObject result = JsonConverter.modelToJson(book);
            assertNotNull(result);
            assertTrue(result.isJsonObject());
            JsonConverter converter = new JsonConverter(result);
            assertEquals("Keykhosrow", converter.getFieldAsString("author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void loadJsonToModelFromJsonObject() {
        try {
            String stringJson = "{ author:  \"Keykhosrow\", title:  \"Chekhov\" }";
            JsonObject jsonObject = JsonConverter.toJson(stringJson);
            Book result = JsonConverter.loadJsonToModel(Book.class, jsonObject);
            assertNotNull(result);
            assertEquals("Keykhosrow", result.getAuthor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void loadJsonToModelFromString() {
        try {
            String stringJson = "{ author:  \"Keykhosrow\", title:  \"Chekhov\" }";
            Book result = JsonConverter.loadJsonToModel(Book.class, stringJson);
            assertNotNull(result);
            assertEquals("Keykhosrow", result.getAuthor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsList() {
        try {
            String stringJson = "{ list:  [{ author:  \"Keykhosrow\", title:  \"Chekhov\" }, { author:  \"Keykhosrow\", title:  \"Iran\" }]}";
            JsonConverter converter = new JsonConverter(stringJson);
            JsonArray result = converter.getFieldAsJsonArray("list");
            assertNotNull(result);
            assertTrue(result.get(0).isJsonObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsModel() {
        try {
            String stringJson = "{ obj:  { author:  \"Keykhosrow\", title:  \"Chekhov\" } }";
            JsonConverter converter = new JsonConverter(stringJson);
            Book result = converter.getFieldAsModel("obj", Book.class);
            assertNotNull(result);
            assertEquals("Keykhosrow", result.getAuthor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsJsonObject() {
        try {
            String stringJson = "{ obj:  { name:  \"Keykhosrow\" } }";
            JsonConverter converter = new JsonConverter(stringJson);
            JsonObject result = converter.getFieldAsJsonObject("obj");
            assertNotNull(result);
            assertTrue(result.isJsonObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsJsonArray() {
        try {
            String stringJson = "{ list:  [{ name:  \"Keykhosrow\" }, { name:  \"Iran\" }]}";
            JsonConverter converter = new JsonConverter(stringJson);
            JsonArray result = converter.getFieldAsJsonArray("list");
            assertNotNull(result);
            assertTrue(result.get(0).isJsonObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void jsonArrayToList() {
        try {
            String stringJson = "[{ name:  \"Keykhosrow\" }, { name:  \"Iran\" }]";
            JsonArray array = JsonConverter.toJsonArray(stringJson);
            assertNotNull(array);
            List<JsonObject> jsonList = JsonConverter.jsonArrayToList(array);
            assertNotNull(jsonList);
            JsonConverter converter = new JsonConverter(jsonList.get(0));
            String result = converter.getFieldAsString("name");
            assertEquals("Keykhosrow", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void toJson() {
        try {
            String stringJson = "{ name:  \"Keykhosrow\" }";
            JsonObject jsonObject = JsonConverter.toJson(stringJson);
            assertNotNull(jsonObject);
            JsonConverter converter = new JsonConverter(jsonObject);
            String result = converter.getFieldAsString("name");
            assertEquals("Keykhosrow", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void toJsonArray() {
        try {
            String stringJson = "[{ name:  \"Keykhosrow\" }, { name:  \"Iran\" }]";
            JsonArray array = JsonConverter.toJsonArray(stringJson);
            assertNotNull(array);
            JsonObject jsonObject = array.get(0).getAsJsonObject();
            assertNotNull(jsonObject);
            JsonConverter converter = new JsonConverter(jsonObject);
            String result = converter.getFieldAsString("name");
            assertEquals("Keykhosrow", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void jsonObjectToMapObject() {
        try {
            String stringJson = "{ id: 3001, name:  \"Keykhosrow\", email: \"info@atrvasa.com\"}";
            JsonConverter converter = new JsonConverter(stringJson);
            Map<String, Object> mappedObject = converter.jsonObjectToMapObject();
            String result = mappedObject.get("name").toString();
            assertEquals("Keykhosrow", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void getFieldAsEnumWithValue() {
//        try {
//            String stringJson = "{ name:  1 }";
//            JsonConverter converter = new JsonConverter(stringJson);
//            Type result = converter.getFieldAsEnum("name", Type.class);
//            assertEquals(Type.PDF, result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsEnumWithName() {
        try {
            String stringJson = "{ name:  \"PDF\"}";
            JsonConverter converter = new JsonConverter(stringJson);
            Type result = converter.getFieldAsEnum("name", Type.class);
            assertEquals(Type.PDF, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsUUID() {
        try {
            String stringJson = "{ name:  \"0f4ce83d-dfe0-4deb-a9a4-5464de53532f\"}";
            JsonConverter converter = new JsonConverter(stringJson);
            UUID result = converter.getFieldAsUUID("name");
            assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsDateInGeorgianWithLine() {
        try {
            String stringJson = "{ name:  \"2023-1-15\"}";
            JsonConverter converter = new JsonConverter(stringJson);
            Date result = converter.getFieldAsDate("name", "en");
            Calendar calendar = new GregorianCalendar(2023, Calendar.JANUARY, 15);
            assertEquals(calendar.getTimeInMillis(), result.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsDateInGeorgianWithSlash() {
        try {
            String stringJson = "{ name:  \"2023/1/15\"}";
            JsonConverter converter = new JsonConverter(stringJson);
            Date result = converter.getFieldAsDate("name", "en");
            Calendar calendar = new GregorianCalendar(2023, Calendar.JANUARY, 15);
            assertEquals(calendar.getTimeInMillis(), result.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsDateInPersianCulture() {
        try {
            String stringJson = "{ name:  \"1401/10/25\"}";
            JsonConverter converter = new JsonConverter(stringJson);
            Date result = converter.getFieldAsDate("name", "fa");
            Calendar calendar = new GregorianCalendar(2023, Calendar.JANUARY, 15);
            assertEquals(calendar.getTimeInMillis(), result.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsBoolean() {
        try {
            String stringJson = "{ name: true }";
            JsonConverter converter = new JsonConverter(stringJson);
            boolean result = converter.getFieldAsBoolean("name");
            assertNotEquals(false, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsLong() {
        try {
            String stringJson = "{ name: 123456789123456 }";
            JsonConverter converter = new JsonConverter(stringJson);
            long result = converter.getFieldAsLong("name");
            assertNotEquals(0, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsDouble() {
        try {
            String stringJson = "{ name: 123456789123456.123 }";
            JsonConverter converter = new JsonConverter(stringJson);
            double result = converter.getFieldAsDouble("name");
            assertNotEquals(0, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getFieldAsInt() {
        try {
            String stringJson = "{ name: 1 }";
            JsonConverter converter = new JsonConverter(stringJson);
            int result = converter.getFieldAsInt("name");
            assertNotEquals(0, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getElement() {
        JsonElement element;
        try {
            String stringJson = "{ name: \"Keykhosrow\" }";
            JsonConverter converter = new JsonConverter(stringJson);
            element = converter.getElement("name");
        } catch (Exception e) {
            element = null;
        }
        assertNotNull(element);
    }

    @Test
    void getFieldAsString() {
        try {
            String stringJson = "{ name: \"Keykhosrow\" }";
            JsonConverter converter = new JsonConverter(stringJson);
            String resultValue = converter.getFieldAsString("name");
            assertEquals("Keykhosrow", resultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void FieldKeyNullTest() {
        Aenah exception = null;
        try {
            String stringJson = "{ name: \"Keykhosrow\" }";
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsString(null);
        } catch (FieldKeyNullException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void FieldNotFoundTest() {
        Aenah exception = null;
        try {
            String stringJson = "{ name: \"Keykhosrow\" }";
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsString("id");
        } catch (FieldNotFoundException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void FieldNotJsonArrayTest() {
        Aenah exception = null;
        try {
            String stringJson = "{ name: \"Keykhosrow\" }";
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsJsonArray("name");
        } catch (FieldNotJsonArrayException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void FieldNotJsonObjectTest() {
        Aenah exception = null;
        try {
            String stringJson = "{ name: \"Keykhosrow\" }";
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsJsonObject("name");
        } catch (FieldNotJsonObjectException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void FieldValueNullTest() {
        Aenah exception = null;
        try {
            String stringJson = "{ name: null }";
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsString("name");
        } catch (FieldValueNullException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void JsonObjectIsNullTest() {
        Aenah exception = null;
        try {
            String stringJson = null;
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsString("name");
        } catch (JsonObjectIsNullException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void JsonParserTest() {
        Aenah exception = null;
        try {
            String stringJson = "--";
            JsonConverter converter = new JsonConverter(stringJson);
        } catch (JsonParserException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }

    @Test
    void ClassTypeIsNullExceptionTest() {
        Aenah exception = null;
        try {
            String stringJson = "{}";
            JsonConverter converter = new JsonConverter(stringJson);
            converter.getFieldAsModel("name", null);
        } catch (ClassTypeIsNullException e) {
            exception = e;
        } catch (Exception e) {
            System.out.println("Throw ...");
        }
        assertNotNull(exception);
    }
}