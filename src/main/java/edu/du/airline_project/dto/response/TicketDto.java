package edu.du.airline_project.dto.response;

import edu.du.airline_project.utils.Define;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * request용으로도 사용됨
 * 결제 정보를 포함함
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {

	private String id;
	private Integer airplaneId;
	private Integer airplaneId2;

	private Integer adultCount;
	private Integer childCount;
	private Integer infantCount;

	private String seatGrade;
	private Integer scheduleId;
	private List<String> seatNames;
	private Long price;
	private Long milesPrice;

	private String seatGrade2;
	private Integer scheduleId2;
	private List<String> seatNames2;
	private Long price2;
	private Long milesPrice2;

	// 결제 시
	private Integer totalAmount;
	private Integer quantity;
	private String tid; // 결제고유번호

	// 탑승객 정보 (연령타입_성별_이름_생년월일)
	private List<String> passengerInfos;

	public TicketDto(Integer adultCount, Integer childCount, Integer infantCount, String seatGrade,
					 Integer scheduleId) {
		this.adultCount = adultCount;
		this.childCount = childCount;
		this.infantCount = infantCount;
		this.seatGrade = seatGrade;
		this.scheduleId = scheduleId;
	}

	// 마일리지 가격으로 환산
	public Long milesPrice(Long price) {
		return (long) Math.floor(price * Define.MILES_TICKET_RATE);
	}



}
