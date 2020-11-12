package br.ufes.model;

public final class Cliente {

    private String nome;
    private String cnpjOuCpf;

    public Cliente(String nome, String codigo) {
        setNome(nome);
        setCnpjOuCpf(codigo);
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

    public void setCnpjOuCpf(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("codigo não deve ser null!");
        }
        this.cnpjOuCpf = codigo;
    }

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNPJ/CPF = " + cnpjOuCpf;
    }

}
