package com.example.les12demo.exception;

// Definieer een op maat gemaakte uitzondering die wordt gegooid als een gevraagde bron niet kan worden gevonden
public class ResourceNotFoundException extends RuntimeException {

    // Constructor met een parameter die de foutmelding bevat
    public ResourceNotFoundException(String message) {
        super(message);
    }

}

