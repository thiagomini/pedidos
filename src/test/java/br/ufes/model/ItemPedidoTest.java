package br.ufes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {

    /**
     * Cria um ItemPedido com o produto de atributos indicados.
     * @param nomeProduto O Nome do produto que será criado.
     * @param valorUnitario O valor unitário do produto que será criado.
     * @param quantidadeCriada A quantidade de produtos criados.
     * @param quantidadeAdquirida A quantidade de produtos que será adicionada ao ItemPedido
     * @return O Item de pedido com os produtos adicionados
     */
    private ItemPedido criarItemPedido(String nomeProduto, double valorUnitario, double quantidadeCriada, double quantidadeAdquirida) {
        return new ItemPedido(
                new Produto(nomeProduto, valorUnitario, quantidadeCriada),
                quantidadeAdquirida
        );
    }

    /**
     * Verifica que não é possível adicionar um item de pedido quando não há estoque do produto
     */
    @Test
    public void CT008() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ItemPedido itemPedido = criarItemPedido(
                    "produto",
                    1,
                    1,
                    2
            );
        });

        String expectedMessage = "Estoque indisponível para atender a quantidade solicitada (2.0) para o produto produto, restam 1.0 em estoque.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Verifica que a quantidade adquirida em um ItemPedido deve ser > 0
     */
    @Test
    public void CT009() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ItemPedido itemPedido = criarItemPedido(
                    "produto",
                    1,
                    1,
                    0
            );
        });

        String expectedMessage = "quantidade adquirida deve ser positiva!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}