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
    void CT001() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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
    void CT002() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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

    /**
     * Verifica que o valor total do item é a quantidade de itens multiplicado pelo valor unitário
     */
    @Test
    void CT003() {
        ItemPedido itemPedido = criarItemPedido(
                "produto",
                10,
                10,
                10
        );

        assertEquals(100, itemPedido.getValorItem(), 0.01);
    }

    /**
     * Verifica que o método toString() do itemPedido imprime corretamente as informações do item de pedido
     */
    @Test
    void CT004() {
        ItemPedido itemPedido = criarItemPedido(
                "produto",
                15,
                10,
                5
        );

        String expectedString = "produto, valor Unitario: R$ 15.0, quantidade no pedido:5.0, valor Total: R$ 75,00";
        assertEquals(expectedString, itemPedido.toString());
    }
}