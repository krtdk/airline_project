package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.FaqResponseDto;
import edu.du.airline_project.repository.model.FaqCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FaqRepository {

	// faq 카테고리 조회
	List<FaqCategory> selectFaqCategory();

	// id기반 faq 조회
	List<FaqResponseDto> selectFaqByCategoryId(Integer categoryId);
	
	// 검색 기능
	List<FaqResponseDto> selectFaqByKeyword(String keyword);
	
	// 관리자 측 faq 삭제 기능
	int deleteFaqById(Integer id);
	
	// 관리자 측 faq 수정 기능
	int updateFaqById(@Param("id") Integer id, @Param("faq") FaqResponseDto faq);
	
	// id기반 List faq 출력 기능 
	List<FaqResponseDto> selectFaqByIdList(Integer id);
	
	// id기반 faq 출력 기능
	FaqResponseDto selectFaqById(Integer id);
	
	/**
	 * @author 서영
	 * 모든 faq 조회
	 */
	List<FaqResponseDto> selectFaqAll();
	
}
