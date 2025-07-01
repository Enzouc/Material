package com.parcial.material.service;

import java.util.List;

import com.parcial.material.model.Material;

public interface IMaterialService {
	List<Material> findAll();
	Material findById(Long id);
	void save(Material m);
	void deleteById(Long id);
}
