package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.Setter;
import lombok.Getter;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item{

    private String artist;
    private String etc;
}
