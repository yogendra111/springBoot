package com.demoboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demoboot.entities.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		
//		Consumer con = new Consumer().setId(10).setName("Ravi");
//		System.out.println(con);
		
//		ObjectMapper mapper = new ObjectMapper();
//		Student stud = new Student(1, "Rohan", "BCA", "729866738");
//		try {
//			String str = mapper.writeValueAsString(stud); //serialization
//		System.out.println("Student JSON: " + str);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		
//		String json = "{ \"id\":3, \"name\":\"Ramesh\", \"course\":\"MCA\", \"mobileNo\":\"907336523\" }";
//		try {
//			Student stu = mapper.readValue(json, Student.class); //de-serialization
//			System.out.println(stu);
//		} catch (JsonMappingException e) {
//
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
