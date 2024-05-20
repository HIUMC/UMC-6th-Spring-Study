package jpabook.jpashop.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.Getter;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
