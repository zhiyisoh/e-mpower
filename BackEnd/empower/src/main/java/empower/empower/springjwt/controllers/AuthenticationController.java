package empower.empower.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
    }
    