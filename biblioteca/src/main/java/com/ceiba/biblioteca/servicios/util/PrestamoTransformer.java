package com.ceiba.biblioteca.servicios.util;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoConsultaPrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoPrestamoDTO;
import com.ceiba.biblioteca.entidades.PrestamoEntidad;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@NoArgsConstructor
public class PrestamoTransformer {

    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;

    public PrestamoEntidad transformarParaEntidad(PrestamoDTO prestamoDto) {
        return PrestamoEntidad.builder()
                .idPrestamo(Integer.valueOf(String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(1, 6)))
                .identificadorLibro(prestamoDto.getIsbn())
                .identificadorUsuario(prestamoDto.getIdentificacionUsuario())
                .tipoUsuario(prestamoDto.getTipoUsuario())
                .fechaMaximaDevolucion(calcularFechaMaximaDevolucion(prestamoDto.getTipoUsuario()))
                .build();
    }

    public ResultadoPrestamoDTO transformarParaResultadoPrestamoDto(PrestamoEntidad prestamoEntidad) {
        return ResultadoPrestamoDTO.builder()
                .id(prestamoEntidad.getIdPrestamo())
                .fechaMaximaDevolucion(prestamoEntidad.getFechaMaximaDevolucion())
                .build();
    }

    public ResultadoConsultaPrestamoDTO transformarParaResultadoDeConsultaPrestamoDto(PrestamoEntidad prestamoEntidad) {
        return ResultadoConsultaPrestamoDTO.builder()
                .id(prestamoEntidad.getIdPrestamo())
                .identificacionUsuario(prestamoEntidad.getIdentificadorUsuario())
                .isbn(prestamoEntidad.getIdentificadorLibro())
                .tipoUsuario(prestamoEntidad.getTipoUsuario())
                .fechaMaximaDevolucion(prestamoEntidad.getFechaMaximaDevolucion())
                .build();
    }

    protected String calcularFechaMaximaDevolucion(int tipoUsuario) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaPrestamo = LocalDate.now();
        int diasVigencia = 0;

        switch (tipoUsuario) {
            case USUARIO_AFILIADO:
                diasVigencia = 10;
                break;
            case USUARIO_EMPLEADO:
                diasVigencia = 8;
                break;
            case USUARIO_INVITADO:
                diasVigencia = 7;
                break;
            default:
                break;
        }

        LocalDate fechaMaximaDevolucion = fechaPrestamo;
        int addedDays = 0;
        while (addedDays < diasVigencia) {
            fechaMaximaDevolucion = fechaMaximaDevolucion.plusDays(1);
            if (!(fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY || fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return fechaMaximaDevolucion.format(formato);
    }


}
