package com.example.BookWorm.service;



import com.example.BookWorm.models.PublisherMaster;
import com.example.BookWorm.repository.PublisherMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherMasterService {

    @Autowired
    private PublisherMasterRepository repository;

    public List<PublisherMaster> findAll() {
        return repository.findAll();
    }

    public Optional<PublisherMaster> findById(int id) {
        return repository.findById(id);
    }

    public PublisherMaster save(PublisherMaster publisher) {
        return repository.save(publisher);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
