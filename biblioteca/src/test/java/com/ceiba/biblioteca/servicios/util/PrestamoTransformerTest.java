package com.ceiba.biblioteca.servicios.util;

import com.ceiba.biblioteca.dto.ResultadoConsultaPrestamoDTO;
import com.ceiba.biblioteca.dto.ResultadoPrestamoDTO;
import com.ceiba.biblioteca.entidades.PrestamoEntidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Clase de pruebas para PrestamoTransformer")
class PrestamoTransformerTest {
    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;

    @InjectMocks
    PrestamoTransformer prestamoTransformer;

    @Mock
    PrestamoEntidad prestamoEntidad;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        prestamoEntidad = PrestamoEntidad.builder()
                .idPrestamo(123)
                .identificadorUsuario("1234")
                .identificadorLibro("5432")
                .tipoUsuario(1)
                .fechaMaximaDevolucion("26/01/2022")
                .build();
    }

    @Test
    void debeRetornarUnaFechaCon10DiasDeviGenciaCuandoElTipoDeUsuarioEsAfiliado() {
        String resultadoEsperado = "26/01/2022";

        String resultado = prestamoTransformer.calcularFechaMaximaDevolucion(USUARIO_AFILIADO);

        assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void debeRetornarUnaFechaCon8DiasDeviGenciaCuandoElTipoDeUsuarioEsEmpleado() {
        String resultadoEsperado = "24/01/2022";

        String resultado = prestamoTransformer.calcularFechaMaximaDevolucion(USUARIO_EMPLEADO);

        assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void debeRetornarUnaFechaCon7DiasDeviGenciaCuandoElTipoDeUsuarioEsInvitado() {
        String resultadoEsperado = "21/01/2022";

        String resultado = prestamoTransformer.calcularFechaMaximaDevolucion(USUARIO_INVITADO);

        assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void debeRetornarUnObjetoResultadoPrestamoDtoCuandoLaFuncionEsllamada() {
        ResultadoPrestamoDTO resultadoPrestamoDTO = prestamoTransformer.transformarParaResultadoPrestamoDto(prestamoEntidad);

        assertThat(resultadoPrestamoDTO).isNotNull();
        assertThat(resultadoPrestamoDTO.getId()).isEqualTo(prestamoEntidad.getIdPrestamo());
        assertThat(resultadoPrestamoDTO.getFechaMaximaDevolucion()).isEqualTo(prestamoEntidad.getFechaMaximaDevolucion());
    }

    @Test
    void debeRetornarUnObjetoResultadoConsultaPrestamoDtoCuandoLaFuncionEsllamada() {
        ResultadoConsultaPrestamoDTO resultadoConsultaPrestamoDTO = prestamoTransformer.transformarParaResultadoDeConsultaPrestamoDto(prestamoEntidad);

        assertThat(resultadoConsultaPrestamoDTO).isNotNull();
        assertThat(resultadoConsultaPrestamoDTO.getId()).isEqualTo(prestamoEntidad.getIdPrestamo());
        assertThat(resultadoConsultaPrestamoDTO.getIsbn()).isEqualTo(prestamoEntidad.getIdentificadorLibro());
        assertThat(resultadoConsultaPrestamoDTO.getTipoUsuario()).isEqualTo(prestamoEntidad.getTipoUsuario());
        assertThat(resultadoConsultaPrestamoDTO.getFechaMaximaDevolucion()).isEqualTo(prestamoEntidad.getFechaMaximaDevolucion());
        assertThat(resultadoConsultaPrestamoDTO.getIdentificacionUsuario()).isEqualTo(prestamoEntidad.getIdentificadorUsuario());
    }


}