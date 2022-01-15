package com.devalgas.portofolio.bookstore.domain.security;

import com.devalgas.portofolio.bookstore.domain.AbstractAuditingEntity;
import com.devalgas.portofolio.bookstore.domain.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author devalgas kamga on 13/01/2022. 20:51
 */
@Entity
@Table(name = "user_role")
public class UserRole extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 8903456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userRoleId;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Role role;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
