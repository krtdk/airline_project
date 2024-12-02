package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.request.LoginFormDto;
import edu.du.airline_project.dto.request.PasswordCheckDto;
import edu.du.airline_project.dto.response.CountByYearAndMonthDto;
import edu.du.airline_project.enums.UserRole;
import edu.du.airline_project.repository.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

	// 로그인 기능
	public User selectByIdAndPassword(LoginFormDto loginFormDto);

	// 암호화 처리에 사용
	public User selectById(LoginFormDto loginFormDto);
	
	// 아이디 찾기
	public User selectById(String id);
	// user_tb에 넣을 값 (kakao login api에 사용)
	public int insertByUser(@Param("id") String id, @Param("password") String password,
			@Param("userRole") UserRole userRole);

	// 소셜 회원가입에 사용
	public User selectSocialDtoById(String id);

	public int updateUserPwById(@Param("password") String password, @Param("userId") String userId);

	public int updateGradeByMemberId(@Param("memberId") String memberId, @Param("grade") String grade);

	public User selectUserById(String id);

	// id 기반 유저 탈퇴 상태값 변경
	int updateUserByStatus(@Param("id") String id, @Param("status") Integer status);

	// 소셜 회원가입시 회원 정보 삭제
	int deleteSocialUserById(String id);

	// 비밀번호 확인 기능
	int updateUserById(PasswordCheckDto passwordCheckDto);

	/**
	 *    해당 월의 신규 회원 수
	 */
	public CountByYearAndMonthDto selectNewUserCountByMonth(@Param("year") Integer year, @Param("month") Integer month);

	/**
	 *    해당 월의 탈퇴 회원 수
	 */
	public CountByYearAndMonthDto selectWithdrawUserCountByMonth(@Param("year") Integer year,
			@Param("month") Integer month);

	/**
	 *   
	 */
	public Integer insert(User user);
	
}
