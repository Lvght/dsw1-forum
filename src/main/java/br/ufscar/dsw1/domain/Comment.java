package br.ufscar.dsw1.domain;

public class Comment {
    private long id;
    private User author;
    private Post originalPost;
    private String content;

    public Comment(long id, String content, User author, Post originalPost) {
        this.originalPost = originalPost;
        this.author = author;
        this.content = content;
        this.id = id;
    }

    public Post getOriginalPost() {
        return originalPost;
    }

    public void setOriginalPost(Post originalPost) {
        this.originalPost = originalPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", originalPost=" + originalPost +
                ", content='" + content + '\'' +
                '}';
    }
}
