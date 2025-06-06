package com.example.onboarding.controller;

import com.example.onboarding.entity.CandidateBankInfo;
import com.example.onboarding.service.CandidateBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}")
public class CandidateBankInfoController {

    private final CandidateBankInfoService bankInfoService;

    @Autowired
    public CandidateBankInfoController(CandidateBankInfoService bankInfoService) {
        this.bankInfoService = bankInfoService;
    }

    @PutMapping("/bank-info")
    public CandidateBankInfo updateBankInfo(@PathVariable Long id, @RequestBody CandidateBankInfo bankInfo) {
        return bankInfoService.updateBankInfo(id, bankInfo);
    }
}
