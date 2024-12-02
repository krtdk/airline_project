package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.repository.model.Airplane;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 서영
 *
 */
@Mapper
public interface AirplaneRepository {

	public List<Airplane> selectAll();
	
}
