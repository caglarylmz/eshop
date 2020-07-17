package com.oriontech.eshopapi.payloads.req;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {
	@NotBlank
	@Size(max = 40)
	private String name;
	@NotBlank
	@Size(max = 15)
	private String username;
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;
	@NotBlank
	private String password;
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phone;
	private String imagePath;
	private String adressDetail;
	private String city;
	private String town;	


	

}