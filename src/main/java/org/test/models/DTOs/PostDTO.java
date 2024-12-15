package org.test.models.DTOs;

//подумать о record
public record PostDTO(int postId, String title, String content, int authorId, String authorName, String creationDate) {
}
