package com.example.ejemplo.service.impl;

import com.example.ejemplo.dto.DormitoriosRequest;
import com.example.ejemplo.dto.DormitoriosResponse;
import com.example.ejemplo.exception.AssignmentAlreadyExistsException;
import com.example.ejemplo.exception.ResourceNotFoundException;
import com.example.ejemplo.mapper.DormitoriosMapper;
import com.example.ejemplo.model.Dormitorios;
import com.example.ejemplo.model.Usuarios;
import com.example.ejemplo.repository.DormitoriosRepository;
import com.example.ejemplo.repository.UsuarioRepository;
import com.example.ejemplo.service.DormitoriosService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DormitoriosImpl implements DormitoriosService {

    private final DormitoriosMapper dormitoriosMapper;

    private final DormitoriosRepository dormitoriosRepository;

    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public DormitoriosResponse createDormitorio(DormitoriosRequest request) {

        Optional<Dormitorios> dormitorios = dormitoriosRepository.findByPisoAndNumero(request.getPiso(), request.getNumero());
        if (dormitorios.isPresent()){
            throw new AssignmentAlreadyExistsException("Dormitorio ya existe con el piso y numero " + request.getPiso() + " " + request.getNumero());
        }

        Dormitorios dormitorio = dormitoriosMapper.dormitoriosRequestToDormitorios(request);
        dormitorio = dormitoriosRepository.save(dormitorio);


        return dormitoriosMapper.dormitoriosToDormitoriosResponse(dormitorio);
    }


    @Override
    public DormitoriosResponse updateDormitorio(Long id, DormitoriosRequest request) {
        Dormitorios dormitorio = dormitoriosRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Dormitorio no encontrado " + id));

        Usuarios usuarios = usuarioRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Usuario no encontrado " + id));

        dormitorio.setPiso(request.getPiso());
        dormitorio.setNumero(request.getNumero());
        dormitorio.setDescripcion(request.getDescripcion());
        dormitorio.setEstado(request.isEstado());
        dormitorio.setUsuario(usuarios);
        dormitorio = dormitoriosRepository.save(dormitorio);


        return  dormitoriosMapper.dormitoriosToDormitoriosResponse(dormitorio);
    }

    @Override
    public DormitoriosResponse getDormitorioById(Long id) {

        Dormitorios dormitorio = dormitoriosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dormitorio no encontrado " + id));
        return dormitoriosMapper.dormitoriosToDormitoriosResponse(dormitorio);
    }

    @Override
    public void deleteDormitorio(Long id) {
        if (!dormitoriosRepository.existsById(id)){
            throw new ResourceNotFoundException("Dormitorio no encontrado" + id);
        }
        dormitoriosRepository.deleteById(id);
    }

    @Override
    public List<DormitoriosResponse> getAllDormitorios() {

        List<Dormitorios> dormitorios = dormitoriosRepository.findAll();
        return dormitorios.stream().map(dormitoriosMapper::dormitoriosToDormitoriosResponse ).toList();

    }
}
