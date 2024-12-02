package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.AirplaneInfoDto;
import edu.du.airline_project.dto.response.SeatInfoResponseDto;
import edu.du.airline_project.dto.response.SeatStatusResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   
 *
 */
@Mapper
public interface SeatRepository {

	// 특정 좌석 정보 확인
	public SeatInfoResponseDto selectSeatInfoByNameAndScheduleId(@Param("seatName") String seatName, @Param("scheduleId") Integer scheduleId);
	
	// 해당 스케줄에 운항하는 비행기의 좌석 리스트 (등급에 따라)
	public List<SeatStatusResponseDto> selectSeatListByScheduleIdAndGrade(@Param("scheduleId") Integer scheduleId, @Param("grade") String grade);
	
	// 해당 비행기의 좌석 등급별 좌석 개수
	public List<AirplaneInfoDto> selectByAirplaneId(Integer airplaneId);
	
}
