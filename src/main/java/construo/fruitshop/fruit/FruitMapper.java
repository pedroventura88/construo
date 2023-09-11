package construo.fruitshop.fruit;

import construo.fruitshop.common.mapper.PageMapper;
import construo.fruitshop.fruit.dto.FruitDto;

public class FruitMapper extends PageMapper {

    public FruitDto toDto(FruitEntity entity) {
        FruitDto dto = new FruitDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevelOfSweet(entity.getLevelOfSweet());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
