package BO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "CAT")
public class Cat extends Animal {

    @Column(name = "CHIP_ID", nullable = false)
    private String chipId;

    public Cat() {
    }

    public Cat(LocalDate birth, String color, String chipId) {
        super(birth, color);
        this.chipId = chipId;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "chipId='" + chipId + '\'' +
                '}';
    }
}
