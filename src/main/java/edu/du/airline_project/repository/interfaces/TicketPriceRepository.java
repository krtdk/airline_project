package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.TicketPrice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketPriceRepository {

	public TicketPrice selectByHours(Integer flightHours);
	
}
