package construo.fruitshop.fruit.dto;

import lombok.Data;

@Data
public class FruitInput {
    private String name;
    private Double price;
    private Integer levelOfSweet;
}
