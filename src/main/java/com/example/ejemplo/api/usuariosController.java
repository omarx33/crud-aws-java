package com.example.ejemplo.api;

import com.example.ejemplo.dto.UserRequest;
import com.example.ejemplo.dto.UserResponse;
import com.example.ejemplo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

@RequiredArgsConstructor
public class usuariosController {

    private final UserService userService;
//    @GetMapping("/hola")
//    public String holaMundo() {
//        return "Hola Mundo";
//    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {

        UserResponse userResponse = userService.createUser(request);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser( @PathVariable Long id,@Valid @RequestBody UserRequest request) {

        UserResponse userResponse = userService.updateUser(id, request);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        UserResponse userResponse = userService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

            List<UserResponse> userResponseList = userService.getAllUsers();

            return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
