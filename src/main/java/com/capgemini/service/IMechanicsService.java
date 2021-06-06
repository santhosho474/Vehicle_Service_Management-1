package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Mechanics;
import com.capgemini.exception.MechanicsException;

public interface IMechanicsService {
String createMechanics(Mechanics mechanics) throws MechanicsException;
String updateMechanics(int mechanicsId,Mechanics mechanics) throws MechanicsException;
Mechanics findMechanicsbyId(int mechanicsId) throws MechanicsException;
List<Mechanics> getRequest() throws MechanicsException;
String deleteMechanic(int mechanicsId) throws MechanicsException;
}
