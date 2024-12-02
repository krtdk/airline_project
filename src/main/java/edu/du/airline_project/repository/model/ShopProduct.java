package edu.du.airline_project.repository.model;

import edu.du.airline_project.utils.NumberUtil;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ShopProduct {
	private int id;
	private String brand;
	private String name;
	private Long price;
	private int count;
	private String productImage;
	private String gifticonImage;
	private MultipartFile file;
	private String originFileName;
	private MultipartFile file2;
	private String originFileName2;
	public String priceNumber() {
		return NumberUtil.numberFormat(price);
	}
}
