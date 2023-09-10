package construo.fruitshop.fruit.impl;

import construo.fruitshop.common.mapper.PageMapper;
import construo.fruitshop.fruit.*;
import construo.fruitshop.fruit.dto.FruitInput;
import construo.fruitshop.fruit.dto.FruitOutput;
import construo.fruitshop.fruit.dto.FruitsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class FruitServiceImpl implements FruitService {
    private static final Logger LOG = LoggerFactory.getLogger(FruitServiceImpl.class);

    private FruitRepository fruitRepository;
    private FruitMapper mapper;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
        this.mapper = new FruitMapper();
    }

    @Override
    public FruitsOutput getAll(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        if (!pageNumber.isPresent()) {
            pageNumber = Optional.of(PageMapper.setDefaultPageNumber());
        }

        if (!pageSize.isPresent()) {
            pageSize = Optional.of(PageMapper.setDefaultPageSize());
        }

        Pageable pageable = PageMapper.toPageable(pageNumber.get(), pageSize.get());
        Page<FruitEntity> pageFruitEntity = fruitRepository.findAll(pageable);
        Page<FruitOutput> pageFruitDto = pageFruitEntity.map(fruit -> mapper.toDto(fruit));

        FruitsOutput fruitsOutput = new FruitsOutput(pageFruitDto.getContent());
        fruitsOutput.setPage(PageMapper.toPageDto(pageFruitDto));
        return fruitsOutput;
    }


    public FruitOutput createFruit(FruitInput fruit) {
        validateFruitInput(fruit);

        FruitEntity entity = new FruitEntity();
        entity.setLevelOfSweet(fruit.getLevelOfSweet());
        entity.setName(fruit.getName());
        entity.setIsAvailable(true);
        entity.setPrice(fruit.getPrice());

        try {
            FruitEntity fruitCreated = fruitRepository.save(entity);
            return mapper.toDto(fruitCreated);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error creating fruit: Duplicate entry or data integrity violation.", e);
            throw new IllegalArgumentException("Fruit data violates integrity constraints.");
        } catch (Exception e) {
            LOG.error("Error creating fruit: Unexpected error occurred.", e);
            throw new RuntimeException("An unexpected error occurred while creating the fruit.");
        }
    }

    @Override
    public boolean deleteFruitById(Long fruitId) {
        try {
            fruitRepository.deleteById(fruitId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Fruit with ID " + fruitId + " not found for deletion.");
        } catch (Exception e) {
            // Handle other exceptions, e.g., database errors
            throw new RuntimeException("An error occurred while deleting the fruit with ID " + fruitId + ".", e);
        }
    }


    private void validateFruitInput(FruitInput fruit) {
        if (StringUtils.isEmpty(fruit.getName()) || fruit.getPrice() <= 0) {
            throw new IllegalArgumentException("Fruit name must not be empty, and price must be greater than zero.");
        }
    }

}
