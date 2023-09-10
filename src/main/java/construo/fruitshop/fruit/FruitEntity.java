package construo.fruitshop.fruit;

import construo.fruitshop.common.entity.ItemBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "fruit")
@NoArgsConstructor
public class FruitEntity extends ItemBaseEntity {
    private Integer levelOfSweet; // 1 to 10
}

