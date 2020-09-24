package br.ufes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {


    /**
     * Verifica que o nome de um produto não pode ser null
     */
    @Test
    void CT003() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Produto produto = new Produto(null, 1, 1);
        });

        String expectedMessage = "nome não deve ser null!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Verifica que o valor unitario de um Produto não pode ser <= 0
     */
    @Test
    void CT004() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Produto produto = new Produto("produto", 0, 1);
        });

        String expectedMessage = "Valor unitário deve ser positivo!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Verifica que a quantidade de Produtos iniciais não pode ser <= 0
     */
    @Test
    void CT005() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Produto produto = new Produto("produto", 1, 0);
        });

        String expectedMessage = "quantidade deve ser positiva!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}