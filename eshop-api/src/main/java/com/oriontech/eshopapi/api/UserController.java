package com.oriontech.eshopapi.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.oriontech.eshopapi.models.User;
import com.oriontech.eshopapi.payloads.req.UserProfileRequest;
import com.oriontech.eshopapi.payloads.res.ApiResponse;
import com.oriontech.eshopapi.payloads.res.UserProfileResponse;
import com.oriontech.eshopapi.repository.RoleRepository;
import com.oriontech.eshopapi.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController implements ServletContextAware {
	private ServletContext servletContext;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@GetMapping(value = "/profile")
	public UserProfileResponse getProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = userRepository.findByUsername(authentication.getName()).get();
		UserProfileResponse userProfileResponse = new UserProfileResponse();

		userProfileResponse.setName(activeUser.getName());
		userProfileResponse.setUsername(activeUser.getUsername());
		userProfileResponse.setEmail(activeUser.getEmail());
		userProfileResponse.setPhone(activeUser.getPhone());
		userProfileResponse.setImagePath(activeUser.getProfileImage());
		userProfileResponse.setAdressDetail(activeUser.getAdressDetail());
		userProfileResponse.setTown(activeUser.getTown());
		userProfileResponse.setCity(activeUser.getCity());
		userProfileResponse.setCreatedAt(activeUser.getCreatedAt());
		userProfileResponse.setRoles(activeUser.getRoles());
		userProfileResponse.setAdverts(activeUser.getAdverts());
		return userProfileResponse;
	}

	@PutMapping(value = "/profile")
	public ResponseEntity<?> updateProfile(@Valid @RequestBody UserProfileRequest userProfileRequest) {

		User user = userRepository.findByUsername(userProfileRequest.getUsername()).get();
		if (!user.getEmail().equals(userProfileRequest.getEmail())) {
			if (!userRepository.existsByEmail(userProfileRequest.getEmail())) {
				user.setEmail(userProfileRequest.getEmail());
			} else {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Email is already taken!"),
						HttpStatus.BAD_REQUEST);
			}
		}

		user.setName(userProfileRequest.getName());
		user.setPassword(encoder.encode(userProfileRequest.getPassword()));
		user.setPhone(userProfileRequest.getPhone());
		user.setProfileImage(userProfileRequest.getImagePath());
		user.setAdressDetail(userProfileRequest.getAdressDetail());
		user.setCity(userProfileRequest.getCity());
		user.setTown(userProfileRequest.getTown());

		return ResponseEntity.ok(new ApiResponse(true, "User profile updated"));
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	private String uploadFile(MultipartFile multipartFile) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy-hhmmss");
		Date date = new Date();
		try {

			byte[] bytes = multipartFile.getBytes();
			String[] split = multipartFile.getOriginalFilename().split("\\.");
			String ext = split[split.length - 1];
			Path path = Paths
					.get(servletContext.getRealPath("/uploads/profile-images/" + dateFormat.format(date) + "." + ext));
			// Path path = Paths.get(servletContext.getRealPath("/upload/images/adverts/" ))
			Files.write(path, bytes);
			return path.getFileName().toString();
		} catch (Exception e) {
			return "avatar.jpg";
		}

	}

	private boolean deleteFile(String fileName) throws IOException {
		Path fileToDeletePath = Paths.get(servletContext.getRealPath("/uploads/profile-images/" + fileName));
		return Files.deleteIfExists(fileToDeletePath);

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

}
