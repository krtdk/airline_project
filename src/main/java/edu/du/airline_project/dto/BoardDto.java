package edu.du.airline_project.dto;

import edu.du.airline_project.utils.NumberUtil;
import edu.du.airline_project.utils.TimestampUtil;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class BoardDto {

	private Integer id;
	private String title;
	private String content;
	private String userId;
	private Integer viewCount = 0;
	private Timestamp createdAt;
	private Integer heartCount = 0;
	private boolean statement;
	private int count; // 계시글 count

	private MultipartFile file;

	// 원래 이미지 명
	private String originName;
	// 실제 업로드 된 이미지 명
	private String fileName;

	// 날짜 YYYY-MM-DD
	public String formatDate() {
		return TimestampUtil.dateToString(createdAt);
	}

	// 숫자 #,###
	public String numberFormat() {
		return NumberUtil.numberFormat(viewCount);
	}

	// 썸네일 이미지 경로
	public String thumbnailImage() {
		return fileName == null ? "/images/board/noImage.png" : fileName;
	}

}