package construo.fruitshop.fruit;

import construo.fruitshop.common.dto.ErrorResponse;
import construo.fruitshop.fruit.dto.FruitInput;
import construo.fruitshop.fruit.dto.FruitOutput;
import construo.fruitshop.fruit.dto.FruitsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    private static final Logger LOG = LoggerFactory.getLogger(FruitController.class);

    private FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllFruits(
            @RequestParam(required = false) Optional<Integer> pageNumber,
            @RequestParam(required = false) Optional<Integer> pageSize) {

        try {
            FruitsOutput fruitPage = fruitService.getAll(pageNumber, pageSize);
            return ResponseEntity.ok(fruitPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error", e.toString()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> createFruit(@RequestBody FruitInput fruitInput) {
        try {
            validateInput(fruitInput);
            FruitOutput fruitCreated = fruitService.createFruit(fruitInput);
            return ResponseEntity.ok(fruitCreated);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Validation Error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", e.toString()));
        }
    }

    private void validateInput(FruitInput fruitInput) throws ValidationException {
        if (StringUtils.isEmpty(fruitInput.getName()) || fruitInput.getPrice() == null) {
            throw new ValidationException("Some mandatory fields are missing");
        }
    }

    @DeleteMapping("/{fruitId}")
    public ResponseEntity<String> deleteFruitById(@PathVariable Long fruitId) {
        try {
            boolean deleted = fruitService.deleteFruitById(fruitId);

            if (deleted) {
                return ResponseEntity.ok("Fruit with ID " + fruitId + " deleted successfully");
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