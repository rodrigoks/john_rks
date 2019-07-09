package br.com.johndeere.vos;

import com.google.gson.annotations.Expose;

public class SpecieVO {

	@Expose
	private String name;
	@Expose
	private String classification;
	@Expose
	private String designation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
