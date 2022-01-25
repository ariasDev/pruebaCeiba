package com.ceiba.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoConsultaPrestamoDTO {
    private Integer id;
    private String isbn;
    private String identificacionUsuario;
    private Integer tipoUsuario;
    private String fechaMaximaDevolucion;
}
