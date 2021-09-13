package br.ufscar.dsw1.domain;

public class Topic {
    private long id;
    private long id_forum;
    private String nome;

    public Topic(long id_forum, String nome) {
        this.id_forum = id_forum;
        this.nome = nome;
    }

    public Topic(long id, long id_forum, String nome) {
        this.id = id;
        this.id_forum = id_forum;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_forum() {
        return id_forum;
    }

    public void setId_forum(long id_forum) {
        this.id_forum = id_forum;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
