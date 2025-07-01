package com.parcial.material.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parcial.material.model.Material;
@Repository
public interface IMaterialRepository extends JpaRepository<Material, Long>{

}
