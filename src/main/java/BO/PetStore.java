package BO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PET_STORE")
public class PetStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "MANAGER_NAME", nullable = false)
    private String managerName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "PRODUCT_PET_STORE",
            joinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PET_STORE", referencedColumnName = "ID"))
    private Set<Product> products;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.PERSIST)
    private Set<Animal> animals;


    {
        this.products = new HashSet<>();
        this.animals = new HashSet<>();
    }

    public PetStore() {
    }

    public PetStore(String name, String managerName, Address address) {
        this.name = name;
        this.managerName = managerName;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public void addProduct(Product product) {
        if (product != null) {
            product.addPetStore(this);
        }
    }

    public void removeProduct(Product product) {
        if (product != null) {
            product.removePetStores(this);
        }
    }

    public void addAnimal(Animal animal) {
        if (animal != null) {
            animal.setPetStore(this);
        }
    }

    public void removeAnimal(Animal animal) {
        if (animal != null) {
            animal.setPetStore(null);
        }
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerName='" + managerName + '\'' +
                ", products=" + products +
                ", address=" + address +
                ", animals=" + animals +
                '}';
    }
}
