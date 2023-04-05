package com.examportal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qId;

	private String title;
	
	@Column(length = 5000)
	private String description;

	private String maxmarks;

	private String numberofquestions;

	private boolean active = true;

	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Questions> questions = new HashSet<>();

	public Quiz() {
		super();

	}

	public Quiz(Long qId, String title, String description, String maxmarks, String numberofquestions, boolean active,
			Category category, Set<Questions> questions) {
		super();
		this.qId = qId;
		this.title = title;
		this.description = description;
		this.maxmarks = maxmarks;
		this.numberofquestions = numberofquestions;
		this.active = active;
		this.category = category;
		this.questions = questions;
	}

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxmarks() {
		return maxmarks;
	}

	public void setMaxmarks(String maxmarks) {
		this.maxmarks = maxmarks;
	}

	public String getNumberofquestions() {
		return numberofquestions;
	}

	public void setNumberofquestions(String numberofquestions) {
		this.numberofquestions = numberofquestions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quiz [qId=" + qId + ", title=" + title + ", description=" + description + ", maxmarks=" + maxmarks
				+ ", numberofquestions=" + numberofquestions + ", active=" + active + ", category=" + category
				+ ", questions=" + questions + "]";
	}

	

}
