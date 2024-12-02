package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.SeatGrade;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 서영
 *
 */
@Mapper
public interface SeatGradeRepository {
	
	public SeatGrade selectByName(String name);
	
}