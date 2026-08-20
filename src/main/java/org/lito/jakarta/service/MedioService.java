package org.lito.jakarta.service;

import org.lito.jakarta.dto.MedioCreateDTO;
import org.lito.jakarta.dto.MedioDetalleDTO;
import org.lito.jakarta.dto.MedioListDTO;

import java.util.List;
import java.util.Optional;

public interface MedioService {
    List<MedioListDTO> findAll();
    List<MedioListDTO> findByCategoria(Integer categoriaId);
    Optional<MedioDetalleDTO> findById(Integer id);
    MedioDetalleDTO create(MedioCreateDTO dto);
    MedioDetalleDTO update(Integer id, MedioCreateDTO dto);
    void delete(Integer id);
}
