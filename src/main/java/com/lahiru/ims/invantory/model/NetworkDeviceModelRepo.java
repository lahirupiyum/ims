package com.lahiru.ims.invantory.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkDeviceModelRepo extends JpaRepository<Model, Integer>{
    Page<Model> findAllByIsActive(Boolean isActive, Pageable pageable);
    List<Model> findAllByIsActive(Boolean isActive);
    Optional<Model> findByIdAndIsActive(Integer id, Boolean isActive);
    Boolean existsByNameAndIsActive(String name, Boolean isActive);
}
