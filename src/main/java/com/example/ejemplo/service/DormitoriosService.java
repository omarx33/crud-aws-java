package com.example.ejemplo.service;

import com.example.ejemplo.dto.DormitoriosRequest;
import com.example.ejemplo.dto.DormitoriosResponse;

import java.util.List;

public interface DormitoriosService {

    DormitoriosResponse createDormitorio(DormitoriosRequest request);

    DormitoriosResponse updateDormitorio(Long id, DormitoriosRequest request);

    DormitoriosResponse getDormitorioById(Long id);

    void deleteDormitorio(Long id);

    List<DormitoriosResponse> getAllDormitorios();

}
