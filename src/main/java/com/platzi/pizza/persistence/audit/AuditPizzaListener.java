package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

import javax.sound.midi.Soundbank;
import java.io.Serializable;

public class AuditPizzaListener {

    private PizzaEntity pizzaEntity;

    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity){
        System.out.println("POST LOAD");
        this.pizzaEntity = SerializationUtils.clone(pizzaEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity){
        System.out.println("POST PERSIST - UPDATE");
        System.out.println("Old value" + this.pizzaEntity);
        System.out.println("New value" + pizzaEntity);
    }

    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity){
        System.out.println(this.pizzaEntity );
    }

}
