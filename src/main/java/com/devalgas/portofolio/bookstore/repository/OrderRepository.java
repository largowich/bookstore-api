package com.devalgas.portofolio.bookstore.repository;

import com.devalgas.portofolio.bookstore.domain.Order;
import com.devalgas.portofolio.bookstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author devalgas kamga on 13/01/2022. 23:39
 * <p>
 * Spring Data SQL repository for the Order entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
