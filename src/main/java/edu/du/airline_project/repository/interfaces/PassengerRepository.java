package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.Passenger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 서영
 *
 */
@Mapper
public interface PassengerRepository {

	public Integer insert(Passenger passenger);
	
	public List<Passenger> selectByTicketId(String ticketId);
	
	public Integer deleteByTicketId(String ticketId);
}
