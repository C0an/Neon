package cc.peko.neon;

import lombok.Getter;

@Getter
public class ForumPost {

    private String title, directURL, author, authorUUID, content;
    private long createdAt, lastUpdate;
    private boolean success;
    private String message;

    public ForumPost(String title, String directURL, String author, String authorUUID, String content, long createdAt, long lastUpdate, boolean success, String message) {
        this.title = title;
        this.directURL = directURL;
        this.author = author;
        this.authorUUID = authorUUID;
        this.content = content;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.success = success;
        this.message = message;
    }


}
