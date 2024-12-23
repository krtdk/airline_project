package edu.du.airline_project.service;

import edu.du.airline_project.dto.response.FaqResponseDto;
import edu.du.airline_project.repository.interfaces.FaqRepository;
import edu.du.airline_project.repository.model.FaqCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

	@Autowired
	private FaqRepository faqRepository;

	// id 기반 자주묻는질문 조회
	public List<FaqResponseDto> readFaqByCategoryId(Integer categoryId) {
		List<FaqResponseDto> faqResponseDtos = faqRepository.selectFaqByCategoryId(categoryId);
		return faqResponseDtos;
	}

	// faq category
	public List<FaqCategory> readFaqCategory() {
		List<FaqCategory> categories = faqRepository.selectFaqCategory();
		return categories;
	}

	// faq 검색 기능
	public List<FaqResponseDto> readFaqByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		List<FaqResponseDto> faqList = faqRepository.selectFaqByKeyword(keyword);
		return faqList;
	}

	// 관리자 측 게시물 삭제 기능
	public void deleteFaqById(Integer id) {
		int result = faqRepository.deleteFaqById(id);
		if (result == 1) {
			System.out.println("삭제 성공");
		}
	}

	// 관리자 측 게시물 수정 기능
	public void updateFaqById(Integer id, FaqResponseDto faq) {
		int result = faqRepository.updateFaqById(id, faq);
		if (result == 1) {
			System.out.println("수정 성공");
		}
	}
	
	// 아이디 기반 faq 리스트 출력 기능
	public List<FaqResponseDto> readFaqByIdList(Integer id) {
		List<FaqResponseDto> faqResponseDto = faqRepository.selectFaqByIdList(id);
		return faqResponseDto;
	}
	
	// 아이디 기반 faq 출력 기능
	public FaqResponseDto readFaqById(Integer id) {
		FaqResponseDto faqResponseDto = faqRepository.selectFaqById(id);
		return faqResponseDto;
	}

	/**
	 *   
	 * @return 모든 faq
	 */
	public List<FaqResponseDto> readFaqAll() {
		return faqRepository.selectFaqAll();
	}
}
