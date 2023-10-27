package com.ksolves.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksolves.entities.Location;
import com.ksolves.entities.Store;
import com.ksolves.redis.annotations.RedisOneToOne;
import jakarta.persistence.Id;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CustomTest {
    ObjectMapper mapper;
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
//        String jsonString = "{\"name\":\"Smart bazaar\",\"location\":\"1\",\"id\":\"1\"}";
//        System.out.println(jsonString.replaceAll("[{}\\\"]", ""));
        Class<?> clazz = Class.forName("com.ksolves.entities.Store");
//        for (Method method: clazz.getDeclaredMethods()) {
//            System.out.println(clazz.getDeclaredMethod(method.getName(), method.getParameterTypes()));
////            System.out.println(method.getName());
//        }
        for (Field field: clazz.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getType());
            System.out.println(!field.getType().isPrimitive() && !field.getType().getName().startsWith("java."));
        }
    }

    void stuff() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.ksolves.entities.Store");
        System.out.println(clazz.isAnnotationPresent(RedisOneToOne.class));
        System.out.println();
        Field field = clazz.getDeclaredField("location");
        field.setAccessible(true);
//        for(Field field : clazz.getDeclaredFields()){
        System.out.println(field.get(Store.class));
        Class ob = field.getClass();
//            System.out.println(field.getType().getDeclaredFields());
//            System.out.println(field.getType());
//            if(field.isAnnotationPresent(RedisOneToOne.class)){
        System.out.println(ob.toString());
        RedisOneToOne rd = field.getDeclaredAnnotation(RedisOneToOne.class);
        System.out.println(rd.key());
//            }
//        }
    }
    public String getJsonString(Object obj) throws IllegalAccessException, JsonProcessingException {
        System.out.println("============Mapping Serialization============");
        Class<?> clazz = obj.getClass();
        Map<String, Object> jsonElementsMap = new HashMap<>();
        for(Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(RedisOneToOne.class)) {

                System.out.println("Field name " + field.getName());
                String key = field.getDeclaredAnnotation(RedisOneToOne.class).key();
                Object fieldObject = field.get(obj);

                Field field2 = Arrays.stream(fieldObject.getClass().getDeclaredFields())
                        .filter(field1 -> field1.isAnnotationPresent(Id.class)).findFirst().get();
                field2.setAccessible(true);
                Object hashkey = field2.get(fieldObject);
                jsonElementsMap.put(field.getName(), hashkey);

//				for(Field field2 : fieldObject.getClass().getDeclaredFields()){
//					if(field2.isAnnotationPresent(Id.class)){
//						field2.setAccessible(true);
//						hashkey = field2.get(fieldObject);
//						jsonElementsMap.put(field.getName(), hashkey);
//						break;
//					}
//				}

                RedisMappingOperations redisMappingOperations =
                        new RedisMappingOperations(key, (Long) hashkey, fieldObject, mapper);
                redisMappingOperations.saveData();

            }else {
                jsonElementsMap.put(field.getName(), field.get(obj));
            }
        }
//		String jsonString = jsonElementsMap.entrySet()
//				.stream()
//				.map(entry -> "\"" + entry.getKey() + "\":\""
//						+ entry.getValue() + "\"")
//				.collect(Collectors.joining(","));

        return mapper.writeValueAsString(jsonElementsMap);
    }
//    public T getObject(byte[] bytes, Class<T> type) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
//        System.out.println("============Mapping Deserialization============");
////		String jsonString = new String(bytes);
////		System.out.println("jsonString : " + jsonString);
//        T node = type.getDeclaredConstructor().newInstance();
////		Map<String, String> jsonElementsMap = new HashMap<>();
////		Arrays.stream(jsonString.replaceAll("[{}\\\"]", "").split(","))
////				.forEach(entry->{
////					String[] value = entry.split(":");
////					jsonElementsMap.put(value[0], value[1]);
////				});
//
//        Map<String, Object> jsonElementsMap = mapper.readValue(bytes, HashMap.class);
//
//        for (Field field : type.getDeclaredFields()) {
////			Method setField = node.getClass().getDeclaredMethod("set"+ StringUtils.capitalize(field.getName()), field.getType());
////			setField.setAccessible(true);
//            field.setAccessible(true);
//
//            if(field.isAnnotationPresent(RedisOneToOne.class)) {
//                String key = field.getDeclaredAnnotation(RedisOneToOne.class).key();
//                Object hashkey = jsonElementsMap.get(field.getName());
//                Object fieldObject = field.getType().getDeclaredConstructor().newInstance();
//
//                RedisMappingOperations redisMappingOperations =
//                        new RedisMappingOperations(key, Long.parseLong(String.valueOf(hashkey)), fieldObject, mapper);
////				System.out.println("location : " + redisMappingOperations.getData());
////				setField.invoke(node, field.getType().cast(redisMappingOperations.getData()));
//                field.set(node, redisMappingOperations.getData());
//            }else {
////				String fieldName = field.getType().getSimpleName();
//                switch(field.getType().getSimpleName()){
//                    case "Long" :
////						setField.invoke(node, Long.valueOf(
////							String.valueOf(jsonElementsMap.get(field.getName()))));
//                        field.set(node, Long.parseLong(String.valueOf(jsonElementsMap.get(field.getName()))));
//                        break;
//                    case "Integer" :
////						setField.invoke(node, Integer.valueOf(
////							String.valueOf(jsonElementsMap.get(field.getName()))));
//                        field.set(node, Integer.parseInt(String.valueOf(jsonElementsMap.get(field.getName()))));
//                        break;
//                    default:
////						setField.invoke(node, jsonElementsMap.get(field.getName()));
//                        field.set(node, jsonElementsMap.get(field.getName()));
//                        break;
//                }
////				field.set(node, jsonElementsMap.get(field.getName()));
////				setField.invoke(node, field.getType().getClass().cast(jsonElementsMap.get(field.getName())));
////				setField.invoke(node, field.getType().isAssignableFrom(String.class)
////						? jsonElementsMap.get(field.getName())
////						: castStringToFieldType(field.getType(), jsonElementsMap.get(field.getName())));
//            }
//        }
//        return node;
//    }

    public Object castStringToFieldType(Class<?> type, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method castMethod = type.getMethod("valueOf", Object.class);
        return castMethod.invoke(null, (String) value);
    }
}
