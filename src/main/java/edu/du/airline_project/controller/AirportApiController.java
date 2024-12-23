package edu.du.airline_project.controller;

import edu.du.airline_project.repository.model.Airport;
import edu.du.airline_project.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportApiController {

	@Autowired
	private AirportService airportService;
	
	// 모든 공항 리스트
	@GetMapping("/all")
	public List<Airport> allAirportData() {
		List<Airport> reqList = airportService.readAll();
		return reqList;
	}
	
	// 자동 완성에 사용할 검색된 공항 리스트
	@GetMapping("/search")
	public List<Airport> searchAirportData(@RequestParam String name) {
		List<Airport> reqList = airportService.readByLikeName(name);
		return reqList;
	}
	
	// 지역에 해당하는 공항 리스트
	@GetMapping("/list")
	public List<Airport> airportByRegionData(@RequestParam String region) {
		List<Airport> reqList = airportService.readByRegion(region);
		return reqList;
	}
	
}
