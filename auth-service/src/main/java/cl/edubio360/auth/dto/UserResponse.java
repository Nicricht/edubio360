package cl.edubio360.auth.dto;

public record UserResponse(Long id, String email, String role, boolean active) {
}
