package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Retain at runtime so we can check it with reflection
@Target(ElementType.FIELD) //Only for fields
public @interface Nullable {
    String key() default "Field is null";
}
