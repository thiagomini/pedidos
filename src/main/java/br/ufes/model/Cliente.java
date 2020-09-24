package br.ufes.model;

public final class Cliente {

    private String nome;
    private String CNPJOuCPF;

    public Cliente(String nome, String codigo) {
        setNome(nome);
        setCNPJOuCPF(codigo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("nome não deve ser null!");
        }
        this.nome = nome;
    }

    public void setCNPJOuCPF(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("codigo não deve ser null!");
        }
        this.CNPJOuCPF = codigo;
    }

    public String getCNPJOuCPF() {
        return CNPJOuCPF;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNPJ/CPF = " + CNPJOuCPF;
    }

}
