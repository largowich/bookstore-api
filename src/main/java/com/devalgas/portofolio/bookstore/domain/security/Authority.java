package com.devalgas.portofolio.bookstore.domain.security;

import com.devalgas.portofolio.bookstore.domain.AbstractAuditingEntity;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @author devalgas kamga on 13/01/2022. 20:48
 */
public class Authority extends AbstractAuditingEntity implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 12312345L;

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

