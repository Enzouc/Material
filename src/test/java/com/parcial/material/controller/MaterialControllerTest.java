package com.parcial.material.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcial.material.model.Material;
import com.parcial.material.service.IMaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(MaterialController.class)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMaterialService materialService;

    @Autowired
    private ObjectMapper objectMapper;

    private Material material;
    private Material material2;

    @BeforeEach
    void setUp() {
        material = new Material();
        material.setId_producto(1L);
        material.setNombre("Tornillos");
        material.setDescripcion("Tornillos de acero inoxidable");
        material.setUnidad_medida("unidades");
        material.setPrecio_unitario(50.0);
        material.setCantidad_disponible(100);

        material2 = new Material();
         material2.setId_producto(2L);
        material2.setNombre("Tuercas");
        material2.setDescripcion("Tuercas de acero galvanizado");
        material2.setUnidad_medida("unidades");
        material2.setPrecio_unitario(30.0);
        material2.setCantidad_disponible(200);
    }

    @Test
    public void testObtenerTodos() throws Exception {
        List<Material> materiales = Arrays.asList(material, material2);
        when(materialService.findAll()).thenReturn(materiales);

        mockMvc.perform(get("/api/phoenix_dinamic/material/getAll"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2))
               .andExpect(jsonPath("$[0].nombre").value("Tornillos"))
               .andExpect(jsonPath("$[1].nombre").value("Tuercas"));
    }

    @Test
    public void testObtenerPorId() throws Exception {
    when(materialService.findById(1L)).thenReturn(material);

    mockMvc.perform(get("/api/phoenix_dinamic/material/get/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id_producto").value(1))
           .andExpect(jsonPath("$.nombre").value("Tornillos"))
           .andExpect(jsonPath("$.precio_unitario").value(50.0));
}
    @Test
    public void testGuardarMaterial() throws Exception {
    
    doNothing().when(materialService).save(any(Material.class));
    
    mockMvc.perform(post("/api/phoenix_dinamic/material/save")
           .contentType(MediaType.APPLICATION_JSON)
           .content(objectMapper.writeValueAsString(material)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.nombre").value("Tornillos"))
           .andExpect(jsonPath("$.cantidad_disponible").value(100));
    
    verify(materialService, times(1)).save(any(Material.class));
}

    @Test
    public void testEliminarPorId() throws Exception {
        when(materialService.findById(1L)).thenReturn(material);
        doNothing().when(materialService).deleteById(1L);

        mockMvc.perform(delete("/api/phoenix_dinamic/material/delete/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id_producto").value(1))
               .andExpect(jsonPath("$.nombre").value("Tornillos"));

        verify(materialService, times(1)).deleteById(1L);
    }

    @Test
    public void testActualizarDatos() throws Exception {
    Material materialActualizado = new Material();
    materialActualizado.setNombre("Tornillos actualizados");
    materialActualizado.setDescripcion("Nueva descripción");
    materialActualizado.setUnidad_medida("paquetes");
    materialActualizado.setPrecio_unitario(55.0);
    materialActualizado.setCantidad_disponible(150);

    
    when(materialService.findById(1L)).thenReturn(material);
    doNothing().when(materialService).save(any(Material.class));

    mockMvc.perform(put("/api/phoenix_dinamic/material/edit/1")
           .contentType(MediaType.APPLICATION_JSON)
           .content(objectMapper.writeValueAsString(materialActualizado)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.nombre").value("Tornillos actualizados"))
           .andExpect(jsonPath("$.precio_unitario").value(55.0))
           .andExpect(jsonPath("$.cantidad_disponible").value(150));
    
    verify(materialService, times(1)).findById(1L);
    verify(materialService, times(1)).save(any(Material.class));
}

    @Test
    public void testObtenerPorIdNoExistente() throws Exception {
        when(materialService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/phoenix_dinamic/material/get/99"))
               .andExpect(status().isOk())
               .andExpect(content().string(""));
    }
}