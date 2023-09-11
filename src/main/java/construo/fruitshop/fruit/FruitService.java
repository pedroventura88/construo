package construo.fruitshop.fruit;

import construo.fruitshop.fruit.dto.FruitDto;
import construo.fruitshop.fruit.dto.FruitsDto;

import java.util.Optional;

public interface FruitService {
    FruitsDto getAll(Optional<Integer> pageNumber, Optional<Integer> pageSize);
    FruitDto createFruit(FruitDto inputFruit);
    boolean deleteFruitById(Long id);
}
