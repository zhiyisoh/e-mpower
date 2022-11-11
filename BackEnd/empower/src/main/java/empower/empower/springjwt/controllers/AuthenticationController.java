package empower.empower.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import empower.empower.springjwt.models.ERole;
import empower.empower.springjwt.models.Role;
import empower.empower.springjwt.models.User;
import empower.empower.springjwt.payloads.request.SignUpRequest;
import empower.empower.springjwt.payloads.request.LoginRequest;
import empower.empower.springjwt.payloads.response.JwtResponse;
import empower.empower.springjwt.payloads.response.MessageResponse;
import empower.empower.springjwt.repository.RoleRepository;
import empower.empower.springjwt.repository.UserRepository;
import empower.empower.springjwt.security.jwt.JwtUtils;
import empower.empower.springjwt.security.service.UserDetailsImpl;
import empower.empower.springjwt.security.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
        @Autowired
        AuthenticationManager authenticationManager;
    
        @Autowired
        UserRepository userRepository;
    
        @Autowired
        RoleRepository roleRepository;
    
        @Autowired
        PasswordEncoder encoder;
    
        @Autowired
        JwtUtils jwtUtils;

        @Autowired
        UserDetailsServiceImpl userService;
    
        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            //security context holder after successfully validating user
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
    
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
    
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }
    
        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }
    
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already taken. Did you forget your password?"));
            }
    
            // Create new user's account
            User user = new User(signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));
            Set<String> strRole = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if(strRole == null){
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: User Role is not found."));
                roles.add(userRole);
            } else {

                //for each of the roles stated , add the role accordingly
                strRole.forEach(role -> {
                        switch (role) {
                        case "admin":
                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                                .orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));
                                roles.add(adminRole);
                                break;
                        default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                                .orElseThrow(() -> new RuntimeException("Error: User Role is not found."));
                                roles.add(userRole);
                        }
                });


            }
                
    
            user.setRoles(roles);
            userRepository.save(user);
    
            return ResponseEntity.ok(new MessageResponse("You have registered successfully! Please proceed to login."));
        }

        @PutMapping("/editprofile/{id}")
        public ResponseEntity<?> editProfile(@PathVariable(value = "id") Long id, @Valid @RequestBody SignUpRequest signUpRequest) {
                
                Optional<User> optionalCurrUser = userRepository.findById(id);
                User currUser = null;

                boolean unameResult = userRepository.existsByUsername(signUpRequest.getUsername());
                boolean emailResult = userRepository.existsByEmail(signUpRequest.getEmail());

                if(optionalCurrUser.isPresent()){
                        currUser = optionalCurrUser.get();
                }
                
                String oldUsername = "";
                String oldEmail = "";

                if(currUser != null){
                        oldUsername = currUser.getUsername();
                        oldEmail = currUser.getEmail();
                }else{
                        return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: User not found"));
                }


                if (unameResult && !oldUsername.equals(signUpRequest.getUsername())) {
                        return ResponseEntity
                                .badRequest()
                                .body(new MessageResponse("Error: Username is already taken!"));
                    }
            
                if (emailResult && !oldEmail.equals(signUpRequest.getEmail())) {
                        return ResponseEntity
                                .badRequest()
                                .body(new MessageResponse("Error: Email is already taken. Did you forget your password?"));
                    }


                return optionalCurrUser
                .map(oldAccData ->{
                        oldAccData.setEmail(signUpRequest.getEmail());
                        oldAccData.setPassword(encoder.encode(signUpRequest.getPassword()));
                        oldAccData.setUsername(signUpRequest.getUsername());
                        userService.saveUser(oldAccData);
                        return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("Error: Something went wrong"));
                
        }

        //gets information of a user for profile displaying
        @GetMapping("/profile/{id}")
        public ResponseEntity<User> get(@PathVariable Long id) {
        try {
            User u = userService.getUser(id);
            return new ResponseEntity<User>(u, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    }
    