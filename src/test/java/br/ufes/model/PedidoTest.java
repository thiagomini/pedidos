package br.ufes.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class PedidoTest {

    /**
     * Cria um pedido sem especificar a Data, o que utilizará a Data Padrão
     * @param produto O Produto a ser adicionado no pedido.
     * @param quantidade A quantidade do produto a ser adicionado no pedido.
     * @param cliente O cliente do pedido
     * @return O pedido construído.
     */
    private Pedido criarPedido(Produto produto, double quantidade, Cliente cliente) {
        LocalDate dataAtual = getDataPadrao();
        return new Pedido(cliente, produto, quantidade, dataAtual);
    }

    /**
     * Cria um Pedido sem especificar os detalhes do cliente e data, utilizando os valores padrão.
     * @param produto O Produto a ser inserido no pedido
     * @param quantidade A quantidade do produto a ser inserido no pedido.
     * @return O pedido construído.
     */
    private Pedido criarPedido(Produto produto, double quantidade) {
        LocalDate dataAtual = getDataPadrao();
        Cliente cliente = getClientePadrao();
        return new Pedido(cliente, produto, quantidade, dataAtual);
    }

    /**
     * Cria um pedido informando apenas a quantidade do produto. o Cliente, a data e o Produto são criados com
     * os valores padrão
     * @param quantidade A quantidade do Produto padrão a ser adicionado no pedido.
     * @return O pedido construído.
     */
    private Pedido criarPedido(double quantidade) {
        LocalDate dataAtual = getDataPadrao();
        Produto produto = getProdutoPadrao();
        Cliente cliente = getClientePadrao();
        return new Pedido(cliente, produto, quantidade, dataAtual);
    }


    private Cliente getClientePadrao() {
        return new Cliente("Fulano", "123.456.789-01");
    }

    private Produto getProdutoPadrao() {
        return new Produto("produto", 10, 10);
    }

    private LocalDate getDataPadrao() {
        return LocalDate.now();
    }


    /**
     * Função <b>Construtor</b><br>
     * Verifica que a data de vencimento de um Pedido criado deve ser de 1 mês após a data informada
     */
    @Test
    public void CT001() {
        LocalDate dataPedido = getDataPadrao();

        Produto produto = getProdutoPadrao();
        Cliente cliente = getClientePadrao();
        Pedido pedido = new Pedido(cliente, produto, 1, dataPedido);

        assertEquals(pedido.getDataVencimento(), dataPedido.plusMonths(1));
    }

    /**
     * Função <b>Construtor</b><br>
     * Verifica que O Produto informado no construtor deve estar presentes no Pedido
     */
    @Test
    public void CT002() {
        Produto produto = new Produto("Produto A", 10, 10);
        Pedido pedido = criarPedido(produto, 3);

        ItemPedido itemEncontrado = pedido.getItemPorNome("Produto A").get();

        Produto produtoEncontrado = itemEncontrado.produto;

        assertEquals(produtoEncontrado.getNome(), "Produto A");
    }

    /**
     * Função <b>addItem()</b><br>
     * Verifica que não deve ser possível adicionar um produto que já esteja no pedido
     */
    @Test
    public void CT003() {
        Produto produto1 = new Produto("Produto A", 10, 10);
        Produto produto2 = new Produto("Produto A", 10, 10);
        Pedido pedido = criarPedido(produto1, 3);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedido.addItem(produto2, 1);
        });

        String expectedMessage = "Produto já existe! Remova-o ou altere a quantidade";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Função <b>addItem()</b><br>
     * Verifica que O produto e a quantidade informados para essa função devem ser adicionados corretamente
     */
    @Test
    public void CT004() {
        Produto produto = new Produto("Produto B", 10, 10);
        Pedido pedido = criarPedido(produto, 3);

        ItemPedido itemEncontrado = pedido.getItemPorNome("Produto B").get();

        double quantidadeEsperada = itemEncontrado.quantidade;

        assertEquals(quantidadeEsperada, 3);
    }

    /**
     * Função <b>removerItem()</b><br>
     * Verifica que O produto de nome informado deve ser removido corretamente
     */
    @Test
    public void CT005() {
        Produto produto = new Produto("Produto C", 10, 10);
        Pedido pedido = criarPedido(produto, 1);

        pedido.removerItem("Produto C");

        Optional<ItemPedido> itemPedido = pedido.getItemPorNome("Produto C");

        assertFalse(itemPedido.isPresent());
    }


    /**
     * Função <b>getItemPorNome()</b><br>
     * Verifica que o Produto de nome pesquisado deve ser retornado da lista de produtos
     */
    @Test
    public void CT006() {
        Produto produto = new Produto("Produto D", 10, 10);
        Pedido pedido = criarPedido(produto, 1);

        Optional<ItemPedido> itemPedido = pedido.getItemPorNome("Produto D");

        assertTrue(itemPedido.isPresent());
    }

    /**
     * Função <b>getItemPorNome()</b><br>
     * Verifica que nenhum produto deve ser retornado caso não encontre o produto de nome especificado.
     */
    @Test
    public void CT007() {
        Produto produto = new Produto("Produto E", 10, 10);
        Pedido pedido = criarPedido(produto, 1);

        Optional<ItemPedido> itemPedido = pedido.getItemPorNome("Produto F");

        assertFalse(itemPedido.isPresent());
    }

    /**
     * Função <b>getValorAPagar()</b><br>
     * Verifica que o valor total está correto (com um tipo de produto)
     */
    @Test
    public void CT008() {
        Produto produto = new Produto("Produto E", 10, 10);
        Pedido pedido = criarPedido(produto, 3);

        double valorTotal = pedido.getValorAPagar();

        assertEquals(valorTotal, 28.5, 0.01);

    }

    /**
     * Função <b>getValorAPagar()</b><br>
     * Verifica que o desconto deve ser aplicado corretamente no valor total (com dois tipos de produto)
     */
    @Test
    public void CT009() {
        Produto produto1 = new Produto("Produto 1", 10, 10);
        Pedido pedido = criarPedido(produto1, 3);

        Produto produto2 = new Produto("Produto 2", 5, 10);
        pedido.addItem(produto2, 3);

        double valorTotal = pedido.getValorAPagar();

        assertEquals(valorTotal, 42.75, 0.01);

    }






}