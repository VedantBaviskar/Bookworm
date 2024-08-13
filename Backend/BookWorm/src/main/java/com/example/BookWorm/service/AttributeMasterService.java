package com.example.BookWorm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BookWorm.models.AttributeMaster;
import com.example.BookWorm.repository.AttributeMasterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeMasterService {

    @Autowired
    private AttributeMasterRepository attributeMasterRepository;

    public List<AttributeMaster> getAllAttributes() {
        return attributeMasterRepository.findAll();
    }

    public Optional<AttributeMaster> getAttributeById(int id) {
        return attributeMasterRepository.findById(id);
    }

    public AttributeMaster saveAttribute(AttributeMaster attributeMaster) {
        
          
   
        return attributeMasterRepository.save(attributeMaster);
    }

    public void deleteAttribute(int id) {
        attributeMasterRepository.deleteById(id);
    }
}
