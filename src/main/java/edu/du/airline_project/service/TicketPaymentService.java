package edu.du.airline_project.service;

import edu.du.airline_project.dto.response.MonthlySalesForChartDto;
import edu.du.airline_project.repository.interfaces.TicketPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TicketPaymentService {

	@Autowired
	private TicketPaymentRepository ticketPaymentRepository;
	
	/**
	 * @return 최근 11개월간 월간 매출액 (이번 달 제외)
	 */
	public List<MonthlySalesForChartDto> readMonthlySales(String date) {
		return ticketPaymentRepository.selectSalesGroupByDate(date);
	}
	
	/**
	 * @return 특정 월의 매출액
	 */
	public MonthlySalesForChartDto readSalesByThisMonth(Integer year, Integer month) {
		return ticketPaymentRepository.selectSalesByThisMonth(year, month);
	}
	
	
	
}
