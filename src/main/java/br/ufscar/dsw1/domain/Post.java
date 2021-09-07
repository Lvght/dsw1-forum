package br.ufscar.dsw1.domain;

public class Post {
    private Long id;
    private Long id_autor;
    private Long id_forum;
    private Long id_topico;
    private String titulo;
    private String conteudo;

    public Post(Long id_autor, Long id_forum, Long id_topico, String titulo, String conteudo) {
        this.id_autor = id_autor;
        this.id_forum = id_forum;
        this.id_topico = id_topico;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_autor() {
        return id_autor;
    }

    public void setId_autor(Long id_autor) {
        this.id_autor = id_autor;
    }

    public Long getId_forum() {
        return id_forum;
    }

    public void setId_forum(Long id_forum) {
        this.id_forum = id_forum;
    }

    public Long getId_topico() {
        return id_topico;
    }

    public void setId_topico(Long id_topico) {
        this.id_topico = id_topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

}
