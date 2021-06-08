package BO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FISH")
public class Fish extends Animal {

    @Column(name = "LIVING_ENV", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private FishLivEnv livingEnv;

    public Fish() {
    }

    public Fish(LocalDate birth, String color, FishLivEnv livingEnv) {
        super(birth, color);
        this.livingEnv = livingEnv;
    }

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "livingEnv=" + livingEnv +
                '}';
    }
}
