package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author devalgas kamga on 13/01/2022. 23:34
 * <p>
 * Spring Data JPA repository for the {@link Role} entity.
 */
public interface RoleRepository extends JpaRepository<Role, String> {
}
