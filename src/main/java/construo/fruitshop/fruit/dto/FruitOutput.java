package construo.fruitshop.fruit.dto;

import construo.fruitshop.common.dto.ItemBaseDto;
import lombok.Data;

@Data
public class FruitOutput extends ItemBaseDto {
    private Integer levelOfSweet;
}
