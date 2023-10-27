package app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "ITRequest")
public class Complain {
	
	@Column(name = "complainId")
	private String ComplainId;
	
	@Column(name = "empId")
	private String EmpId;
	
	@Column(name = "category")
	private String Category;
	
	@Column(name = "complain")
	private String Complain;
	
	@Lob
	@Column(name = "description", columnDefinition = "CLOB")
	private String ComplainDesc;
}
