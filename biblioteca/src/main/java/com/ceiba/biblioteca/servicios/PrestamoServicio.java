package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoConsultaPrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoPrestamoDTO;

public interface PrestamoServicio {

    ResultadoPrestamoDTO generarNuevoPrestamo(PrestamoDTO prestamoDto) throws Exception;

    ResultadoConsultaPrestamoDTO consultarPrestamo(int id) throws Exception;
}
