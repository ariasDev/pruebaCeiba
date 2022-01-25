package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoConsultaPrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoPrestamoDTO;
import com.ceiba.biblioteca.entidades.PrestamoEntidad;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.servicios.util.PrestamoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestamoServicioImp implements PrestamoServicio {

    private static final String TIPO_USUARIO_INVALIDO = "Tipo de usuario no permitido en la biblioteca";
    private static final String REGISTRO_NO_ENCONTRADO = "El registro consultado no existe";
    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;

    @Autowired
    PrestamoRepositorio prestamoRepositorio;

    @Autowired
    PrestamoTransformer prestamoTransformer;

    @Override
    public ResultadoPrestamoDTO generarNuevoPrestamo(PrestamoDTO prestamoDto) throws Exception {
        if (validarTipoUsuario(prestamoDto.getTipoUsuario())) {
            if (prestamoDto.getTipoUsuario() == USUARIO_INVITADO && !prestamoRepositorio.findByIdentificadorUsuario(prestamoDto.getIdentificacionUsuario()).isEmpty()) {
                throw new Exception("El usuario con identificación " + prestamoDto.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            } else {
                PrestamoEntidad prestamoEntidad = prestamoTransformer.transformarParaEntidad(prestamoDto);
                return prestamoTransformer.transformarParaResultadoPrestamoDto(prestamoRepositorio.save(prestamoEntidad));
            }
        } else {
            throw new Exception(TIPO_USUARIO_INVALIDO);
        }
    }

    @Override
    public ResultadoConsultaPrestamoDTO consultarPrestamo(int id) throws Exception {
        Optional<PrestamoEntidad> prestamoEntidad = prestamoRepositorio.findById(id);
        if (prestamoEntidad.isPresent()) {
            return prestamoTransformer.transformarParaResultadoDeConsultaPrestamoDto(prestamoEntidad.get());
        } else {
            throw new Exception(REGISTRO_NO_ENCONTRADO);
        }
    }

    protected boolean validarTipoUsuario(int tipoUsuario) {
        return tipoUsuario == USUARIO_AFILIADO || tipoUsuario == USUARIO_EMPLEADO || tipoUsuario == USUARIO_INVITADO;
    }
}
