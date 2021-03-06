package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @author devalgas kamga on 13/01/2022. 23:46
 * <p>
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    String USERS_BY_LOGIN_CACHE = "usersByLogin";
//
//    String USERS_BY_EMAIL_CACHE = "usersByEmail";
//
//    Optional<User> findOneByActivationKey(String activationKey);
//
//    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);
//
//    Optional<User> findOneByResetKey(String resetKey);
//
//    Optional<User> findOneByEmailIgnoreCase(String email);
//
//    Optional<User> findOneByLogin(String login);
//
//    @EntityGraph(attributePaths = "authorities")
//    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
//    Optional<User> findOneWithAuthoritiesByLogin(String login);
//
//    @EntityGraph(attributePaths = "authorities")
//    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
//    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);
//
//    Page<User> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
