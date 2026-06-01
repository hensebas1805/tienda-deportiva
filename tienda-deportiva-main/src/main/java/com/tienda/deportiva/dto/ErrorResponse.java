package com.tienda.deportiva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String mensaje;
    private String detalles;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String path;
}