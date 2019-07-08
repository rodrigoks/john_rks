package br.com.johndeere.vos;

import java.util.List;

import com.google.gson.annotations.Expose;

public class PeopleVO {

	@Expose
	private String name;
	@Expose
	private String height;
	@Expose
	private String mass;
	@Expose
	private String hair_color;
	@Expose
	private String skin_color;
	@Expose
	private String eye_color;
	@Expose
	private String birth_year;
	@Expose
	private String gender;
	@Expose
	private String homeworld;
	@Expose
	private List<String> films;
	@Expose
	private List<String> species;
	@Expose
	private List<String> vehicles;
	@Expose
	private List<String> starships;
	@Expose
	private String created;
	@Expose
	private String edited;
	@Expose
	private String url;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getHair_color() {
		return hair_color;
	}
	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}
	public String getSkin_color() {
		return skin_color;
	}
	public void setSkin_color(String skin_color) {
		this.skin_color = skin_color;
	}
	public String getEye_color() {
		return eye_color;
	}
	public void setEye_color(String eye_color) {
		this.eye_color = eye_color;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHomeworld() {
		return homeworld;
	}
	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}
	public List<String> getFilms() {
		return films;
	}
	public void setFilms(List<String> films) {
		this.films = films;
	}
	public List<String> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}
	public List<String> getStarships() {
		return starships;
	}
	public void setStarships(List<String> starships) {
		this.starships = starships;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getEdited() {
		return edited;
	}
	public void setEdited(String edited) {
		this.edited = edited;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMass() {
		return mass;
	}
	public void setMass(String mass) {
		this.mass = mass;
	}
	public List<String> getSpecies() {
		return species;
	}
	public void setSpecies(List<String> species) {
		this.species = species;
	}
	
}
