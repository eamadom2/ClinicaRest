package com.ebam.clinicaREST.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebam.clinicaREST.exception.ModeloNotFoundException;
import com.ebam.clinicaREST.model.domain.Doctor;
import com.ebam.clinicaREST.service.IDoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<>();

		try {
			doctors = doctorService.getAll();
		} catch (Exception e) {

		}
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}

	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
		Optional<Doctor> doctor;
		try {
			doctor = doctorService.getOne(id);
			if (!doctor.isPresent()) {
				throw new ModeloNotFoundException("ID: " + id);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor doctorNew = new Doctor();

		try {
			doctorNew = doctorService.saveOrUpdate(doctor);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return new ResponseEntity<Doctor>(doctorNew, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor) {
		try {
			doctorService.saveOrUpdate(doctor);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}
		return new ResponseEntity<Doctor>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long id) {
		Optional<Doctor> doctor;

		try {
			doctor = doctorService.getOne(id);
			if (!doctor.isPresent()) {
				throw new ModeloNotFoundException("ID: " + id);
			} else {
				doctorService.deleteById(id);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Doctor>(HttpStatus.OK);

	}
}
