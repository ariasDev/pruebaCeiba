package com.ceiba.biblioteca.controladores;


import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoConsultaPrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoPrestamoDTO;
import com.ceiba.biblioteca.servicios.PrestamoServicio;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio prestamoServicio;

    @PostMapping
    @ApiOperation(
            value = "Registrar un nuevo prestamo",
            notes = "Servicio para registrar un nuevo prestamo",
            response = ResultadoPrestamoDTO.class)
    public ResponseEntity<Object> realizarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        try {
            ResultadoPrestamoDTO resultadoPrestamoDTO = prestamoServicio.generarNuevoPrestamo(prestamoDTO);
            return new ResponseEntity<>(resultadoPrestamoDTO, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Obtener el detalle de un prestamo",
            notes = "Servicio para obtener el detalle de un prestamo",
            response = ResultadoConsultaPrestamoDTO.class)
    public ResponseEntity<Object> consultarPrestamo(@ApiParam(required = true, name = "id", value = "Numero que identifica a un prestamo") @PathVariable int id) {
        try {
            ResultadoConsultaPrestamoDTO resultadoConsultaPrestamoDTO = prestamoServicio.consultarPrestamo(id);
            return new ResponseEntity<>(resultadoConsultaPrestamoDTO, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }


}

