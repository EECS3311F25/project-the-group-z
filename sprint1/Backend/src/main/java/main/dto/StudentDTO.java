package main.dto;

/**
 Basic data sent to frontend.
 */
public record StudentDTO(Long studentNumber, String firstName, String lastName, String userName, String email, String major) {}

