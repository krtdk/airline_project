package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.TicketAllInfoDto;
import edu.du.airline_project.repository.model.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TicketRepository {

	/**
	 *    id로 티켓 조회
	 */
	public Ticket selectById(String id);

	/**
	 *    티켓 생성
	 */
	public Integer insert(Ticket ticket);

	/**
	 *    티켓 삭제
	 */
	public Integer deleteById(String id);

	/**
	 *    해당 유저가 예매한 티켓들을 최근순으로 조회 (결제 내역 추가용 간단한 정보들만)
	 */
	public List<Ticket> selectByUserIdOrderByDate(String memberId);

	/**
	 *    해당 티켓에 대한 결제 정보를 포함한 모든 정보 반환 type : 1 -> 첫 번째 일정 type : 2 -> 두 번째
	 *         일정
	 */
	public TicketAllInfoDto selectAllInfoById(@Param("id") String id, @Param("type") Integer type);

	/**

	 *   
	 * 해당 유저의 티켓 구매 내역들을 최근순으로 조회
	 */
	public List<TicketAllInfoDto> selectTicketListByMemberId(String memberId);
	
	/**
	 *   
	 * 해당 유저의 티켓 구매 내역들을 최근순으로 조회
	 */
	public List<TicketAllInfoDto> selectTicketListByMemberIdLimit(@Param("memberId") String memberId, @Param("index") Integer index);
	
	/**
	 *   
	 * 예약날짜를 결제 성공 시점으로 갱신

	 */
	public Integer updateReservedDate(String id);

	/**
	 *       마일리지 신청 리스트
	 * 
	 */
	public List<TicketAllInfoDto> selectTicketList(@Param("memberId") String memberId, @Param("type") String type);
	/**
	 *      
	 * 마일리지 신청 페이지에 티켓정보 출력
	 * @param id
	 * @return
	 */
	public TicketAllInfoDto selectByTicketId(String id);
	
	public int inserticketIdById(TicketAllInfoDto ticketAllInfoDto);
	
	/**
	 *   
	 * 모든 티켓 구매 내역들을 최근순으로 조회
	 */
	public List<TicketAllInfoDto> selectTicketListAll();
	
	/**
	 *   
	 * 모든 티켓 구매 내역들을 최근순으로 조회
	 */
	public List<TicketAllInfoDto> selectTicketListAllLimit(Integer index);
	
}
