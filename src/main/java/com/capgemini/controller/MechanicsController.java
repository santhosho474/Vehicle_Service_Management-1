package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Mechanics;
import com.capgemini.service.MechanicsServiceImpl;

@RestController
@RequestMapping("/api/mechanics/")
@CrossOrigin
public class MechanicsController {
	
	@Autowired
	private MechanicsServiceImpl mechanicsServiceimpl;
	
	@PostMapping("/")
	public ResponseEntity<String> create(@Valid @RequestBody Mechanics mechanics) throws Exception {
		
		String message=mechanicsServiceimpl.createMechanics(mechanics);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PutMapping("/{mechanicsId}")
	public ResponseEntity<String> update(@PathVariable int mechanicsId,@RequestBody Mechanics mechanics ) throws Exception {
		String message=mechanicsServiceimpl.updateMechanics(mechanicsId, mechanics);
		return new ResponseEntity<>(message, HttpStatus.OK);
}
	@GetMapping("/{mechanicsId}")
	public ResponseEntity<Mechanics> findById(@PathVariable int mechanicsId) throws Exception {
		Mechanics mechanic=mechanicsServiceimpl.findMechanicsbyId(mechanicsId);
		return new ResponseEntity<>(mechanic, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Mechanics>> getRequest() throws Exception{
		List<Mechanics> mechanics=mechanicsServiceimpl.getRequest();
		return new ResponseEntity<>(mechanics, HttpStatus.OK);
	}
	
	@DeleteMapping("/{mechanicsId}")
	public ResponseEntity<String> deleteUser(@PathVariable int mechanicsId) throws Exception {
		String message=mechanicsServiceimpl.deleteMechanic(mechanicsId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
