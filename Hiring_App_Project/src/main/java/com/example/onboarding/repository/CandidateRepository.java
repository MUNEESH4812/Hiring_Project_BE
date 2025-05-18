package com.example.onboarding.repository;

import com.example.onboarding.entity.Candidate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	List<Candidate> findByStatusIgnoreCase(String status);
}
