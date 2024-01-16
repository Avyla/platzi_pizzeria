package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    /* Si se utiliza findAll Traer toda los registros que cumplan las condiciones
    Sin embargo si se utiliza findFirst o findTop solo se traera el primer registro que cumpla las condiciones */
    //PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String ingrediente);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingrediente);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

    Integer countByVeganTrue();

//    @Query(value = "UPDATE pizza " +
//                   "SET price = :newPrice " +
//                   "WHERE id_pizza = :idPizza ",nativeQuery = true)
//    void updatePrice(@Param("newPrice") Integer newPrice,
//                     @Param("idPizza") Integer idPrizza);

    @Query(value = "UPDATE pizza " +
            "SET price = :#{#newPizza.newPrice} " +
            "WHERE id_pizza = :#{#newPizza.idPizza}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizza") UpdatePizzaPriceDto newPizza);
}
