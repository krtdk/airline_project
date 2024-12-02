package edu.du.airline_project.service;

import edu.du.airline_project.repository.interfaces.ReservedSeatRepository;
import edu.du.airline_project.repository.model.ReservedSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class ReservedSeatService {
	
	@Autowired
	private ReservedSeatRepository reservedSeatRepository;

	@Transactional
	public List<ReservedSeat> readByTicketId(String ticketId) {
		List<ReservedSeat> entityList = reservedSeatRepository.selectByTicketId(ticketId);
		
		return entityList;
	}
	
}
