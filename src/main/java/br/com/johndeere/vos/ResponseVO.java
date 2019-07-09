package br.com.johndeere.vos;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.annotations.Expose;

public class ResponseVO {
	
	@Expose
	private MovieVO movie;
	@Expose
	private PeopleVO people;
	@Expose
	private SpecieVO specie;
	@Expose
	private Collection<PeopleVO> colPeople;
	
	public ResponseVO(
			MovieVO movie, 
			PeopleVO people, 
			SpecieVO specie, 
			Collection<PeopleVO> colPeople) {
		this.movie = movie;
		this.people = people;
		this.specie = specie;
		this.colPeople = colPeople;
	}
	
	public MovieVO getMovie() {
		return movie;
	}
	public void setMovie(MovieVO movie) {
		this.movie = movie;
	}
	public PeopleVO getPeople() {
		return people;
	}
	public void setPeople(PeopleVO people) {
		this.people = people;
	}
	public SpecieVO getSpecie() {
		return specie;
	}
	public void setSpecie(SpecieVO specie) {
		this.specie = specie;
	}
	public Collection<PeopleVO> getColPeople() {
		if(colPeople == null)
			colPeople = new ArrayList<PeopleVO>();
		return colPeople;
	}
	public void setColPeople(Collection<PeopleVO> colPeople) {
		this.colPeople = colPeople;
	}

}
