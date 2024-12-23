package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.SaveMileageDto;
import edu.du.airline_project.dto.UseMileageDto;
import edu.du.airline_project.repository.model.MemberGrade;
import edu.du.airline_project.repository.model.Mileage;
import edu.du.airline_project.repository.model.UseMileage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MileageRepository {

	
    public SaveMileageDto selectSaveMileage(String memberId);
	
    public SaveMileageDto selectExtinctionMileage(String memberId);
	public UseMileageDto selectUseMileage(String memberId);
	public List<Mileage> selectMileageList(@Param("memberId")String memberId,@Param("startTime")String startTime,
			@Param("endTime")String endTime,@Param("isAllSearch") String isAllSearch,@Param("isUpSearch")String isUpSearch,
			@Param("isUseSearch")String isUseSearch,@Param("isExpireSearch")String isExpireSearch);
	public List<UseMileage> selectUseMileageList(String memberId);
	public int insertMileage(@Param("saveMileageDto")SaveMileageDto saveMileageDto,@Param("memberGrade") MemberGrade memberGrade);
	public MemberGrade selectUserGradeByMemberId(String memberId);
	public List<Mileage> selectNowMileage(String memberId);
	public int insertUseDataList(Mileage mileage);
	public int updateBalance(Mileage mileage);
	public Long selectSumSaveMileageByMemberId(String memberId);
	public Mileage selectMileageByMemberId(String memberId);
	public List<Mileage> selectUseDataListTb(@Param("memberId") String memberId,@Param("gifticonId")String gifticonId);
	public int updateBalanceById(@Param("mileage")Mileage mileage,@Param("id") int id);
	public Mileage selectExprirationBalanceByMemberId(@Param("memberId") String memberId,@Param("ts")Timestamp ts);
	public Mileage selectSaveBalanceByMemberId(@Param("memberId") String memberId,@Param("ts")Timestamp ts);
	public List<Mileage> selectMileageTbOrderByMileageDateByMemberId(String memberId);
	
	/**
	 *   
	 * mileage_tb에 티켓 관련 사용 내역 추가
	 */
	public Integer insertUseMileageByTicket(Mileage mileage);
	
	/**
	 *   
	 * use_mileage_list_tb에 티켓 관련 사용 내역 상세 추가
	 */
	public Integer insertUseMileageDetailByTicket(UseMileage useMileage);
	
	/**
	 *   
	 * 해당 티켓 아이디에 의해 사용된 마일리지 상세 내역 가져오기
	 */
	public List<UseMileage> selectUseMileageDataDetailByTicketId(String ticketId);
	
	/**
	 *   
	 * 환불 시 사용하는 balance 갱신
	 */
	public Integer updateBalanceByRefund(@Param("id") Integer id, @Param("balance") Long balance);
	
	/**
	 *   
	 * 환불 후 사용 상세 내역은 삭제
	 */
	public Integer deleteUseMileageDetailByTicketId(Integer id);
	
	/**
	 *   
	 * 환불된 마일리지를 적립 내역으로 추가
	 */
	public Integer insertRefundMiles(Mileage mileage);
	
	public Mileage selectById(Integer id);
	
	public Integer deleteByTicketId(String ticketId);
}
