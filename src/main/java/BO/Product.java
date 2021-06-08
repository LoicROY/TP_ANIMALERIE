package BO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProdType type;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<PetStore> petStores;

    {
        this.petStores = new HashSet<>();
    }

    public Product() {
    }

    public Product(String code, String label, ProdType type, double price) {
        this.code = code;
        this.label = label;
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProdType getType() {
        return type;
    }

    public void setType(ProdType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<PetStore> getPetStores() {
        return petStores;
    }

    public void setPetStore(Set<PetStore> petStores) {
        this.petStores = petStores;
    }

    public void addPetStore(PetStore petStore){
        if (petStore != null){
            petStore.getProducts().add(this);
            this.petStores.add(petStore);
        }
    }

    public void removePetStores(PetStore petStore){
        if (petStore != null){
            petStore.getProducts().remove(this);
            this.petStores.remove(petStore);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", petStores=" + petStores.size() +
                '}';
    }
}
