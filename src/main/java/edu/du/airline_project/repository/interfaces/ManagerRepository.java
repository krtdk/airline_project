package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerRepository {

	public Manager selectById(String id);

	/**
	 * @author 서영
	 * @return 전체 관리자 목록
	 */
	public List<Manager> selectManagerListAll();
	
	/**
	 * @author 서영
	 * @return 전체 관리자 목록 (페이징용) 20개씩
	 */
	public List<Manager> selectManagerListAllLimit(Integer index);
	
	/**
	 * @author 서영
	 * @return 관리자 검색
	 */
	public List<Manager> selectManagerListSearch(String search);
	
	/**
	 * @author 서영
	 * @return 관리자 등록
	 */
	public Integer insert(Manager manager);
	
	
}
