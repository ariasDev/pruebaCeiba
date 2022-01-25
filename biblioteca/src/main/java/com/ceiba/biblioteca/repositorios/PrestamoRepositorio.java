package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.entidades.PrestamoEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepositorio extends CrudRepository<PrestamoEntidad, Integer> {

    List<PrestamoEntidad> findByIdentificadorUsuario(String id);
}
