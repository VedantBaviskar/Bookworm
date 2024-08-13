package com.example.BookWorm.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookWorm.models.AttributeMaster;
import com.example.BookWorm.models.ProductType;
import com.example.BookWorm.service.AttributeMasterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attributes")
@CrossOrigin(origins = "*")  // Enable CORS for all origins
public class AttributeMasterController {

    @Autowired
    private AttributeMasterService attributeMasterService;

    @GetMapping
    public List<AttributeMaster> getAllAttributes() {
        return attributeMasterService.getAllAttributes();
    }

    @GetMapping("/{id}")
    public Optional<AttributeMaster> getAttributeById(@PathVariable int id) {
        return attributeMasterService.getAttributeById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AttributeMaster> createAttributeType(@RequestBody AttributeMaster AttributeType) {
    	AttributeMaster savedProductType = attributeMasterService.saveAttribute(AttributeType);
        return new ResponseEntity<>(savedProductType, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public AttributeMaster updateAttribute(@PathVariable int id, @RequestBody AttributeMaster attributeMaster) {
       
        attributeMaster.setAttributeId(id);
        return attributeMasterService.saveAttribute(attributeMaster);
    }

    @DeleteMapping("/{id}")
    public void deleteAttribute(@PathVariable int id) {
        attributeMasterService.deleteAttribute(id);
    }
}
