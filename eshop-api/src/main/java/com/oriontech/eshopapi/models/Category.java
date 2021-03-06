package com.oriontech.eshopapi.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private boolean status;
	private String icon;

	@ManyToOne()
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private Category parentCategory;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCategory")
	private List<Category> subCategories = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Advert> adverts = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Tip> tips = new ArrayList<Tip>();

	@PrePersist
	protected void prePersist() {
		this.status=true;
		this.icon="no-icon.png";

	}

	public Category(String name) {
		super();
		this.name = name;
		this.status=true;
		this.parentCategory=null;
	}
	
	
	
}
