package cc.peko.neon;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ForumPost {

    private String title, directURL, author;
    private UUID authorUUID;
    private long createdAt, lastUpdated;

    public ForumPost(String title, String directURL, String author, UUID authorUUID, long createdAt, long lastUpdated) {
        this.title = title;
        this.directURL = directURL;
        this.author = author;
        this.authorUUID = authorUUID;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

}
