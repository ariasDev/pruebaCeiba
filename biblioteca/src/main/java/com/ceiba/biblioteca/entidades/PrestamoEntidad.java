package com.ceiba.biblioteca.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prestamos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PrestamoEntidad {

    @Id
    @Column(name = "id", length = 20)
    private Integer idPrestamo;

    @Column(name = "isbn", length = 10)
    private String identificadorLibro;

    @Column(name = "idusuario", length = 10)
    private String identificadorUsuario;

    @Column(name = "tipousuario", length = 1)
    private Integer tipoUsuario;

    @Column(name = "fechamaximadevolucion", length = 10)
    private String fechaMaximaDevolucion;
}
