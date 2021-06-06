package com.capgemini.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Mechanics;
import com.capgemini.exception.MechanicsException;
import com.capgemini.repository.MechanicsRepository;
@Service
public class MechanicsServiceImpl implements IMechanicsService {
	@Autowired
	private MechanicsRepository mechanicsrepository;
	@Override
	public String createMechanics(Mechanics mechanics) throws MechanicsException {
		if(mechanics.getMechanicsAge()<20 || mechanics.getMechanicsAge()>45) {
			throw new MechanicsException("Age is invalid");
		}
		if(!mechanics.getMechanicsMobile().matches("[6-9][0-9]{9}")) {
			throw new MechanicsException("mobile number is invalid");
		}
		if(!mechanics.getMechanicsName().matches("[A-Za-z. ]+")) {
			throw new MechanicsException("name is invalid");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		mechanics.create_Date_Time = java.time.LocalDateTime.now().format(formatter);
		mechanics.update_Date_Time = java.time.LocalDateTime.now().format(formatter);
		//mechanics.date = java.time.LocalDateTime.now().format(formatter);
		mechanicsrepository.save(mechanics);
		
		return "Mechanic Details Added!!!";
	}


	@Override
	public String updateMechanics(int mechanicsId,Mechanics mechanics) throws MechanicsException {
		Mechanics dbMechanic=mechanicsrepository.findById(mechanicsId).get();
		if(dbMechanic!=null) {
			if(mechanics.getMechanicsAge()<20 || mechanics.getMechanicsAge()>45) {
				throw new MechanicsException("Age is invalid");
			}
			if(!mechanics.getMechanicsMobile().matches("[6-9][0-9]{9}")) {
				throw new MechanicsException("mobile number is invalid");
			}
			if(!mechanics.getMechanicsName().matches("[A-Za-z. ]+")) {
				throw new MechanicsException("name is invalid");
			}
			dbMechanic.setMechanicsName(mechanics.getMechanicsName());
			dbMechanic.setMechanicsAge(mechanics.getMechanicsAge());
			dbMechanic.setMechanicsMobile(mechanics.getMechanicsMobile());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			mechanics.update_Date_Time = java.time.LocalDateTime.now().format(formatter);
			
		}
		mechanicsrepository.save(dbMechanic);
		return "Mechanic Updated!!!";
	}

	@Override
	public Mechanics findMechanicsbyId(int mechanicsId) throws MechanicsException {
		if(!mechanicsrepository.existsById(mechanicsId)) {
			throw new MechanicsException("Mechanic Not Found");
		}
		Mechanics dbMechanic=mechanicsrepository.findById(mechanicsId).get();
		if(dbMechanic.isDeleted==true) {
			throw new MechanicsException("Mechanic deleted from the data");
		}
		return(dbMechanic);
	}

	@Override
	public List<Mechanics> getRequest() throws MechanicsException {
		List<Mechanics> mechanics=mechanicsrepository.findAll().stream()
				.filter((p1)->p1.isDeleted==false)
				.collect(Collectors.toList());
		if(mechanics.isEmpty()) {
			throw new MechanicsException("Mechanics data is empty");
		}
		return mechanics;
	}

	@Override
	public String deleteMechanic(int mechanicsId) throws MechanicsException {
		if(!mechanicsrepository.existsById(mechanicsId)) {
			throw new MechanicsException("Mechanic Not Found");
		}
		Mechanics dbMechanic=mechanicsrepository.findById(mechanicsId).get();
		if(dbMechanic.isDeleted == false) {
			dbMechanic.isDeleted = true;
			mechanicsrepository.save(dbMechanic);
		}
		return "Mechanic Deleted";
	}

}
