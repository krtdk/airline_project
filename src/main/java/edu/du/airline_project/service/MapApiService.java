package edu.du.airline_project.service;

import edu.du.airline_project.repository.interfaces.AirportRepository;
import edu.du.airline_project.repository.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapApiService {

	@Autowired
	private AirportRepository airportRepository;

	// 공항 좌표값 가져오기
	public List<Airport> airportSearch(String searchName) {
		List<Airport> list = airportRepository.selectByLikeName(searchName);
		return list;
	}
	
	// 지역, 이름 가져오기
	public List<Airport> selectAllName(String region) {
		List<Airport> list = airportRepository.selectByRegion(region);
		return list;
	}

}
