package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.Airport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 서영
 *
 */
@Mapper
public interface AirportRepository {

	public List<Airport> selectAll();
	
	// 자동 완성에 사용할 공항 리스트
	public List<Airport> selectByLikeName(String searchName);
	
	// 공항 위치 정보 - 지역만
	public List<Airport> selectRegion();
	
	// 공항 위치 정보 - 지역에 해당하는 공항만
	public List<Airport> selectByRegion(String region);
	
}