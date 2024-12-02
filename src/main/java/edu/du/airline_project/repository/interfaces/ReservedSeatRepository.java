package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.ReservedSeat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservedSeatRepository {

	/**
	 *   
	 * 해당 스케줄에 예약되어 있는 좌석들 가져오기
	 */
	public List<ReservedSeat> selectByScheduleId(Integer scheduleId);
	
	/**
	 *   
	 * 예약 좌석 등록
	 */
	public Integer insert(ReservedSeat reservedSeat);
	
	public List<ReservedSeat> selectByTicketId(String ticketId);
	
	/**
	 *   
	 * 티켓 환불 시 예약 좌석 삭제
	 */
	public Integer deleteByTicketId(String ticketId);
}

