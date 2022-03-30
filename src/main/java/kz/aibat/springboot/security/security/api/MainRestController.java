package kz.aibat.springboot.security.security.api;

import kz.aibat.springboot.security.security.model.Item;
import kz.aibat.springboot.security.security.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@RequiredArgsConstructor
public class MainRestController {

    private final ItemService itemService;

    @GetMapping(value="/allitems")
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @PostMapping(value="/additem")
    public ResponseEntity<String> addItem(@RequestParam(name = "name") String name,
                                          @RequestParam(name = "model") String model,
                                          @RequestParam(name = "price") int price){

        Item item = new Item();
        item.setName(name);
        item.setModel(model);
        item.setPrice(price);
        itemService.addItem(item);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
