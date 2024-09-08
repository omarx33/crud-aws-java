package com.example.ejemplo.service.impl;

import com.example.ejemplo.dto.DormitoriosRequest;
import com.example.ejemplo.dto.DormitoriosResponse;
import com.example.ejemplo.mapper.DormitoriosMapper;
import com.example.ejemplo.model.Dormitorios;
import com.example.ejemplo.repository.DormitoriosRepository;
import com.example.ejemplo.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //solo carga mockito
public class DormitoriosServiceImplTest {
    @Mock
    private DormitoriosMapper dormitoriosMapper;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private DormitoriosRepository dormitoriosRepository;
    @InjectMocks
    private DormitoriosImpl dormitoriosImpl;

    @Test
    @DisplayName("dado se crea un dormitorio, cuando se crea un dormitorio, entonces se devuelve el dormitorio creado")
    void givenDormitorioRequest_whenCreateDormitorio_thenReturnDormitorioResponse() {
        // Given
        DormitoriosRequest dormitoriosRequest = new DormitoriosRequest(); //aqui se podria poner los datos
        DormitoriosResponse dormitoriosResponse = new DormitoriosResponse();

        /* simulacion de comportamiento, esto debe ser en el orden del que esta ejecutando el create de imlp createDormitorio*/
        when(dormitoriosRepository.findByPisoAndNumero(dormitoriosRequest.getPiso(), dormitoriosRequest.getNumero()) )
                .thenReturn(Optional.of(new Dormitorios())); //busca por piso y numero

        when(dormitoriosMapper.dormitoriosRequestToDormitorios(dormitoriosRequest)).thenReturn(new Dormitorios()); //mapea el request a dormitorio
        when(dormitoriosRepository.save(any(Dormitorios.class))).thenReturn(new Dormitorios());//guarda el dormitorio y retorna el dormitorio guardado
        when(dormitoriosMapper.dormitoriosToDormitoriosResponse(any(Dormitorios.class))).thenReturn(dormitoriosResponse); //mapea el dormitorio a dormitorio response


        // When
        DormitoriosResponse result = dormitoriosImpl.createDormitorio(dormitoriosRequest); //simula la busqueda


        // Then
        assertNotNull(result); //si es null
        assertEquals(dormitoriosResponse, result); //es igual a
    }

}
