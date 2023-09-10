package construo.fruitshop.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDto {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer itemCount;
}
