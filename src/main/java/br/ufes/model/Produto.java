package br.ufes.model;

public final class Produto {

    private String nome;
    private double valorUnitario;
    private double valorUltimaCompra;
    private double quantidade;

    public Produto(String nome, double valorUnitario, double quantidade) {
        setNome(nome);
        setValorUnitario(valorUnitario);
        setQuantidade(quantidade);
    }

    public boolean estoqueDisponivel(double quantidadeNecessaria) {
        return this.quantidade >= quantidadeNecessaria;
    }

    public String getNome() {
        return nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getValorUltimaCompra() {
        return valorUltimaCompra;
    }

    public double getQuantidade() {
        return quantidade;
    }

    private void setQuantidade(double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("quantidade deve ser positiva!");
        }
        this.quantidade = quantidade;
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("nome não deve ser null!");
        }
        this.nome = nome;
    }

    public void setValorUnitario(double valorUnitario) {
        if (valorUnitario <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser positivo!");
        }
        this.valorUltimaCompra = this.valorUnitario;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "Produto: " + nome
                + ", valor unitario: R$" + valorUnitario
                + ", valor da ultima compra: R$" + valorUltimaCompra
                + ", quantidade em estoque: " + quantidade;
    }

}
