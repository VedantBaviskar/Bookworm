package com.example.BookWorm.Controller;


import com.example.BookWorm.models.BeneficiaryMaster;
import com.example.BookWorm.models.ProdBeneficiaryMaster;
import com.example.BookWorm.service.BeneficiaryMasterService;
import com.example.BookWorm.service.ProductBeneficiaryMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beneficiaries")
public class BeneficiaryMasterController {

    @Autowired
    private BeneficiaryMasterService beneficiaryMasterService;

    @GetMapping
    public List<BeneficiaryMaster> getAllBeneficiaries() {
        return beneficiaryMasterService.getAllBeneficiaries();
    }
//    @Autowired
//    private ProdBeneficiaryMaster prodBeneficiaryMasterRepository;

   
    @GetMapping("/{id}")
    public ResponseEntity<BeneficiaryMaster> getBeneficiaryById(@PathVariable Long id) {
        Optional<BeneficiaryMaster> beneficiary = beneficiaryMasterService.getBeneficiaryById(id);
        return beneficiary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BeneficiaryMaster createBeneficiary(@RequestBody BeneficiaryMaster beneficiary) {
        return beneficiaryMasterService.saveBeneficiary(beneficiary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiaryMaster> updateBeneficiary(@PathVariable Long id, @RequestBody BeneficiaryMaster beneficiaryDetails) {
        return ResponseEntity.ok(beneficiaryMasterService.updateBeneficiary(id, beneficiaryDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Long id) {
        beneficiaryMasterService.deleteBeneficiary(id);
        return ResponseEntity.noContent().build();
    }
}
