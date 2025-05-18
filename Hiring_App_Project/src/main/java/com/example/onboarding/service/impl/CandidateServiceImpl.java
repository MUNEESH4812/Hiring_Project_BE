package com.example.onboarding.service.impl;

import com.example.onboarding.dto.CandidatePersonalInfoDTO;
import com.example.onboarding.dto.CandidateBankInfoDTO;
import com.example.onboarding.dto.CandidateEducationDTO;
import com.example.onboarding.entity.*;
import com.example.onboarding.exception.CandidateNotFoundException;
import com.example.onboarding.repository.*;
import com.example.onboarding.service.CandidateService;
import com.example.onboarding.service.EmailServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidatePersonalInfoRepository personalInfoRepository;
    private final CandidateBankInfoRepository bankInfoRepository;
    private final CandidateEducationInfoRepository educationInfoRepository;
    private final EmailServiceImpl emailService;

    public CandidateServiceImpl(
            CandidateRepository candidateRepository,
            CandidatePersonalInfoRepository personalInfoRepository,
            CandidateBankInfoRepository bankInfoRepository,
            CandidateEducationInfoRepository educationInfoRepository,
            EmailServiceImpl emailService
    ) {
        this.candidateRepository = candidateRepository;
        this.personalInfoRepository = personalInfoRepository;
        this.bankInfoRepository = bankInfoRepository;
        this.educationInfoRepository = educationInfoRepository;
        this.emailService = emailService;
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
    }


    @Override
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate existing = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        existing.setName(updatedCandidate.getName());
        existing.setEmail(updatedCandidate.getEmail());
        existing.setPhone(updatedCandidate.getPhone());
        existing.setPosition(updatedCandidate.getPosition());
        existing.setStatus(updatedCandidate.getStatus());
        existing.setHiredDate(updatedCandidate.getHiredDate());

        return candidateRepository.save(existing);
    }

    @Override
    public long getCandidateCount() {
        return candidateRepository.count();
    }

    @Override
    public Candidate updateCandidateStatus(Long id, String status) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.setStatus(status);
        Candidate updated = candidateRepository.save(candidate);

        emailService.sendStatusUpdateEmail(candidate);

        return updated;
    }

    @Override
    public List<Candidate> getCandidatesByStatus(String status) {
        return candidateRepository.findByStatusIgnoreCase(status);
    }

    @Override
    public void updateCandidatePersonalInfo(Long candidateId, CandidatePersonalInfoDTO dto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        CandidatePersonalInfo info = new CandidatePersonalInfo();
        info.setCandidate(candidate);
        info.setDob(dto.getDob());
        info.setGender(dto.getGender());
        info.setAddress(dto.getAddress());
        info.setNationality(dto.getNationality());

        personalInfoRepository.save(info);
    }

    @Override
    public void updateCandidateBankInfo(Long candidateId, CandidateBankInfoDTO dto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        CandidateBankInfo info = new CandidateBankInfo();
        info.setCandidate(candidate);
        info.setBankName(dto.getBankName());
        info.setAccountNumber(dto.getAccountNumber());
        info.setIfscCode(dto.getIfscCode());

        bankInfoRepository.save(info);
    }

    @Override
    public void updateCandidateEducationInfo(Long candidateId, CandidateEducationDTO dto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        CandidateEducationInfo info = new CandidateEducationInfo();
        info.setCandidate(candidate);
        info.setDegree(dto.getDegree());
        info.setInstitution(dto.getInstitution());
        info.setYearOfGraduation(dto.getYearOfGraduation());

        educationInfoRepository.save(info);
    }
}
