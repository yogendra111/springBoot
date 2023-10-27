package com.ksolves.redis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * This annotation used at RUNTIME and only one Field
 * It contains one argument i.e. key
 * Now we’re going to take advantage of this by using Java’s Reflection API
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RedisOneToMany {
    public String key();
}
