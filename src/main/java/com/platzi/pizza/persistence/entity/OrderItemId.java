package com.platzi.pizza.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemId implements Serializable {

    private Integer idItem;
    private Integer idOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId that)) return false;
        return Objects.equals(idItem, that.idItem) && Objects.equals(idOrder, that.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idOrder);
    }
}
