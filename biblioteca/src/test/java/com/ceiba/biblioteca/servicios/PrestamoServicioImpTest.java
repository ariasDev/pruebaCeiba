package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.dto.PrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoPrestamoDTO;
import com.ceiba.biblioteca.entidades.PrestamoEntidad;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.servicios.util.PrestamoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Clase de pruebas para PrestamoServicioImp")
class PrestamoServicioImpTest {

    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_DESCONOCIDO = 5;

    @Mock
    PrestamoRepositorio prestamoRepositorio;

    @Mock
    PrestamoTransformer prestamoTransformer;

    @Mock
    PrestamoDTO prestamoDTO;

    @InjectMocks
    PrestamoServicioImp prestamoServicioImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        prestamoDTO = PrestamoDTO.builder()
                .identificacionUsuario("12344")
                .isbn("123454")
                .tipoUsuario(USUARIO_AFILIADO)
                .build();
    }

    @Test
    void debeRetornarVerdaderoCuandoElTipoDeUsuarioEsValido() {
        boolean resultado = prestamoServicioImp.validarTipoUsuario(USUARIO_AFILIADO);

        assertThat(resultado).isTrue();
    }

    @Test
    void debeRetornarFalsoCuandoElTipoDeUsuarioNoEsValido() {
        boolean resultado = prestamoServicioImp.validarTipoUsuario(USUARIO_DESCONOCIDO);

        assertThat(resultado).isFalse();
    }

    @Test
    void debeGenerarNuevoPrestamoCuandoSeCumplenLasValidacionesNecesarias() throws Exception {
        when(prestamoTransformer.transformarParaEntidad(any(PrestamoDTO.class))).thenReturn(PrestamoEntidad.builder().build());
        when(prestamoTransformer.transformarParaResultadoPrestamoDto(any(PrestamoEntidad.class))).thenReturn(ResultadoPrestamoDTO.builder().build());
        when(prestamoRepositorio.save(any(PrestamoEntidad.class))).thenReturn(PrestamoEntidad.builder().build());

        ResultadoPrestamoDTO resultadoPrestamoDTO = prestamoServicioImp.generarNuevoPrestamo(prestamoDTO);

        assertThat(resultadoPrestamoDTO).isNotNull();
        verify(prestamoTransformer).transformarParaEntidad(ArgumentMatchers.any(PrestamoDTO.class));
        verify(prestamoTransformer).transformarParaResultadoPrestamoDto(ArgumentMatchers.any(PrestamoEntidad.class));
        verify(prestamoRepositorio).save(ArgumentMatchers.any(PrestamoEntidad.class));
    }


}