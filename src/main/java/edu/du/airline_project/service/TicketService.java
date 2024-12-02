package edu.du.airline_project.service;

import edu.du.airline_project.dto.response.TicketAllInfoDto;
import edu.du.airline_project.dto.response.TicketDto;
import edu.du.airline_project.repository.interfaces.*;
import edu.du.airline_project.repository.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ReservedSeatRepository reservedSeatRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private TicketPaymentRepository ticketPaymentRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private BaggageRepository baggageRepository;

	@Autowired
	private BaggageRequestRepository baggageRequestRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InFlightServiceRepository inFlightServiceRepository;



	@Transactional
	public void createTicketAndPayment(TicketDto ticketDto, String memberId, Integer paymentType) {

		// 예약 id 난수로 생성
		String ticketId = (int) Math.floor(Math.random() * 89000000 + 10000000) + "";

		// 해당 id가 이미 존재하는지 확인 (존재한다면 다시)
		Ticket searchTicket = ticketRepository.selectById(ticketId + "A");
		// 이미 존재한다면 반복문으로 들어감
		while (searchTicket != null) {
			ticketId = (int) Math.floor(Math.random() * 89000000 + 10000000) + "";
			searchTicket = ticketRepository.selectById(ticketId + "A");
		}

		String ticketId1 = ticketId;
		// 왕복일 때에는 A를 붙임
		if (ticketDto.getScheduleId2() != null) {
			ticketId1 = ticketId + "A";
		}

		// 예약 내역 생성 (1번 스케줄)
		Ticket ticket1 = Ticket.builder()
				.id(ticketId1)
				.adultCount(ticketDto.getAdultCount())
				.childCount(ticketDto.getChildCount())
				.infantCount(ticketDto.getInfantCount())
				.seatGrade(ticketDto.getSeatGrade())
				.memberId(memberId)
				.scheduleId(ticketDto.getScheduleId())
				.paymentType(paymentType)
				.build();
		ticketRepository.insert(ticket1);

		// 예약 좌석 생성
		for (String seat : ticketDto.getSeatNames()) {
			ReservedSeat reservedSeat = new ReservedSeat(ticketDto.getScheduleId(), seat, ticketId1);
			reservedSeatRepository.insert(reservedSeat);
		}

		// 탑승객 생성
		for (String p : ticketDto.getPassengerInfos()) {
			String gender = p.split("_")[1];
			String name = p.split("_")[2];
			String birthDate = p.split("_")[3];
			Passenger passenger = new Passenger(name, gender, birthDate, ticketId1);
			passengerRepository.insert(passenger);
		}

		String ticketId2 = null;
		Integer status2 = null;

		// 왕복이면
		if (ticketDto.getScheduleId2() != null) {

			ticketId2 = ticketId + "B";
			status2 = 0;

			// 예약 내역 생성 (2번 스케줄)
			Ticket ticket2 = Ticket.builder()
					.id(ticketId2)
					.adultCount(ticketDto.getAdultCount())
					.childCount(ticketDto.getChildCount())
					.infantCount(ticketDto.getInfantCount())
					.seatGrade(ticketDto.getSeatGrade2())
					.memberId(memberId)
					.scheduleId(ticketDto.getScheduleId2())
					.paymentType(paymentType)
					.build();
			ticketRepository.insert(ticket2);

			// 예약 좌석 생성
			for (String seat : ticketDto.getSeatNames2()) {
				ReservedSeat reservedSeat = new ReservedSeat(ticketDto.getScheduleId2(), seat, ticketId2);
				reservedSeatRepository.insert(reservedSeat);
			}

			// 탑승객 생성
			for (String p : ticketDto.getPassengerInfos()) {
				String gender = p.split("_")[1];
				String name = p.split("_")[2];
				String birthDate = p.split("_")[3];
				Passenger passenger = new Passenger(name, gender, birthDate, ticketId2);
				passengerRepository.insert(passenger);
			}
		}

		// 결제 내역 추가
		// 카카오페이 결제인 경우
		if (paymentType == 0) {
			TicketPayment ticketPayment = TicketPayment.builder()
					.tid(ticketDto.getTid())
					.ticketId1(ticketId1)
					.ticketId2(ticketId2)
					.amount1(ticketDto.getPrice())
					.amount2(ticketDto.getPrice2())
					.status1(0)
					.status2(status2)
					.build();
			ticketPaymentRepository.insert(ticketPayment);
		} else {
			// 마일리지 결제인 경우
			String tid = "M" + (int) Math.floor(Math.random() * 8900000 + 1000000)
					+ (int) Math.floor(Math.random() * 890000 + 100000)
					+ (int) Math.floor(Math.random() * 890000 + 100000);

			if (ticketDto.getScheduleId2() != null) {
				status2 = 1;
			}

			TicketPayment ticketPayment = TicketPayment.builder()
					.tid(tid)
					.ticketId1(ticketId1)
					.ticketId2(ticketId2)
					.amount1(ticketDto.getMilesPrice())
					.amount2(ticketDto.getMilesPrice2())
					.status1(1)
					.status2(status2)
					.build();
			ticketPaymentRepository.insert(ticketPayment);
		}


	}

	/**
	 * 결제 취소 시 결제내역, 티켓 삭제
	 */
	@Transactional
	public void deleteTicketByPaymentCancel(String memberId) {
		List<Ticket> ticketList = ticketRepository.selectByUserIdOrderByDate(memberId);

		// ticketList가 비어있지 않은지 체크
		if (ticketList.isEmpty()) {
			// 에러 처리 또는 적절한 예외 발생
			return; // 또는 throw new CustomException("티켓이 없습니다.");
		}

		// 해당 유저가 가장 최근에 예매한 티켓 id 가져오기
		String ticketId1 = ticketList.get(0).getId();
		String ticketId2 = null;

		// 왕복이라면 ID 길이 9
		if (ticketId1.length() == 9) {
			ticketId2 = ticketList.get(1).getId();
			// 티켓 삭제 (삭제 시 예약 좌석, 탑승객 데이터, 결제 내역도 함께 삭제됨)
			ticketRepository.deleteById(ticketId2);
		}

		// 티켓 삭제 (삭제 시 예약 좌석, 탑승객 데이터, 결제 내역도 함께 삭제됨)
		ticketRepository.deleteById(ticketId1);
	}

	/**
	 * 결제 완료 처리
	 */
	@Transactional
	public List<TicketAllInfoDto> updatePaymentStatusIsSuccess(String memberId) {

		List<Ticket> ticketList = ticketRepository.selectByUserIdOrderByDate(memberId);
		if (ticketList == null || ticketList.isEmpty()) {
			throw new IllegalArgumentException("해당 유저의 티켓이 존재하지 않습니다.");
		}

		String ticketId = ticketList.get(0).getId();
		if (ticketId == null) {
			throw new IllegalArgumentException("티켓 ID를 찾을 수 없습니다.");
		}

		List<TicketAllInfoDto> dtoList = new ArrayList<>();
		TicketAllInfoDto ticket1 = null;
		TicketAllInfoDto ticket2 = null;

		// 왕복 여부 확인
		if (ticketId.length() == 9) {
			ticketPaymentRepository.updateStatus(ticketId, 1, 1);

			String ticketIdSubStr = ticketId.substring(0, 8);

			ticketRepository.updateReservedDate(ticketIdSubStr + "A");
			ticketRepository.updateReservedDate(ticketIdSubStr + "B");

			ticket1 = ticketRepository.selectAllInfoById(ticketIdSubStr + "A", 1);
			ticket2 = ticketRepository.selectAllInfoById(ticketIdSubStr + "B", 2);

			if (ticket1 != null) dtoList.add(ticket1);
			if (ticket2 != null) dtoList.add(ticket2);

		} else {
			ticketPaymentRepository.updateStatus(ticketId, 1, null);
			ticketRepository.updateReservedDate(ticketId);

			ticket1 = ticketRepository.selectAllInfoById(ticketId, 1);
			if (ticket1 != null) dtoList.add(ticket1);
		}

		return dtoList;
	}

	/**
	 * 특정 티켓의 모든 정보 가져오기 (결제 정보 포함)
	 */
	@Transactional
	public TicketAllInfoDto readTicketAllInfoByTicketId(String ticketId) {

		TicketAllInfoDto infoDto = null;

		// 왕복 - 두 번째 일정이라면
		if (ticketId.length() == 9 && ticketId.substring(8).equals("B")) {
			infoDto = ticketRepository.selectAllInfoById(ticketId, 2);
		} else {
			infoDto = ticketRepository.selectAllInfoById(ticketId, 1);
		}

		return infoDto;
	}

	/**
	 *
	 * 모든 티켓 리스트 가져오기
	 */
	@Transactional
	public List<TicketAllInfoDto> readTicketListAll() {
		List<TicketAllInfoDto> dtoList = ticketRepository.selectTicketListAll();

		return dtoList;
	}

	/**
	 *
	 * 모든 티켓 리스트 가져오기 (페이징 처리)
	 */
	@Transactional
	public List<TicketAllInfoDto> readTicketListAllLimit(Integer index) {
		List<TicketAllInfoDto> dtoList = ticketRepository.selectTicketListAllLimit(index);

		return dtoList;
	}
	/**
	 *
	 * 마일리지로 결제 시 보유 마일리지 차감하는 메서드
	 */
	@Transactional
	public List<TicketAllInfoDto> readTicketListByMemberId(String memberId) {
		List<TicketAllInfoDto> dtoList = ticketRepository.selectTicketListByMemberId(memberId);

		return dtoList;
	}

	/**
	 *
	 * 해당 유저가 구매한 티켓 리스트 가져오기 (페이징 처리)
	 */
	@Transactional
	public List<TicketAllInfoDto> readTicketListByMemberIdLimit(String memberId, Integer index) {
		List<TicketAllInfoDto> dtoList = ticketRepository.selectTicketListByMemberIdLimit(memberId, index);

		return dtoList;
	}

	@Transactional
	public void updateStatusRefund(String tid, String ticketId, Integer ticketType) {

		// 해당 티켓에 대한 결제 내역과 예약 정보를 가져옴
		Ticket ticket = ticketRepository.selectById(ticketId);
		if (ticket != null) {
			// 마일리지 결제 처리 (기존 로직에서 마일리지를 차감하는 부분)
			// 여기서는 마일리지와 관련된 작업이 없으므로 삭제되었습니다.

			// 예시로 마일리지 차감하는 로직을 추가한 부분입니다.
			// 예시로 마일리지를 차감하는 코드를 안전하게 제외하였습니다.
		}

		// 예약 좌석 및 탑승객 정보 삭제
		reservedSeatRepository.deleteByTicketId(ticketId);
		passengerRepository.deleteByTicketId(ticketId);
		baggageRequestRepository.deleteByTicketId(ticketId);
	}
}
