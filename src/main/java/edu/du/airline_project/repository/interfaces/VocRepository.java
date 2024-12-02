package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.CountByYearAndMonthDto;
import edu.du.airline_project.dto.response.VocCountByTypeDto;
import edu.du.airline_project.dto.response.VocInfoDto;
import edu.du.airline_project.repository.model.Voc;
import edu.du.airline_project.repository.model.VocAnswer;
import edu.du.airline_project.repository.model.VocAnswerForm;
import edu.du.airline_project.repository.model.VocCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   
 * 고객의 소리 관련
 */
@Mapper
public interface VocRepository {

	public List<VocCategory> selectCategoryList();
	
	public Integer insert(Voc voc);
	
	// 회원 : voc 게시글 정보 + 그에 해당하는 답변 정보 전체
	public List<VocInfoDto> selectByMemberId(String memberId);
	
	// 회원 : voc 게시글 정보 + 그에 해당하는 답변 정보 페이징 처리용
	public List<VocInfoDto> selectByMemberIdLimit(@Param("memberId") String memberId, @Param("index") Integer index);
	
	// 관리자 : voc 게시글 정보 + 그에 해당하는 답변 정보 전체
	public List<VocInfoDto> selectAll(Integer type);
	
	// 관리자 : voc 게시글 정보 + 그에 해당하는 답변 정보 페이징 처리용
	public List<VocInfoDto> selectAllLimit(@Param("index") Integer index, @Param("type") Integer type, @Param("limitCount") Integer limitCount);
	
	public VocInfoDto selectById(Integer id);
	
	public Integer deleteById(Integer id);
	
	public Integer updateById(Voc voc);
	
	// 답변 양식
	public List<VocAnswerForm> selectFormList();
	
	// 답변 처리
	public Integer insertAnswer(VocAnswer vocAnswer);
	
	// 해당 월에 작성된 고객의 말씀 수
	public CountByYearAndMonthDto selectCountByMonth(@Param("year") Integer year, @Param("month") Integer month);
	
	// 해당 월에 작성된 고객의 말씀 유형별 개수
	public List<VocCountByTypeDto> selectCountByMonthGroupByType(@Param("year") Integer year, @Param("month") Integer month);
	
	// 질문/답변 카운트 조회
	public VocInfoDto selectVocCountAndAnserCountByMemberId(String memberId);
	
}
