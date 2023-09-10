package construo.fruitshop.fruit;

import construo.fruitshop.common.mapper.PageMapper;
import construo.fruitshop.fruit.dto.FruitOutput;

public class FruitMapper extends PageMapper {

    public FruitOutput toDto(FruitEntity entity) {
        FruitOutput dto = new FruitOutput();
        dto.setName(entity.getName());
        dto.setLevelOfSweet(entity.getLevelOfSweet());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
