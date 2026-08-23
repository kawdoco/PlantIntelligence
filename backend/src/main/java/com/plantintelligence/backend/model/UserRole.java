package com.plantintelligence.backend.model;

/**
 * Represents the access role assigned to a PlantIntelligence user.
 *
 * <p>This enum is persisted as a VARCHAR string in the database (never as an
 * ordinal integer). Adding new roles requires a database migration to update the
 * {@code role} column's effective value set, but no schema change is required
 * because the column is typed VARCHAR(30).
 *
 * <p>Assumed initial roles — update this enum and document in the README when
 * the team agrees on additional roles:
 * <ul>
 *   <li>{@link #USER}  — standard authenticated user</li>
 *   <li>{@link #ADMIN} — administrator with elevated privileges</li>
 * </ul>
 */
public enum UserRole {

    /** Standard authenticated end-user. */
    USER,

    /** Administrator with elevated system privileges. */
    ADMIN
}
