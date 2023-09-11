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

    public void mockFruits() {
        List<FruitEntity> fruits = new ArrayList<>();

        for (int id = 1; id <= 50; id++) {
            FruitEntity fruit = new FruitEntity();
            fruit.setId(Long.valueOf(id));
            fruit.setName("fruit_" + id);
            fruit.setPrice(2.99 + (id * 0.1)); // Price increases by 0.1 for each fruit
            fruit.setCreationTime(LocalDateTime.now());
            fruit.setUpdateTime(LocalDateTime.now());
            fruit.setIsAvailable(id % 2 == 0); // Alternate availability
            fruit.setLevelOfSweet(id % 10); // Levels of sweetness from 0 to 9
            fruits.add(fruit);
        }

        fruitRepository.saveAll(fruits);
    }


}
