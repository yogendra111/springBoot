package com.demoboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //It will not return those fields that are empty
public class BookModel {

	@JsonProperty("b_id")
//	@JsonIgnore   //It will ignore 
	private Integer id;
	
	@JsonProperty("b_name")
	private String name;
	
	@JsonProperty("b_author")
	private String author;
	
	@JsonProperty("b_price")
	private Double price;
	
}
