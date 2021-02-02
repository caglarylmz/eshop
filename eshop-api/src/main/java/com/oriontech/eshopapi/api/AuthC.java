package com.oriontech.eshopapi.api;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oriontech.eshopapi.auth.JwtTokenProvider;
import com.oriontech.eshopapi.enums.RoleName;
import com.oriontech.eshopapi.exceptions.AppException;
import com.oriontech.eshopapi.models.Role;
import com.oriontech.eshopapi.models.User;
import com.oriontech.eshopapi.payloads.req.LoginRequest;
import com.oriontech.eshopapi.payloads.req.SignUpRequest;
import com.oriontech.eshopapi.payloads.res.ApiResponse;
import com.oriontech.eshopapi.payloads.res.JwtAuthenticationResponse;
import com.oriontech.eshopapi.repository.RoleRepository;
import com.oriontech.eshopapi.repository.UserRepository;

//Authorization: Bearer <accessToken> ile api istekleri işlenebiir

//For Flutter
@RestController
@RequestMapping("/api/auth")
public class AuthC {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping(path = "/sign-in", consumes = { "application/x-www-form-urlencoded" })
    public ResponseEntity<?> authenticateUser(@RequestParam Map<String, String> params,
            @RequestHeader HttpHeaders httpHeaders) {

        System.out.println(params);
        String usernameOrEmail = params.get("usernameOrEmail");
        String password = params.get("password");
        LoginRequest loginRequest = new LoginRequest(usernameOrEmail, password);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping(path = "/sign-up", consumes = { "application/x-www-form-urlencoded" })
    public ResponseEntity<?> registerUser(@RequestParam Map<String, String> params,
            @RequestHeader HttpHeaders httpHeaders) {
        String fullname = params.get("fullname");
        String username = params.get("username");
        String email = params.get("email");
        String password = params.get("password");

        SignUpRequest signUpRequest = new SignUpRequest(fullname, username, email, password);
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    /*
     * @PostMapping(path = "/signin",consumes =
     * {"application/x-www-form-urlencoded"}) public ResponseEntity<?>
     * authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
     * 
     * Authentication authentication = authenticationManager .authenticate(new
     * UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
     * loginRequest.getPassword()));
     * 
     * SecurityContextHolder.getContext().setAuthentication(authentication);
     * 
     * String jwt = tokenProvider.generateToken(authentication); return
     * ResponseEntity.ok(new JwtAuthenticationResponse(jwt)); }
     * 
     * @SuppressWarnings({ "unchecked", "rawtypes" })
     * 
     * @PostMapping("/signup") public ResponseEntity<?>
     * registerUser(@Valid @RequestBody SignUpRequest signUpRequest) { if
     * (userRepository.existsByUsername(signUpRequest.getUsername())) { return new
     * ResponseEntity(new ApiResponse(false, "Username is already taken!"),
     * HttpStatus.BAD_REQUEST); }
     * 
     * if (userRepository.existsByEmail(signUpRequest.getEmail())) { return new
     * ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
     * HttpStatus.BAD_REQUEST); }
     * 
     * // Creating user's account User user = new User(signUpRequest.getName(),
     * signUpRequest.getUsername(), signUpRequest.getEmail(),
     * passwordEncoder.encode(signUpRequest.getPassword()));
     * 
     * Role userRole = roleRepository.findByName(RoleName.ROLE_USER) .orElseThrow(()
     * -> new AppException("User Role not set."));
     * 
     * user.setRoles(Collections.singleton(userRole));
     * 
     * User result = userRepository.save(user);
     * 
     * URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(
     * "/api/users/{username}") .buildAndExpand(result.getUsername()).toUri();
     * 
     * return ResponseEntity.created(location).body(new ApiResponse(true,
     * "User registered successfully")); }
     */

}
