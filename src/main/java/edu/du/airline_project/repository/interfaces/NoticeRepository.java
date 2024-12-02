package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.NoticeResponseDto;
import edu.du.airline_project.repository.model.Notice;
import edu.du.airline_project.repository.model.NoticeCategory;
import edu.du.airline_project.utils.PagingObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeRepository {

	// 관리자 측 공지사항 작성 기능
	int insertNotice(Notice notice);

	// 전체 카테고리 출력 기능
	List<NoticeCategory> selectNoticeCategory();
	
	// 전체 공지사항 글 개수 받아오기 기능
	int selectNoticeCount();
	
	// 전체 공지사항 글 개수 받아오기 기능(카테고리 기반)
	int selectNoticeByCategoryIdCount(Integer categoryId);

	// 전체 공지사항 출력 기능
	List<NoticeResponseDto> selectNotice(PagingObj obj);

	// 공지사항 검색 기능
	List<NoticeResponseDto> selectNoticeByTitle(@Param("obj") PagingObj obj, @Param("keyword") String keyword);
	
	// 공지사항 검색 글 개수 
	int selectNoticeByKeywordCount(String keyword);

	// 공지사항 상세 페이지 이동
	NoticeResponseDto selectNoticeById(int id);

	// 공지사항 카테고리별 출력 기능
	List<NoticeResponseDto> selectNoticeByCategoryId(@Param("obj") PagingObj obj, @Param("categoryId") int categoryId);

	// 관리자 측 게시글 삭제
	int deleteNoticeById(Integer id);

	// 관리자 측 게시글 수정
	int updateNoticeById(Notice notice);
	
	/**
	 *   
	 * 최근 순으로 정렬한 N개의 공지사항 (메인페이지용)
	 */
	List<NoticeResponseDto> selectOrderByIdDescLimitN(Integer limitCount);
}
