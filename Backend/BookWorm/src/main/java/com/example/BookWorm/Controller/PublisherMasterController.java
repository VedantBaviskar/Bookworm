package com.example.BookWorm.Controller;



import com.example.BookWorm.models.PublisherMaster;
import com.example.BookWorm.service.PublisherMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishers")
public class PublisherMasterController {

    @Autowired
    private PublisherMasterService service;

    @GetMapping("/get")
    public List<PublisherMaster> getAllPublishers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherMaster> getPublisherById(@PathVariable int id) {
        Optional<PublisherMaster> publisher = service.findById(id);
        if (publisher.isPresent()) {
            return ResponseEntity.ok(publisher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public PublisherMaster createPublisher(@RequestBody PublisherMaster publisher) {
        return service.save(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherMaster> updatePublisher(@PathVariable int id, @RequestBody PublisherMaster publisherDetails) {
        Optional<PublisherMaster> publisher = service.findById(id);
        if (publisher.isPresent()) {
            PublisherMaster updatedPublisher = publisher.get();
            updatedPublisher.setName(publisherDetails.getName());
            return ResponseEntity.ok(service.save(updatedPublisher));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        Optional<PublisherMaster> publisher = service.findById(id);
        if (publisher.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
