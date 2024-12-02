package edu.du.airline_project.repository.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardThumbnail {
	
	private Integer id;
	
	private MultipartFile file;
	
	// 원래 이미지 명
	private String originalFileName;
	// 실제 업로드 된 이미지 명
	private String uploadFileName;


}
