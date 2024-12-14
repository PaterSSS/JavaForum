package org.test.models.DTOs;

//подумать о record
public class PostDTO {
    private final int postId;
    private final String title;
    private final String content;
    private final int authorId;
    private final String authorName;
    private final String creationDate;


    public PostDTO(int postId, String title, String content, int authorId, String authorName, String creationDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.authorName = authorName;
        this.creationDate = creationDate;
    }
}
