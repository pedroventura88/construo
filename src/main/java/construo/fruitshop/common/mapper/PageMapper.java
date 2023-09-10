package construo.fruitshop.common.mapper;

import construo.fruitshop.common.dto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class PageMapper {

    public static Integer setDefaultPageSize(){
        return 10;
    }

    public static Integer setDefaultPageNumber() {
        return 1;
    }

    public static PageRequest toPageable(Integer pageNumber, Integer pageSize) {
        return pageNumber != null ? PageRequest.of(Math.max(1, pageNumber) - 1, pageSize) : PageRequest.ofSize(pageSize);
    }

    public static PageDto toPageDto(Page<?> page) {
        return PageDto.builder()
                .pageSize(page.getSize())
                .pageNumber(page.getNumber() + 1)
                .itemCount(Long.valueOf(page.getTotalElements()).intValue())
                .build();
    }
}
