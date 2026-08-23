package com.plantintelligence.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * JPA entity representing a row in the {@code users} database table.
 *
 * <h3>Design notes</h3>
 * <ul>
 *   <li>Schema is created by the Flyway migration {@code V1__create_users_table.sql};
 *       Hibernate only validates the mapping ({@code ddl-auto=validate}).</li>
 *   <li>{@link UserRole} is stored as a VARCHAR string, not an ordinal integer,
 *       so the persisted value is human-readable and ordinal-stable.</li>
 *   <li>{@code passwordHash} must contain a securely hashed password (e.g. BCrypt).
 *       Plain-text passwords must never be stored here.</li>
 *   <li>{@code equals} and {@code hashCode} are based on the database-assigned
 *       {@code id} only, following the recommended JPA entity identity strategy.
 *       This avoids LazyInitializationExceptions and recursive loop issues that
 *       can occur when mutable fields are included.</li>
 *   <li>{@code toString()} deliberately omits {@code passwordHash} to prevent
 *       accidental exposure in logs.</li>
 *   <li>Lombok {@code @Getter} is used for concise field accessors. Setters,
 *       constructors, equals, hashCode, and toString are written explicitly to
 *       maintain JPA safety and security control.</li>
 * </ul>
 */
@Entity
@Table(name = "users")
public class User {

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    /**
     * Stores the BCrypt (or equivalent) hash of the user's password.
     * This field MUST NOT be logged, serialised to JSON without explicit
     * exclusion, or exposed through {@link #toString()}.
     */
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    /**
     * The user's access role, persisted as a VARCHAR string so that the stored
     * value is human-readable and immune to ordinal shifts when new enum values
     * are inserted.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 30)
    private UserRole role;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * No-argument constructor required by the JPA specification.
     * Not intended for direct use in application code.
     */
    protected User() {
    }

    /**
     * Creates a new {@code User} ready to be persisted. The {@code id} is
     * intentionally omitted because it is assigned by the database on INSERT.
     *
     * @param username     unique display name (max 50 chars, not null)
     * @param email        unique email address (max 255 chars, not null)
     * @param passwordHash pre-computed password hash — never a plain-text password
     * @param role         the access role granted to this user
     */
    public User(String username, String email, String passwordHash, UserRole role) {
        this.username     = username;
        this.email        = email;
        this.passwordHash = passwordHash;
        this.role         = role;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /** @return the database-assigned primary key, or {@code null} before first persist */
    public Long getId() {
        return id;
    }

    /** @return the unique username */
    public String getUsername() {
        return username;
    }

    /** @return the unique email address */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the stored password hash.
     *
     * <p><strong>Security reminder:</strong> do not log or serialise this value
     * without explicit exclusion. Use only for password verification.
     *
     * @return the hashed password string
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /** @return the access role assigned to this user */
    public UserRole getRole() {
        return role;
    }

    // -------------------------------------------------------------------------
    // Setters (id intentionally omitted — assigned by the database)
    // -------------------------------------------------------------------------

    /**
     * Updates the username.
     *
     * @param username new unique username (max 50 chars, not null)
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Updates the email address.
     *
     * @param email new unique email (max 255 chars, not null)
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Replaces the stored password hash.
     *
     * @param passwordHash new pre-computed password hash — never plain-text
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Updates the user's access role.
     *
     * @param role the new role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    // -------------------------------------------------------------------------
    // equals / hashCode — identity based on database-assigned id only
    // -------------------------------------------------------------------------

    /**
     * Two {@code User} instances are considered equal if and only if they have
     * the same non-null database-assigned {@code id}. Transient instances
     * (id == null) are only equal to themselves.
     *
     * <p>This strategy is recommended for JPA entities to avoid issues with
     * lazy initialisation, mutable state, and Hibernate proxy objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        // Two transient (unsaved) entities are not equal unless they are the same object.
        if (id == null || other.id == null) {
            return false;
        }
        return id.equals(other.id);
    }

    /**
     * Returns a stable hash code based on the entity type so that transient
     * and persisted instances remain in the same hash bucket within a
     * collection while being safe across all JPA lifecycle states.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // -------------------------------------------------------------------------
    // toString — passwordHash is intentionally excluded for security
    // -------------------------------------------------------------------------

    /**
     * Returns a human-readable representation of this user.
     *
     * <p><strong>Security:</strong> {@code passwordHash} is deliberately
     * excluded to prevent accidental exposure in application logs.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                // passwordHash is intentionally omitted — never log hashed passwords
                '}';
    }
}
