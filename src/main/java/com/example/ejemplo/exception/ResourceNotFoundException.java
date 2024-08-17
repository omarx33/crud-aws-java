package com.example.ejemplo.exception;

public class ResourceNotFoundException extends RuntimeException{
    //este exception funcionara para recursos que no sean encontrados (busquedas)
        public ResourceNotFoundException(String message) {
            super(message);
        }
}
