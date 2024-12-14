package org.test.models.DTOs;

//пока оставлю так, но можно сделать record, чтобы так много места не занимал код
public record UserProfileDTO(String username, String registrationDate, String bio) {

}
