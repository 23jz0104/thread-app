package dto;

import java.io.Serializable;

import model.Post;
import model.User;

/**
 * 投稿表示用DTO
 */
public class PostDTO implements Serializable {
    private User user;
    private Post post;

    /**
     * コンストラクタ
     */
    public PostDTO() {

    }

    public PostDTO(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
