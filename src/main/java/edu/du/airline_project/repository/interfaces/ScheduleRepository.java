package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.ScheduleInfoResponseDto;
import edu.du.airline_project.repository.model.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * @author 서영
 */
@Mapper
public interface ScheduleRepository {

	// 항공권 옵션에 따른 운항 스케줄 조회
	public List<ScheduleInfoResponseDto> selectByAirportAndDepartureDate(@Param("departure") String departure, @Param("destination") String destination, @Param("flightDate") Date flightDate);
	
	// id로 운항 스케줄 + 노선 조회
	public ScheduleInfoResponseDto selectDtoByScheduleId(Integer scheduleId);
	
	// id로 운항 스케줄만 조회
	public Schedule selectByScheduleId(Integer scheduleId);
}
