package construo.fruitshop;

import construo.fruitshop.fruit.FruitEntity;
import construo.fruitshop.fruit.FruitRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataInitializer {

    private FruitRepository fruitRepository;


    public DataInitializer(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
        mockFruits();
    }

    private void mockFruits() {
        List<FruitEntity> fruits = new ArrayList<>();
        FruitEntity f1 = new FruitEntity();
        f1.setId(1L);
        f1.setName("banana");
        f1.setPrice(2.99);
        f1.setCreationTime(LocalDateTime.of(2022,2,22,10,59));
        f1.setUpdateTime(LocalDateTime.of(2022,2,22,10,59));
        f1.setIsAvailable(true);
        f1.setLevelOfSweet(8);
        fruits.add(f1);

        FruitEntity f2 = new FruitEntity();
        f2.setId(2L);
        f2.setName("jaca");
        f2.setPrice(3.14);
        f2.setCreationTime(LocalDateTime.of(2021,2,22,10,59));
        f2.setUpdateTime(LocalDateTime.of(2021,2,22,10,59));
        f2.setIsAvailable(true);
        f2.setLevelOfSweet(9);
        fruits.add(f2);
        fruits.forEach(fruit -> fruitRepository.save(fruit));
    }


}
