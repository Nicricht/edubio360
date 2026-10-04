package cl.edubio360.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 30)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    protected UserRole() {}

    public UserRole(String name, UserEntity user) {
        this.name = name;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public UserEntity getUser() { return user; }
}
