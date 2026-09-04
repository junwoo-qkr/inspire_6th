package com.example.mybatispractice.features.users.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatispractice.features.users.domain.DTO.UserRequestDTO;
import com.example.mybatispractice.features.users.domain.DTO.UserResponseDTO;
import com.example.mybatispractice.features.users.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "User API", description = "사용자 회원가입과 로그인 관련 API 명세서")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // SignUp
    @Operation(summary = "회원가입", description = "신규가입(email, password, name)")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "201", description = "가입 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패"),
            @ApiResponse(responseCode = "500", description = "가입 실패"),
        }
    )
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "사용자 가입 정보를 담는 DTO",
            required = true,
            content =  @Content(
                schema = @Schema(implementation = UserRequestDTO.class)
            )
        )
        @Valid @RequestBody UserRequestDTO request, BindingResult bindingResult) {
        System.out.println("signUp / params : " + request);

        if (bindingResult.hasErrors()) {
            System.out.println("usercontroller signup validation error");
            bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .forEach(System.out::println);

            Map<String, String> errMap = new HashMap<>();
            bindingResult.getAllErrors().stream()
                .forEach(err -> {
                    FieldError field = (FieldError)err;
                    String message = err.getDefaultMessage();
                    errMap.put(field.getField(), message);
                });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMap);
        }

        int signUpFlag = userService.signUp(request);
        if (signUpFlag != 0) {
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
        } else {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    // signIn
    @Operation(summary = "로그인", description = "사용자 로그인(email, password)")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "로그인 인증 오류"),
        }
    )
    @GetMapping("/signIn")
    public ResponseEntity<?> signIn(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "사용자 로그인 정보를 담는 DTO",
            required = true,
            content =  @Content(
                schema = @Schema(implementation = UserRequestDTO.class)
            )
        )
        @RequestParam("email") String email,
        @RequestParam("password") String password) {
            System.out.println("signUp / params : " + email + " \t " + password);
            Map<String, Object> map = userService.signIn(UserRequestDTO.builder()
                .email(email)
                .password(password)
                .build());
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", (String)map.get("at"));
            headers.add("Refresh-Token", (String)map.get("rt"));
            headers.add("Access-Control-Expose-Headers", "Authorization, Refresh-Token");

            return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body((UserResponseDTO)map.get("response"));
        }
}