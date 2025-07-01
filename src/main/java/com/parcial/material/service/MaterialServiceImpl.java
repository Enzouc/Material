package com.parcial.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.material.model.Material;
import com.parcial.material.repository.IMaterialRepository;

@Service
public class MaterialServiceImpl implements IMaterialService{

	@Autowired
	private IMaterialRepository repository;
	@Override
	public List<Material> findAll() {
		return repository.findAll();
	}

	@Override
	public Material findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public void save(Material m) {
		repository.save(m);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}
	
}
