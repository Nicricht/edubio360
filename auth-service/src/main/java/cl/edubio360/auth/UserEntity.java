package cl.edubio360.auth;

import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_users_email", columnNames = "email"))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 180)
    private String email;

    @Column(nullable = false, length = 120)
    private String passwordHash;

    @Column(nullable = false, length = 30)
    private String role;

    @Column(nullable = false)
    private boolean active = true;

    protected UserEntity() {}

    public UserEntity(String email, String passwordHash, String role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = true;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }
}
