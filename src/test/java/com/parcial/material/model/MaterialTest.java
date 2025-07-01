package com.parcial.material.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaterialTest {

    private Material material;

    @BeforeEach
    void setUp() {
        material = Material.builder()
                .id_producto(1L)
                .nombre("Tornillos de acero")
                .descripcion("Tornillos de 1/2 pulgada")
                .cantidad_disponible(100.0)
                .unidad_medida("unidades")
                .precio_unitario(25.5)
                .build();
    }

    @Test
    void testBuilder() {
        assertNotNull(material);
        assertEquals(1L, material.getId_producto());
        assertEquals("Tornillos de acero", material.getNombre());
        assertEquals(100.0, material.getCantidad_disponible(), 0.001);
        assertEquals(25.5, material.getPrecio_unitario(), 0.001);
    }

    @Test
    void testSettersAndGetters() {
        Material m = new Material();
        m.setId_producto(2L);
        m.setNombre("Tuercas");
        m.setDescripcion("Tuercas hexagonales");
        m.setCantidad_disponible(50.0);
        m.setUnidad_medida("paquetes");
        m.setPrecio_unitario(15.75);

        assertEquals(2L, m.getId_producto());
        assertEquals("Tuercas", m.getNombre());
        assertEquals("Tuercas hexagonales", m.getDescripcion());
        assertEquals(50.0, m.getCantidad_disponible(), 0.001);
        assertEquals("paquetes", m.getUnidad_medida());
        assertEquals(15.75, m.getPrecio_unitario(), 0.001);
    }

    @Test
    void testEqualsAndHashCode() {
        Material m1 = Material.builder().id_producto(1L).build();
        Material m2 = Material.builder().id_producto(1L).build();
        Material m3 = Material.builder().id_producto(2L).build();

        assertEquals(m1, m2);
        assertNotEquals(m1, m3);
        assertEquals(m1.hashCode(), m2.hashCode());
        assertNotEquals(m1.hashCode(), m3.hashCode());
    }

    @Test
    void testToString() {
        String str = material.toString();
        assertTrue(str.contains("Material"));
        assertTrue(str.contains("id_producto=1"));
        assertTrue(str.contains("nombre=Tornillos de acero"));
        assertTrue(str.contains("precio_unitario=25.5"));
    }

    @Test
    void testNoArgsConstructor() {
        Material m = new Material();
        assertNotNull(m);
        assertNull(m.getNombre());
        assertNull(m.getDescripcion());
        assertEquals(0.0, m.getCantidad_disponible());
    }
}