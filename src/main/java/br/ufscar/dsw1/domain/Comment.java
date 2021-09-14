package br.ufscar.dsw1.domain;

public class Comment {
    private long id;
    private Post originalPost;
    private String content;

    public Comment(long id, String content, Post originalPost) {
        this.originalPost = originalPost;
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
}
