package com.platzi.pizza.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserRolId implements Serializable {


    private String username;
    private String rol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRolId userRolId)) return false;
        return Objects.equals(getUsername(), userRolId.getUsername()) && Objects.equals(getRol(), userRolId.getRol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getRol());
    }
}
