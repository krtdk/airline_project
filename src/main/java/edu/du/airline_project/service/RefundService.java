package edu.du.airline_project.service;

import edu.du.airline_project.repository.interfaces.RefundFeeRepository;
import edu.du.airline_project.repository.model.RefundFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RefundService {
	
	@Autowired
	private RefundFeeRepository refundFeeRepository;

	/**
	 */
	@Transactional
	public Long readRefundFee(Integer scheduleType, Integer dayCount) {
		
		Integer criterion = 0;
		
		// 국제선일 때
		if (scheduleType == 2) {
			
			if (dayCount >= 90) {
				criterion = 90;
			} else if (dayCount >= 60) {
				criterion = 60;
			} else if (dayCount >= 15) {
				criterion = 15;
			} else if (dayCount >= 4) {
				criterion = 4;
			} else if (dayCount >= 0) {
				criterion = 0;
			}
		}
		
		Long fee = refundFeeRepository.selectByCriterionAndType(criterion, scheduleType);
		
		return fee;
	}
	
	public List<RefundFee> readByType(Integer type) {
		List<RefundFee> entityList = refundFeeRepository.selectByType(type);
		return entityList;
	}
	
}
