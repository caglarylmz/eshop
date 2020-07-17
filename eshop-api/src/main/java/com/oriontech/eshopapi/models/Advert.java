package com.oriontech.eshopapi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Advert {
	@Id
	private UUID id=UUID.randomUUID();
	private String baslik;
	@Lob
	private String aciklama;
	@NotNull
	private int toplamAdet;
	@NotNull
	private double toplamFiyat;

	private String kimden;
	private boolean topluSatis;
	private boolean status;
	private boolean featured;
	private boolean showcase;
	private boolean different;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date updatedAt;

	@OneToOne
	@JoinColumn(name = "address_id")
	private AdvertAddress address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "advert", cascade = CascadeType.ALL)
	private List<AdvertDetail> advertDetails = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "advert", cascade = CascadeType.ALL)
	private List<Photo> photos = new ArrayList<Photo>();

	/* İlanın ne zaman oluşturulduğu veya update edildiği bilgisini saklıyoruz */
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

	public Advert(String baslik, String aciklama) {
		this.baslik = baslik;
		this.aciklama = aciklama;
		this.status = true;
	}

	public Advert(String baslik, String aciklama, boolean different) {
		this.baslik = baslik;
		this.aciklama = aciklama;
		this.different = different;
	}

}
