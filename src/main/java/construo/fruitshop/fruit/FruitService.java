package construo.fruitshop.fruit;

import construo.fruitshop.fruit.dto.FruitInput;
import construo.fruitshop.fruit.dto.FruitOutput;
import construo.fruitshop.fruit.dto.FruitsOutput;

import java.util.Optional;

public interface FruitService {
    FruitsOutput getAll(Optional<Integer> pageNumber, Optional<Integer> pageSize);
    FruitOutput createFruit(FruitInput inputFruit);

    boolean deleteFruitById(Long fruitId);
}
