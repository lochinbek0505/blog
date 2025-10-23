package uz.falconmobile.blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
//
//    private final AuthanticationService  authanticationService;
//
//    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
//
//        authanticationService.authenticate()
//
//    }

}
