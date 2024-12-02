package edu.du.airline_project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardUpdateDto {

	private Integer id;
	private String title;
	private String content;

	private MultipartFile file;

	// 원래 이미지 명
	private String originName;
	// 실제 업로드 된 이미지 명
	private String fileName;

}
