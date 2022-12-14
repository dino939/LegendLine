package ru.hld.legendline.api.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface EventTarget {
    byte value() default 2;
}
