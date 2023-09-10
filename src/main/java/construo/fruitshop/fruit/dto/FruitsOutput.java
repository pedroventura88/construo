package construo.fruitshop.fruit.dto;

import construo.fruitshop.common.dto.PageDto;
import lombok.Data;

import java.util.List;

@Data
public class FruitsOutput {
    private List<FruitOutput> items;
    private PageDto page;

    public FruitsOutput(List<FruitOutput> items) {
        this.items = items;
    }
}
