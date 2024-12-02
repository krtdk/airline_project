package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.Memo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 서영
 */
@Mapper
public interface MemoRepository {

	public Integer insert(Memo memo);
	
	public Memo selectByManagerId(String managerId);
	
	public Integer update(Memo memo);
	
}
