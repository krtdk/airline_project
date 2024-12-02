package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.request.InFlightReqRequestDto;
import edu.du.airline_project.dto.response.InFlightMealResponseDto;
import edu.du.airline_project.dto.response.InFlightServiceResponseDto;
import edu.du.airline_project.dto.response.SpecialMealResponseDto;
import edu.du.airline_project.repository.model.InFlightMeal;
import edu.du.airline_project.repository.model.InFlightMealDetail;
import edu.du.airline_project.repository.model.InFlightService;
import edu.du.airline_project.utils.PagingObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InFlightServiceRepository {

	// 기내 서비스 출력 기능
	List<InFlightService> selectInFlightService();

	// 기내 서비스 검색 기능
	List<InFlightService> selectInFlightServiceByName(String keyword);
	
	// 특별 기내식 상세 정보 name 기반 특별 기내식 조회 기능 
	List<InFlightMealResponseDto> selectInFlightMealByName(String name);
	
	// 특별 기내식 조회 기능
	List<InFlightMealResponseDto> selectInFlightDetailMeal();
	
	// 특별 기내식 조회 기능
	List<InFlightMeal> selectInFlightMealCategory();
	
	// 특별 기내식 종류 출력 기능
	List<InFlightMealDetail> selectInFlightMealByMealId(String mealDetail);
	
	// memberId 기반 특별 기내식 신청 조회 기능  
	InFlightMealResponseDto selectInFlightMealRequestByUserId(@Param("memberId") String memberId, @Param("departureDate") String departureDate);
	
	// name 기반 상세 특별 기내식 조회 기능
	InFlightMealDetail selectInFlightMealDetailByName(String name);
	
	// 특별 기내식 신청 기능 (데이터 삽입)
	int insertInFlightMealRequest(InFlightReqRequestDto inFlightReqRequestDto);
	
	// 특별 기내식을 신청한 사람의 스케줄 조회 기능
	List<InFlightMealResponseDto> selectInFlightScheduleByMemberId(String memberId);
	
	// 특별 기내식 신청할 때 좌석 수를 동적으로 가져가기 위해 사용
	InFlightMealResponseDto selectInFlightRequestForSeatCount(@Param("memberId") String memberId, @Param("ticketId") String ticketId);
	
	// 기내 서비스 조회시 운항 시간별 서비스 조회를 하기 위해 사용
	List<InFlightServiceResponseDto> selectAvailableServiceByFlightHours(String flightHours);
	
	// 마이페이지 특별 기내식 신청 내역 조회
	List<SpecialMealResponseDto> selectRequestMealByMemberId(String memberId);
	
	// 마이페이지 특별 기내식 신청 내역 조회 ) memberId, ticketId 기반 조회
//	InFlightServiceResponseDto selectTicketByIdAndTicketId(String memberId, String ticketId);
	
	// id 기반 기내식 신청 취소 
	int deleteRequestMealById(Integer id);
	
	// 매니저 기내 특별식 신청 내역 조회
	List<InFlightMealResponseDto> selectInFlightMealForManager(PagingObj obj);
	
	// 매니저 기내 특별식 신청 내역 총 개수
	Integer selectInFlightMealCount();
	
	
	/**
	 * @author 서영
	 * 티켓 취소/환불 시 신청 삭제
	 */
	Integer deleteByTicketId(String ticketId);
}
 

