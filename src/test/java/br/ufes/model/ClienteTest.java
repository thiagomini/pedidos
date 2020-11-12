package br.ufes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    /**
     * Verifica que o codigo de um cliente não pode ser null
     */
    @Test
    public void CT001() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente("Nome", null);
        });

        String expectedMessage = "codigo não deve ser null!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Verifica que o nome de um cliente não pode ser null
     */
    @Test
    public void CT002() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Cliente cliente = new Cliente(null, "12345678901");
        });

        String expectedMessage = "nome não deve ser null!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }
}