package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.GifticonDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GifticonRepository {

	public List<GifticonDto> selectGifticonList(@Param("id")String id,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("radio")String radio);
	public List<GifticonDto> selectGifticonListForManager(@Param("id")String id,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("radio")String radio);
	public int deleteGifticonBygifticonId(@Param("gifticonId")String[] gifticonId);
	public int insertRevokeGifticon(String gifticonId);
	public GifticonDto selectGifticonLimit();
	// 기프티콘 상태값 업데이트
	public int updateGifticonStatus(@Param("gifticonId")String gifticonId);
	public int updateGifticonStatusChange2(@Param("gifticonId")String gifticonId);
	public GifticonDto selectGifticonCount(String memberId);
}
