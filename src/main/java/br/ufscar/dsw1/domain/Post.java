package br.ufscar.dsw1.domain;

import br.ufscar.dsw1.domain.User;
import br.ufscar.dsw1.domain.Forum;
import br.ufscar.dsw1.domain.Topic;

public class Post {
    private Long id;
    private Long id_autor;
    private Long id_forum;
    private Long id_topico;
    private String titulo;
    private String conteudo;
    private User autor;
    private Forum forum;
    private Topic topico;
    private int sessionUserReaction;

    public Post(Long id_autor, Long id_forum, Long id_topico, String titulo, String conteudo, int sessionUserReaction) {
        this.id_autor = id_autor;
        this.id_forum = id_forum;
        this.id_topico = id_topico;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.sessionUserReaction = sessionUserReaction;
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

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Topic getTopico() {
        return topico;
    }

    public void setTopico(Topic topico) {
        this.topico = topico;
    }

    public int getSessionUserReaction() {
        return sessionUserReaction;
    }

    public void setSessionUserReaction(int sessionUserReaction) {
        this.sessionUserReaction = sessionUserReaction;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", id_autor=" + id_autor +
                ", id_forum=" + id_forum +
                ", id_topico=" + id_topico +
                ", titulo='" + titulo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", autor=" + autor +
                ", forum=" + forum +
                ", topico=" + topico +
                ", sessionUserReaction=" + sessionUserReaction +
                '}';
    }
}
