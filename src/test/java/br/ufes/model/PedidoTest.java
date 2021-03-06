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
    void CT001() {
        LocalDate dataPedido = getDataPadrao();

        Produto produto = getProdutoPadrao();
        Cliente cliente = getClientePadrao();
        Pedido pedido = new Pedido(cliente, produto, 1, dataPedido);

        assertEquals(dataPedido.plusMonths(1), pedido.getDataVencimento());
    }

    /**
     * Função <b>Construtor</b><br>
     * Verifica que O Produto informado no construtor deve estar presentes no Pedido
     */
    @Test
    void CT002() {
        Produto produto = new Produto("Produto A", 10, 10);
        Pedido pedido = criarPedido(produto, 3);

        ItemPedido itemEncontrado = pedido.getItemPorNome("Produto A").get();

        Produto produtoEncontrado = itemEncontrado.produto;

        assertEquals("Produto A", produtoEncontrado.getNome());
    }

    /**
     * Função <b>addItem()</b><br>
     * Verifica que não deve ser possível adicionar um produto que já esteja no pedido
     */
    @Test
    void CT003() {
        Produto produto1 = new Produto("Produto A", 10, 10);
        Produto produto2 = new Produto("Produto A", 10, 10);
        Pedido pedido = criarPedido(produto1, 3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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
    void CT004() {
        Produto produto = new Produto("Produto B", 10, 10);
        Pedido pedido = criarPedido(produto, 3);

        ItemPedido itemEncontrado = pedido.getItemPorNome("Produto B").get();

        double quantidadeDeItens = itemEncontrado.quantidade;

        assertEquals(3, quantidadeDeItens);
    }

    /**
     * Função <b>removerItem()</b><br>
     * Verifica que O produto de nome informado deve ser removido corretamente
     */
    @Test
    void CT005() {
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
    void CT006() {
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
    void CT007() {
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
    void CT008() {
        Produto produto = new Produto("Produto E", 10, 10);
        Pedido pedido = criarPedido(produto, 3);

        double valorTotal = pedido.getValorAPagar();

        assertEquals(28.5,valorTotal, 0.01);

    }

    /**
     * Função <b>getValorAPagar()</b><br>
     * Verifica que o desconto deve ser aplicado corretamente no valor total (com dois tipos de produto)
     */
    @Test
    void CT009() {
        Produto produto1 = new Produto("Produto 1", 10, 10);
        Pedido pedido = criarPedido(produto1, 3);

        Produto produto2 = new Produto("Produto 2", 5, 10);
        pedido.addItem(produto2, 3);

        double valorTotal = pedido.getValorAPagar();

        assertEquals(42.75, valorTotal, 0.01);

    }

    /**
     * <b>Construtor</b>
     * Verifica que não é possível criar um pedido com número negativo de itens
     */
    @Test
    void CT010() {
        Produto produto = new Produto("Produto A", 10, 1);
        LocalDate dataAtual = LocalDate.now();
        Cliente cliente = getClientePadrao();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Pedido(cliente, produto, -1, dataAtual)
        );

        assertEquals("Informe uma quantidade válida!", exception.getMessage());
    }

    /**
     * Função <b>removerItem</b>
     * Verifica que não é possível remover um item que não existe no pedido.
     */
    @Test
    void CT011() {
        Produto produto = new Produto("Produto A", 10, 1);
        Pedido pedido = criarPedido(produto, 1);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pedido.removerItem("Produto B");
        });

        assertEquals( "Item Produto B não encontrado", exception.getMessage());
    }

    /**
     * <b>Construtor</b>
     * Verifica que não é possível criar um pedido com um cliente nulo
     */
    @Test
    void CT012() {
        Produto produto = new Produto("Produto A", 10, 1);
        LocalDate dataAtual = LocalDate.now();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Pedido(null, produto, -1, dataAtual)
        );

        assertEquals("Informe um cliente válido", exception.getMessage());
    }

    /**
     * Função <b>toString</b>
     * Verifica que imprime todos os campos são impressos corretamente
     */
    @Test
    void VT013() {
        Produto produto = new Produto("Produto A", 10, 3);
        Cliente cliente = getClientePadrao();
        Pedido pedido = new Pedido(cliente, produto, 3, LocalDate.parse("2020-11-13"));

        String expectedMessage = "--------------- Pedido --------------" +
                "\nCliente: Fulano, CNPJ/CPF = 123.456.789-01" +
                "\nData: 13/11/2020, Data de vencimento: 13/12/2020" +
                "\nValor sem desconto: R$30,00" +
                "\nDesconto: R$1,50 (5.0%)" +
                "\nValor a pagar: R$28,50" +
                "\nItens do pedido:" +
                "\n\t- Produto A, valor unitário: R$10,00, quantidade no pedido: 3.0, valor total: R$30,00";

        assertEquals(expectedMessage, pedido.toString());

    }
}