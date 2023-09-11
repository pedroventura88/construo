package construo.fruitshop.fruit.dto;

import construo.fruitshop.common.dto.PageDto;
import lombok.Data;

import java.util.List;

@Data
public class FruitsDto {
    private List<FruitDto> items;
    private PageDto page;

    public FruitsDto(List<FruitDto> items) {
        this.items = items;
    }
}
