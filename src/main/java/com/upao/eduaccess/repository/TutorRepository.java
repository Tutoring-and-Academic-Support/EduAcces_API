package com.upao.eduaccess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upao.eduaccess.domain.Tutor;

public interface TutorRepository extends JpaRepository<Tutor,Long>
{
	 Optional<Tutor> findById(Long id_tutor);
}