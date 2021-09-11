package br.ufscar.dsw1.domain;

public class Forum {
    private long id;
    private long id_dono;
    private int escopo_postagem;
    private int escopo_acesso;
    private String titulo;
    private String descricao;
    private String icone;
    private long membros;

    public Forum(long id_dono, int escopo_postagem, int escopo_acesso, String titulo, String descricao, String icone) {
        this.id_dono = id_dono;
        this.escopo_postagem = escopo_postagem;
        this.escopo_acesso = escopo_acesso;
        this.titulo = titulo;
        this.descricao = descricao;
        this.icone = icone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_dono() {
        return id_dono;
    }

    public void setId_dono(long id_dono) {
        this.id_dono = id_dono;
    }

    public int getEscopo_postagem() {
        return escopo_postagem;
    }

    public void setEscopo_postagem(int escopo_postagem) {
        this.escopo_postagem = escopo_postagem;
    }

    public int getEscopo_acesso() {
        return escopo_acesso;
    }

    public void setEscopo_acesso(int escopo_acesso) {
        this.escopo_acesso = escopo_acesso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public long getMembros() {
        return membros;
    }

    public void setMembros(long membros) {
        this.membros = membros;
    }

}
