package com.example.ejemplo.api;

import com.example.ejemplo.dto.DormitoriosRequest;
import com.example.ejemplo.dto.DormitoriosResponse;
import com.example.ejemplo.service.DormitoriosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dormitorios")

@RequiredArgsConstructor
public class DormitoriosController {

    private final DormitoriosService dormitoriosService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')") //debe estar habilitado en security el enablemethodsecurity, en la tabla roles esta con "ROLE_"

    @PostMapping
    public ResponseEntity<DormitoriosResponse> createDormitorio(@Valid @RequestBody DormitoriosRequest request) {

        DormitoriosResponse dormitoriosResponse = dormitoriosService.createDormitorio(request);

        return new ResponseEntity<>(dormitoriosResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DormitoriosResponse> getDormitorioById(@PathVariable Long id) {

        DormitoriosResponse dormitoriosResponse = dormitoriosService.getDormitorioById(id);

        return new ResponseEntity<>(dormitoriosResponse, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<DormitoriosResponse>> getAllDormitorios() {

            List<DormitoriosResponse> dormitoriosResponseList = dormitoriosService.getAllDormitorios();

            return new ResponseEntity<>(dormitoriosResponseList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')") //solo puede eliminar el admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDormitorio(@PathVariable Long id) {
        dormitoriosService.deleteDormitorio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
