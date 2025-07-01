package com.parcial.material.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.material.model.Material;
import com.parcial.material.service.IMaterialService;

@RestController
@RequestMapping("/api/phoenix_dinamic/material")
public class MaterialController {
	@Autowired
	private IMaterialService service;
	
	@GetMapping("/getAll")
	public List<Material> obtenerTodos(){
		return service.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Material obtenerPorId(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/save")
	public Material guardarMaterial(@RequestBody Material m) {
		service.save(m);
		return m;
	}
	@DeleteMapping("/delete/{id}")
	public Material eliminarPorId(@PathVariable Long id) {
		Material m=service.findById(id);
		service.deleteById(id);
		return m;
	}
	@PutMapping("/edit/{id}")
	public Material actualizarDatos(@PathVariable("id") Long id,@RequestBody Material m) 
	{
		Material mAntiguo=service.findById(id);
		mAntiguo.setNombre(m.getNombre());
		mAntiguo.setDescripcion(m.getDescripcion());
		mAntiguo.setUnidad_medida(m.getUnidad_medida());
		mAntiguo.setPrecio_unitario(m.getPrecio_unitario());
		mAntiguo.setCantidad_disponible(m.getCantidad_disponible());
		service.save(mAntiguo);
		return mAntiguo;
	}

}
