package edu.du.airline_project.service;

import edu.du.airline_project.repository.interfaces.AirplaneRepository;
import edu.du.airline_project.repository.model.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 */
@Service
public class AirplaneService {
	
	@Autowired
	private AirplaneRepository airplaneRepository;

	public List<Airplane> readAll() {
		List<Airplane> entityList = airplaneRepository.selectAll();
		return entityList;
	}
	
}
