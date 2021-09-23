package br.ufscar.dsw1.domain;

public class Reaction {
    Post post;
    User author;
    int type;

    public Reaction(Post post, User author, int type) {
        this.post = post;
        this.author = author;
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "post=" + post +
                ", author=" + author +
                ", type=" + type +
                '}';
    }
}
