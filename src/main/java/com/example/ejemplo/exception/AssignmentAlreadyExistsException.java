package com.example.ejemplo.exception;

public class AssignmentAlreadyExistsException extends RuntimeException{
    //esto funcionara para asignaciones (datos) repetidas dara el error
        public AssignmentAlreadyExistsException(String message) {
            super(message);
        }
}
