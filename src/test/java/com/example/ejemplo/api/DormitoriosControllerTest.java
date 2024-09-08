package com.example.ejemplo.api;

import com.example.ejemplo.dto.DormitoriosRequest;
import com.example.ejemplo.dto.DormitoriosResponse;
import com.example.ejemplo.service.DormitoriosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) //junit y mockito (si usas bd en la prueba u otras cosas avanzadas)
public class DormitoriosControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DormitoriosService dormitoriosService;

    @InjectMocks
    private DormitoriosController dormitoriosController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); //si tienes campos de fechas automaticos que lo vuelva a json en el request
        mockMvc = MockMvcBuilders.standaloneSetup(dormitoriosController).build();
    }


    @Test
    @DisplayName("Dado los datos del dormitorio, cuando se crea un dormitorio, entonces se devuelve el dormitorio creado ")
    public void givenValidDormitorioRequest_whenCreateDormitorio_thenReturnDormitorioResponse() throws Exception { //el  throws Exception es para que no de error en el objectMapper (declaralo junto al mapper de abajo)
        // Given
        DormitoriosRequest dormitoriosRequest = new DormitoriosRequest(4, 3, "registro de data", true, 1L);
        DormitoriosResponse dormitoriosResponse = new DormitoriosResponse(1L, 1, 2, "registro de data", true, 1L);

        // When
        given(dormitoriosService.createDormitorio(any(DormitoriosRequest.class))).willReturn(dormitoriosResponse); //any = cualquier objeto typo...

        // Then
        mockMvc.perform(post("/dormitorios") //url de controller
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                //writeValueAsString = convierte un objeto a un string json para ser leido
                .content(objectMapper.writeValueAsString(dormitoriosRequest)))
                .andExpect(status().isCreated()); //espera respuesta un 200 created


    }

}
