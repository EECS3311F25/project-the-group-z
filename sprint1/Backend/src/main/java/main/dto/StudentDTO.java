package main.dto;

/**
 * Basic data sent to frontend.
 */
public record StudentDTO(
    Long studentNumber,
    String firstName,
    String lastName,
    String username,
    String email,
    String major
) {}
