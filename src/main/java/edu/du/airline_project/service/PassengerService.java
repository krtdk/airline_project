package edu.du.airline_project.service;

import edu.du.airline_project.repository.interfaces.PassengerRepository;
import edu.du.airline_project.repository.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 서영
 *
 */
@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;

	/**
	 * @return 해당 티켓의 탑승객 정보
	 */
	public List<Passenger> readByTicketId(String ticketId) {
		List<Passenger> entityList = passengerRepository.selectByTicketId(ticketId);
		
		return entityList;
	}
	
}
