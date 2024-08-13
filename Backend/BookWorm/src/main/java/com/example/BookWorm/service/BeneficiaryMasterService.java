package com.example.BookWorm.service;


import com.example.BookWorm.models.BeneficiaryMaster;
import com.example.BookWorm.repository.BeneficiaryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryMasterService {

    @Autowired
    private BeneficiaryMasterRepository beneficiaryMasterRepository;

    public List<BeneficiaryMaster> getAllBeneficiaries() {
        return beneficiaryMasterRepository.findAll();
    }

    public Optional<BeneficiaryMaster> getBeneficiaryById(Long id) {
        return beneficiaryMasterRepository.findById(id);
    }

    public BeneficiaryMaster saveBeneficiary(BeneficiaryMaster beneficiary) {
        return beneficiaryMasterRepository.save(beneficiary);
    }

    public void deleteBeneficiary(Long id) {
        beneficiaryMasterRepository.deleteById(id);
    }

    public BeneficiaryMaster updateBeneficiary(Long id, BeneficiaryMaster beneficiaryDetails) {
        return beneficiaryMasterRepository.findById(id).map(beneficiary -> {
            beneficiary.setBenName(beneficiaryDetails.getBenName());
            beneficiary.setBenEmailId(beneficiaryDetails.getBenEmailId());
            beneficiary.setBenContactNo(beneficiaryDetails.getBenContactNo());
            beneficiary.setBenBankName(beneficiaryDetails.getBenBankName());
            beneficiary.setBenBankBranch(beneficiaryDetails.getBenBankBranch());
            beneficiary.setBenIFSC(beneficiaryDetails.getBenIFSC());
            beneficiary.setBenAccNo(beneficiaryDetails.getBenAccNo());
            beneficiary.setBenAccType(beneficiaryDetails.getBenAccType());
            beneficiary.setBenPAN(beneficiaryDetails.getBenPAN());
            return beneficiaryMasterRepository.save(beneficiary);
        }).orElseGet(() -> {
            beneficiaryDetails.setBenId(id);
            return beneficiaryMasterRepository.save(beneficiaryDetails);
        });
    }
}
