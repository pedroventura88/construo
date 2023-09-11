package construo.fruitshop.fruit;

import construo.fruitshop.common.dto.ErrorResponse;
import construo.fruitshop.fruit.dto.FruitDto;
import construo.fruitshop.fruit.dto.FruitsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    private FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllFruits(
            @RequestParam(required = false) Optional<Integer> pageNumber,
            @RequestParam(required = false) Optional<Integer> pageSize) {

        try {
            FruitsDto fruitPage = fruitService.getAll(pageNumber, pageSize);
            return ResponseEntity.ok(fruitPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error", e.toString()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> createFruit(@RequestBody FruitDto fruitInput) {
        try {
            validateInput(fruitInput);
            FruitDto fruitCreated = fruitService.createFruit(fruitInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(fruitCreated);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Validation Error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", e.toString()));
        }
    }

    private void validateInput(FruitDto fruitInput) throws ValidationException {
        if (StringUtils.isEmpty(fruitInput.getName()) || fruitInput.getPrice() == null) {
            throw new ValidationException("Some mandatory fields are missing");
        }
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<?> deleteFruitById(@PathVariable Long id) {
        try {
            boolean deleted = fruitService.deleteFruitById(id);

            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the fruit");
        }
    }


}
