package com.parcial.material.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parcial.material.model.Material;
import com.parcial.material.repository.IMaterialRepository;

@ExtendWith(MockitoExtension.class)
public class IMaterialServiceTest {

    @Mock
    private IMaterialRepository repository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    private Material material1;
    private Material material2;

    @BeforeEach
    void setUp() {
        material1 = new Material();
        material1.setId_producto(1L);
        material1.setNombre("Tornillos");
        material1.setCantidad_disponible(100.0);

        material2 = new Material();
        material2.setId_producto(2L);
        material2.setNombre("Tuercas");
        material2.setCantidad_disponible(200.0);
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(material1, material2));

        List<Material> result = materialService.findAll();

        assertEquals(2, result.size());
        assertEquals("Tornillos", result.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Existente() {
        when(repository.findById(1L)).thenReturn(Optional.of(material1));

        Material result = materialService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId_producto());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NoExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> materialService.findById(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void testSave() {
    Material material = new Material();
        when(repository.save(any(Material.class))).thenReturn(material);
        materialService.save(material);
        verify(repository, times(1)).save(material);
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(1L);

        materialService.deleteById(1L);

        verify(repository, times(1)).deleteById(1L);
    }

}