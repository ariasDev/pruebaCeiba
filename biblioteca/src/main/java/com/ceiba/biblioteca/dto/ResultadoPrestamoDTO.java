package com.ceiba.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoPrestamoDTO {

    private int id;
    private String fechaMaximaDevolucion;

}
