package uz.falconmobile.blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.falconmobile.blog.domain.dtos.AuthResponse;
import uz.falconmobile.blog.domain.dtos.LoginRequest;
import uz.falconmobile.blog.services.AuthanticationService;

@RestController
@RequestMapping("v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthanticationService  authanticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {

        UserDetails userDetails = authanticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authanticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .expiresIn(86400L)
                .build();

        return ResponseEntity.ok(authResponse);

    }

}
