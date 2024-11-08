package org.example.annotations;

import java.lang.reflect.Field;

public class AnnotaionProcessor {
    public static void checkNullValues(Object object) { // Can be used for any class
        Class<?> objectClass = object.getClass();

        // Iterate over each field in the class
        for (Field field : objectClass.getDeclaredFields()) {
            // Check if the field has the @Nullable annotation
            if (field.isAnnotationPresent(Nullable.class)) {
                Nullable nullableAnnotation = field.getAnnotation(Nullable.class);
                field.setAccessible(true); // Allow access to private fields

                try {
                    // Check if the field is null
                    if (field.get(object) == null) {
                        System.out.println("WARNING: " + nullableAnnotation.key() + ", field is null in " + objectClass.getSimpleName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
