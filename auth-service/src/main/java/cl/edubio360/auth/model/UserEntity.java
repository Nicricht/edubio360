package cl.edubio360.auth.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();

    protected UserEntity() {}

    public UserEntity(String email, String passwordHash, String role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = true;
        addRole(role);
    }

    public void actualizar(String email, String passwordHash, String role, boolean active) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = active;
        replaceRoles(List.of(role));
    }

    public void addRole(String role) {
        UserRole assignment = new UserRole(role, this);
        roles.add(assignment);
    }

    public void replaceRoles(List<String> nuevas) {
        roles.clear();
        if (nuevas != null) nuevas.forEach(this::addRole);
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isActive() { return active; }
    public List<UserRole> getRoles() { return roles; }
    public String getPrimaryRole() { return roles.isEmpty() ? "STUDENT" : roles.get(0).getName(); }
}
