import BO.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class main {

    private static EntityManagerFactory entityManagerFactory;
    private static String FIND_ANIMALS_BY_PET_STORE = "SELECT a FROM Animal a WHERE a.petStore.id = :id";

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("petStore");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        init();
        List<Animal> animals = findAnimalsByPetStore(entityManager.find(PetStore.class, 1L));
        System.out.println(animals);

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void init() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Address address1 = new Address("1", "rue de la plage", "44000", "Nantes");
        Address address2 = new Address("2", "rue de la paix", "44000", "Nantes");
        Address address3 = new Address("3", "rue de la mairie", "44000", "Nantes");

        Animal animal1 = new Animal(LocalDate.now(), "green");
        Animal animal2 = new Animal(LocalDate.now(), "blue");
        Animal animal3 = new Animal(LocalDate.now(), "purple");

        Animal cat1 = new Cat(LocalDate.now(), "black", "123");
        Animal cat2 = new Cat(LocalDate.now(), "white", "456");
        Animal cat3 = new Cat(LocalDate.now(), "Black", "789");

        Animal fish1 = new Fish(LocalDate.now(), "red", FishLivEnv.FRESH_WATER);
        Animal fish2 = new Fish(LocalDate.now(), "yellow", FishLivEnv.SEA_WATER);
        Animal fish3 = new Fish(LocalDate.now(), "green", FishLivEnv.FRESH_WATER);

        Product product1 = new Product("123", "boite de thon", ProdType.FOOD, 5);
        Product product2 = new Product("456", "laisse", ProdType.ACCESSORY, 5);
        Product product3 = new Product("789", "sac à crotte", ProdType.CLEANING, 5);

        PetStore petStore1 = new PetStore("pet1", "Durand", address1);
        petStore1.addProduct(product1);
        petStore1.addProduct(product2);
        petStore1.addProduct(product3);
        petStore1.addAnimal(animal1);
        petStore1.addAnimal(animal2);
        petStore1.addAnimal(animal3);

        PetStore petStore2 = new PetStore("pet2", "Halliday", address2);
        petStore2.addProduct(product1);
        petStore2.addProduct(product2);
        petStore2.addProduct(product3);
        petStore2.addAnimal(cat1);
        petStore2.addAnimal(cat2);
        petStore2.addAnimal(cat3);

        PetStore petStore3 = new PetStore("pet3", "Semoun", address3);
        petStore3.addProduct(product1);
        petStore3.addProduct(product2);
        petStore3.addProduct(product3);
        petStore3.addAnimal(fish1);
        petStore3.addAnimal(fish2);
        petStore3.addAnimal(fish3);

        em.persist(petStore1);
        em.persist(petStore2);
        em.persist(petStore3);

        em.getTransaction().commit();
        em.close();
    }

    public static List<Animal> findAnimalsByPetStore(PetStore petStore) {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Animal> query = em.createQuery(FIND_ANIMALS_BY_PET_STORE, Animal.class);
        query.setParameter("id", petStore.getId());
        return query.getResultList();
    }
}
