package com.ebam.clinicaREST.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ebam.clinicaREST.model.domain.Doctor;
import com.ebam.clinicaREST.model.repository.DoctorRepository;
import com.ebam.clinicaREST.service.IDoctorService;

@Service
public class DoctorService implements IDoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> getAll() throws Exception {
		return doctorRepository.findAll();
	}

	@Override
	public Page<Doctor> getAll(Pageable pageable) throws Exception {
		return doctorRepository.findAll(pageable);
	}

	@Override
	public Doctor saveOrUpdate(Doctor entity) throws Exception {
		return doctorRepository.save(entity);
	}

	@Override
	public Optional<Doctor> getOne(Long id) throws Exception {
		return doctorRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		doctorRepository.deleteById(id);
	}

	
}
