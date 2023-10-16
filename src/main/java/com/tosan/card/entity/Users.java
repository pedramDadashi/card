package com.tosan.card.entity;


import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.entity.enumuration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Users extends BaseEntity<Long> implements UserDetails {

    String firstname;
    String lastname;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String nationalCode;
    String password;
    Boolean isActive;
    @Enumerated(value = EnumType.STRING)
    Role role;

    protected Users(String firstname, String lastname, String email,
                    String nationalCode, String password, boolean isActive, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nationalCode = nationalCode;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
