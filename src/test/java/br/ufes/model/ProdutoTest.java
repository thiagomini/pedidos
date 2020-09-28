package br.ufes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {


    /**
     * Verifica que o nome de um produto não pode ser null
     */
    @Test
    void CT001() {
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
    void CT002() {
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
    void CT003() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Produto produto = new Produto("produto", 1, 0);
        });

        String expectedMessage = "quantidade deve ser positiva!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Verifica que a função estoqueDisponivel retorna <b>True</b> quando a quantidade de produtos é maior que a quantidade
     * requisitada
     */
    @Test
    void CT004() {
        Produto produto = new Produto("produto", 1, 2);
        boolean actualValue = produto.estoqueDisponivel(1);
        assertTrue(actualValue);
    }

    /**
     * Verifica que a função estoqueDisponivel retorna <b>False</b> quando a quantidade de produtos é <b>menor</b> que a quantidade
     * requisitada
     */
    @Test
    void CT005() {
        Produto produto = new Produto("produto", 1, 1);
        boolean actualValue = produto.estoqueDisponivel(2);
        assertFalse(actualValue);
    }

    /**
     * Verifica que a função estoqueDisponivel retorna <b>True</b> quando a quantidade de produtos é <b>igual</b> à quantidade
     * requisitada
     */
    @Test
    void CT006() {
        Produto produto = new Produto("produto", 1, 1);
        boolean actualValue = produto.estoqueDisponivel(1);
        assertTrue(actualValue);
    }
}