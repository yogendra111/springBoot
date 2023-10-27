package com.ksolves.redis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.ksolves.exceptionhandler.RedisTypeRecommendationException;
import com.ksolves.redis.annotations.RedisOneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomRedisSerializer<T> implements RedisSerializer<T> {

	ObjectMapper mapper;
	Class<T> type;
	Boolean isContainMapping;

	public Boolean initialCheck(){
		for(Field field : type.getDeclaredFields()) {
			if(field.isAnnotationPresent(OneToOne.class)){
				if(!field.isAnnotationPresent(RedisOneToOne.class)) {
					throw new RedisTypeRecommendationException(
							"Could not determine recommended JdbcType for " + field.getType()
					);
				}
				return true;
			}
		}
		return false;
	}

	public CustomRedisSerializer(ObjectMapper mapper, Class<T> type) {
		this.mapper = mapper;
		this.type = type;
		this.isContainMapping = initialCheck();
	}

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		System.out.println("Contain mapping : " + isContainMapping);
		String value = "";
		try {
			if(isContainMapping){
				value = getJsonString(t);
			}else{
				value = mapper.writeValueAsString(t);
			}
			System.out.println("To Redis (JSON) ===> " + value);
			return value.getBytes();
		} catch (JsonProcessingException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
    }

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		System.out.println("Contain mapping : " + isContainMapping);
		T node = null;
		try {
			if(isContainMapping){
				node = getObject(bytes, type);
			}else{
				node = mapper.readValue(bytes,type);
			}
			System.out.println("From redis (Object) ===> " + node);
			return node;
		} catch (IOException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {
			e.printStackTrace();
			return null;
		}
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

				RedisMappingOperations redisMappingOperations =
						new RedisMappingOperations(key, (Long) hashkey, fieldObject, mapper);
				redisMappingOperations.saveData();

			}else {
				jsonElementsMap.put(field.getName(), field.get(obj));
			}
		}

		return mapper.writeValueAsString(jsonElementsMap);
	}
	public T getObject(byte[] bytes, Class<T> type) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
		System.out.println("============Mapping Deserialization============");
		T node = type.getDeclaredConstructor().newInstance();

		Map<String, Object> jsonElementsMap = mapper.readValue(bytes, HashMap.class);

		for (Field field : type.getDeclaredFields()) {
//			Method setField = node.getClass().getDeclaredMethod("set"+ StringUtils.capitalize(field.getName()), field.getType());
//			setField.setAccessible(true);
			field.setAccessible(true);

			if(field.isAnnotationPresent(RedisOneToOne.class)) {
				String key = field.getDeclaredAnnotation(RedisOneToOne.class).key();
				Object hashkey = jsonElementsMap.get(field.getName());
				Object fieldObject = field.getType().getDeclaredConstructor().newInstance();

				RedisMappingOperations redisMappingOperations =
						new RedisMappingOperations(key, Long.parseLong(String.valueOf(hashkey)), fieldObject, mapper);
//				System.out.println("location : " + redisMappingOperations.getData());
//				setField.invoke(node, field.getType().cast(redisMappingOperations.getData()));
				field.set(node, redisMappingOperations.getData());
			}else {
//				String fieldName = field.getType().getSimpleName();
				switch(field.getType().getSimpleName()){
					case "Long" :
//						setField.invoke(node, Long.valueOf(
//							String.valueOf(jsonElementsMap.get(field.getName()))));
						field.set(node, Long.parseLong(String.valueOf(jsonElementsMap.get(field.getName()))));
						break;
					case "Integer" :
//						setField.invoke(node, Integer.valueOf(
//							String.valueOf(jsonElementsMap.get(field.getName()))));
						field.set(node, Integer.parseInt(String.valueOf(jsonElementsMap.get(field.getName()))));
						break;
					default:
//						setField.invoke(node, jsonElementsMap.get(field.getName()));
						field.set(node, jsonElementsMap.get(field.getName()));
						break;
				}
			}
		}
		return node;
	}

	public Object castStringToFieldType(Class<?> type, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method castMethod = type.getMethod("valueOf", Object.class);
		return castMethod.invoke(null, (String) value);
	}

}
