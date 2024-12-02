package edu.du.airline_project.service;

import edu.du.airline_project.dto.response.ScheduleInfoResponseDto;
import edu.du.airline_project.repository.interfaces.ScheduleRepository;
import edu.du.airline_project.repository.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 *
 */

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	/**
	 * 항공권 옵션에 따른 스케줄 조회
	 */
	@Transactional
	public List<ScheduleInfoResponseDto> readByAirportAndDepartureDate(String departure, String destination, Date flightDate) {
		List<ScheduleInfoResponseDto> dtoList = scheduleRepository.selectByAirportAndDepartureDate(departure, destination, flightDate);
		
		return dtoList;
	}
	
	/**
	 * @return id에 따른 스케줄 조회
	 */
	@Transactional
	public Schedule readByScheduleId(Integer scheduleId) {
		Schedule schEntity = scheduleRepository.selectByScheduleId(scheduleId);
		return schEntity;
	}
	
	/**
	 * @return id에 따른 스케줄+노선+비행기 정보 조회
	 */
	@Transactional
	public ScheduleInfoResponseDto readInfoDtoByScheduleId(Integer scheduleId) {
		ScheduleInfoResponseDto dto = scheduleRepository.selectDtoByScheduleId(scheduleId);
		return dto;
	}
	
}
