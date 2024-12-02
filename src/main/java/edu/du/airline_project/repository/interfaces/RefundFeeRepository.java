package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.RefundFee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RefundFeeRepository {

	// 출발일 며칠 이전인지 + 국내선/국제선에 따라 환불 수수료 결정
	public Long selectByCriterionAndType(@Param("criterion") Integer criterion, @Param("type") Integer type);
	
	public List<RefundFee> selectByType(Integer type); 
	
}
