package com.plantintelligence.backend.repository;

import com.plantintelligence.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 *
 * <p>Provides standard CRUD operations inherited from {@link JpaRepository}.
 * This repository was introduced alongside the {@code users} table migration
 * (V1) to enable meaningful persistence tests. It is intentionally minimal —
 * no custom queries are added here beyond the scope of the Users DB issue.
 *
 * <p>Additional query methods (e.g. findByUsername, findByEmail) should be
 * added in a separate feature branch once authentication is implemented.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
