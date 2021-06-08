package BO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ANIMAL")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "BIRTH", nullable = false)
    private LocalDate birth;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @ManyToOne
    private PetStore petStore;

    public Animal() {
    }

    public Animal(LocalDate birth, String color) {
        this.birth = birth;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        if (this.petStore != null){
            this.petStore.getAnimals().remove(this);
        }
        this.petStore = petStore;
        if (petStore != null){
            petStore.getAnimals().add(this);
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", birth=" + birth +
                ", color='" + color + '\'' +
                ", petStore=" + petStore.getId() +
                '}';
    }
}
