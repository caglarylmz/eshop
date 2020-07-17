package com.oriontech.eshopapi.payloads.res;


import java.util.Date;
import java.util.List;
import java.util.Set;

import com.oriontech.eshopapi.models.Advert;
import com.oriontech.eshopapi.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

	private String name;
	private String username;
	private String email;
	private String password;
	private String phone;
	private String imagePath;
	private String adressDetail;
	private String city;
	private String town;	
	private Date createdAt;
	private Date updatedAt;
	private Set<Role> roles;
	List<Advert> adverts;

	

}