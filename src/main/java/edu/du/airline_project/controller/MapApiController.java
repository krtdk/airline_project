package edu.du.airline_project.controller;

import edu.du.airline_project.repository.model.Airport;
import edu.du.airline_project.service.MapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *     구글 맵 API
 */

@Controller
public class MapApiController {

	@Autowired
	private MapApiService mapApiService;

	// 공항 지역, 이름 찾기
	@GetMapping("/continent")
	@ResponseBody
	public List<Airport> selectByContinentProc(String region) {

		List<Airport> list = mapApiService.selectAllName(region);

		return list;
	}

	// 공항 좌표값 가져오기
	@GetMapping("/airportPosition")
	@ResponseBody
	public Airport selectAirportPositionProc(String searchName) {

		List<Airport> list = mapApiService.airportSearch(searchName);

		return list.get(0);
	}

	@GetMapping("/map")
	public String mapPage() {
		return "/board/mapApi";
	}

}