package com.ceiba.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PrestamoDTO {

    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
}
