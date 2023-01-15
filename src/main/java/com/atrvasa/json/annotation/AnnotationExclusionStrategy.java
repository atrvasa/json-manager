package com.atrvasa.json.annotation;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * to exclude fields in converting objects to JSON
 */
public class AnnotationExclusionStrategy implements ExclusionStrategy {
    public boolean shouldSkipField(FieldAttributes f) {
        return (
                f.getAnnotation(Exclude.class) != null
        );
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}