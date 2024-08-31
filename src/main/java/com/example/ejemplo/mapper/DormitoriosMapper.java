package com.example.ejemplo.mapper;

import com.example.ejemplo.dto.DormitoriosRequest;
import com.example.ejemplo.dto.DormitoriosResponse;
import com.example.ejemplo.model.Dormitorios;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DormitoriosMapper {

    private final ModelMapper modelMapper;


    public DormitoriosMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Dormitorios dormitoriosRequestToDormitorios(DormitoriosRequest request){
        return modelMapper.map(request, Dormitorios.class);
    }

    public DormitoriosResponse dormitoriosToDormitoriosResponse(Dormitorios request){
        return modelMapper.map(request, DormitoriosResponse.class);
    }
}
