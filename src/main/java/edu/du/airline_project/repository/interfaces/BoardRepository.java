package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.BoardDto;
import edu.du.airline_project.dto.BoardUpdateDto;
import edu.du.airline_project.repository.model.Board;
import edu.du.airline_project.repository.model.LoveHeart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardRepository {
	
	// 게시물 목록 전체 조회, 좋아요 수 조회
	public List<Board> selectByBoardList();
	// 게시물 쓰기
	public int insertByBoard(BoardDto boardDto);
	// 게시물 수정
	public int updateByBoard(@Param("id") Integer id, @Param("boardUpdateDto") BoardUpdateDto boardUpdateDto);
	// 게시물 썸네일 안올렸을 때 수정
	public int updateByBoardJustThumbnail(@Param("id") Integer id, @Param("boardUpdateDto") BoardUpdateDto boardUpdateDto);
	// 게시물 삭제
	public int deleteByBoard(Integer id);
	// 게시물 상세 보기
	public BoardDto selectByBoardDetail(Integer id);
	// 게시물 조회 수 증가
	public void updateByViewCount(Integer id);
	// 좋아요 
	public int insertByHeart(@Param("id") Integer id, @Param("userId") String userId);
	// 좋아요 취소
	public int deleteByHeart(@Param("id") Integer id, @Param("userId") String userId);
	// 게시물 삭제시 좋아요 삭제
	public int deleteHeartByBoard(Integer id);
	// 좋아요 수 조회
	public List<LoveHeart> selectByLikeUser(Integer id);
	// 게시물에 좋아요를 누른 유저 조회
	public List<LoveHeart> selectByBoardIdAndLikeUser(@Param("id") Integer id, @Param("userId") String userId);
	
	// 페이징용
	public List<Board> selectBoardListLimit(@Param("index") Integer index, @Param("limitCount") Integer limitCount);
	// 게시글 수량
		public BoardDto selectBoardCountByMemberId(String memberId);
}

