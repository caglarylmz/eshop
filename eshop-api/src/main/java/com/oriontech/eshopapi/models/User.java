package com.oriontech.eshopapi.models;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email" }))
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	private  String name;

	@NotBlank
	@Size(max = 15)
	private  String username;

	
	@NotBlank
	@Size(max = 40)
	@Email
	private  String email;

	@JsonIgnore
	@NotBlank
	@Size(max = 100)
	private  String password;
	
	private boolean status;
	@Column(name="profil_image" ,length = 1024 )
	private String profileImage;
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phone;
	
	private String adressDetail;
	private String city;
	private String town;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	List<Advert> adverts = new ArrayList<Advert>();

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy : hh-ss")
	private Date createdAt;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date updatedAt;
	
	@PrePersist
	protected void prePersist() {
		if (this.createdAt == null)
			createdAt = new Date();
		if (this.updatedAt == null)
			updatedAt = new Date();
		this.status=true;

	}

	@PreUpdate
	protected void preUpdate() {
		this.updatedAt = new Date();
	}
	
	public User(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 15) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status=true;
	}
	
	//for update for  unchanged password
	public User(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 15) String username,
			@NotBlank @Size(max = 40) @Email String email) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.status=true;
	}


}
