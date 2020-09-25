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

    /**
     * Verifica que a função estoqueDisponivel retorna <b>True</b> quando a quantidade de produtos é maior que a quantidade
     * requisitada
     */
    @Test
    void CT006() {
        Produto produto = new Produto("produto", 1, 2);
        Boolean actualValue = produto.estoqueDisponivel(1);
        assertEquals(true, actualValue);
    }

    /**
     * Verifica que a função estoqueDisponivel retorna <b>False</b> quando a quantidade de produtos é <b>menor</b> que a quantidade
     * requisitada
     */
    @Test
    void CT007() {
        Produto produto = new Produto("produto", 1, 1);
        Boolean actualValue = produto.estoqueDisponivel(2);
        assertEquals(false, actualValue);
    }

    /**
     * Verifica que a função estoqueDisponivel retorna <b>True</b> quando a quantidade de produtos é <b>igual</b> à quantidade
     * requisitada
     */
    @Test
    void CT010() {
        Produto produto = new Produto("produto", 1, 1);
        Boolean actualValue = produto.estoqueDisponivel(1);
        assertEquals(true, actualValue);
    }
}