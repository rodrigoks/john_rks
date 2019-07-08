package br.com.johndeere.vos;

import java.util.List;

import com.google.gson.annotations.Expose;

public class MovieVO {

	@Expose
	private String title;
	@Expose
	private String episode_id;
	@Expose
	private String opening_crawl;
	@Expose
	private String director;
	@Expose
	private String producer;
	@Expose
	private String releaseDate;
	@Expose
	private List<String> characters;
	@Expose
	private List<String> planets;
	@Expose
	private List<String> starships;
	@Expose
	private List<String> vehicles;
	@Expose
	private List<String> species;
	@Expose
	private String created;
	@Expose
	private String edited;
	@Expose
	private String url;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEpisode_id() {
		return episode_id;
	}
	public void setEpisode_id(String episode_id) {
		this.episode_id = episode_id;
	}
	public String getOpening_crawl() {
		return opening_crawl;
	}
	public void setOpening_crawl(String opening_crawl) {
		this.opening_crawl = opening_crawl;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<String> getCharacters() {
		return characters;
	}
	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}
	public List<String> getPlanets() {
		return planets;
	}
	public void setPlanets(List<String> planets) {
		this.planets = planets;
	}
	public List<String> getStarships() {
		return starships;
	}
	public void setStarships(List<String> starships) {
		this.starships = starships;
	}
	public List<String> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}
	public List<String> getSpecies() {
		return species;
	}
	public void setSpecies(List<String> species) {
		this.species = species;
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
	
}
