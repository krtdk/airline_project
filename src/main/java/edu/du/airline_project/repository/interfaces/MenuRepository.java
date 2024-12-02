package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.response.MenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 서영
 *
 */
@Mapper
public interface MenuRepository {

	public MenuDto selectBySubMenuAndType(@Param("subMenu") String subMenu, @Param("type") String type);
	
	public List<MenuDto> selectByMainId(Integer mainId);
	
}
