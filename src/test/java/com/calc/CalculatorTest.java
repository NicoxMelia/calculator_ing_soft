package com.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    // ── Suma ─────────────────────────────────────────────────────

    @Test
    void suma_dosPositivos_retornaSuma() {
        assertEquals(5, calc.suma(2, 3));
    }

    @Test
    void suma_conNegativo_retornaSuma() {
        assertEquals(1, calc.suma(3, -2));
    }

    @Test
    void suma_dosNegativos_retornaSuma() {
        assertEquals(-5, calc.suma(-2, -3));
    }

    // ── Resta ────────────────────────────────────────────────────

    @Test
    void resta_dosPositivos_retornaResta() {
        assertEquals(1, calc.resta(3, 2));
    }

    @Test
    void resta_resultadoNegativo_retornaResta() {
        assertEquals(-1, calc.resta(2, 3));
    }

    // ── Multiplicacion ───────────────────────────────────────────

    @Test
    void multiplica_dosPositivos_retornaProducto() {
        assertEquals(6, calc.multiplica(2, 3));
    }

    @Test
    void multiplica_porCero_retornaCero() {
        assertEquals(0, calc.multiplica(5, 0));
    }

    @Test
    void multiplica_dosNegativos_retornaPositivo() {
        assertEquals(6, calc.multiplica(-2, -3));
    }

    // ── Division ─────────────────────────────────────────────────

    @Test
    void divide_dosPositivos_retornaCociente() {
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    void divide_porCero_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(10, 0));
    }

    @Test
    void divide_porCero_mensajeDeError() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> calc.divide(10, 0)
        );
        assertEquals("No se puede dividir por cero", ex.getMessage());
    }
}
