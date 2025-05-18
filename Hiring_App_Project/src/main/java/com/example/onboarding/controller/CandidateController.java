package com.example.onboarding.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.onboarding.entity.Candidate;
import com.example.onboarding.service.CandidateService;
import com.example.onboarding.dto.UpdateStatusRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    
    @PostMapping("/createCandidate")
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.createCandidate(candidate);
        return ResponseEntity.ok(savedCandidate);
    }


    // GET /api/candidates/hired - Get all hired candidates
    @GetMapping("/applied")
    public List<Candidate> getAllAppliedCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    
    @PutMapping("/{id}/updateCandidate")
    public ResponseEntity<Candidate> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody Candidate updatedCandidate
    ) {
        Candidate candidate = candidateService.updateCandidate(id, updatedCandidate);
        return ResponseEntity.ok(candidate);
    }

    
    @GetMapping("/count")
    public ResponseEntity<Long> getCandidateCount() {
        long count = candidateService.getCandidateCount();
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Candidate> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        Candidate updated = candidateService.updateCandidateStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Candidate>> getCandidatesByStatus(@PathVariable String status) {
        List<Candidate> candidates = candidateService.getCandidatesByStatus(status);
        return ResponseEntity.ok(candidates);
    }
    
    


}
