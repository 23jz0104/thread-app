package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post implements Serializable {

    private int           id;
    private int           userId;
    private String        title;
    private String        content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Post() {

    }

    public Post(int id, int userId, String title, String content, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super();
        this.id          = id;
        this.userId      = userId;
        this.title       = title;
        this.content     = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content + ", createdDate=" + createdDate
                + ", updatedDate=" + updatedDate + "]";
    }

    public String getFormattedDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
