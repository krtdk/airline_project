package edu.du.airline_project.repository.interfaces;

import edu.du.airline_project.dto.*;
import edu.du.airline_project.dto.response.ProductBrandOrderAmountDto;
import edu.du.airline_project.repository.model.Mileage;
import edu.du.airline_project.repository.model.ShopOrder;
import edu.du.airline_project.repository.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductRepository {

	public int insert(ShopProduct shopProduct);
	public List<ShopProduct> selectProductList();
	public List<ShopProduct> selectProductList(@Param("searchOrder")String searchOrder,@Param("paging")PagingVO paging);
	
	public List<ShopProduct> ProductListTest(PagingVO paging);
	public int getTotalRowCount(PagingVO paging);
	public int getSerchTotalRowCount(@Param("searchProduct") String searchProduct, @Param("searchOption")String searchOption,@Param("paging")PagingVO paging);

	
	public ShopProduct selectById(int id);
	public int updateProduct(ShopProduct shopProduct);
	public int deleteProduct(int id);
	public int buyProduct(ShopOrder shopOrder);
	public int insertShopProductDto(ShopOrderDto  productDto);
	public int insertGifticonDto(GifticonDto gifticonDto);
	public ShopOrder selectShopOrder(String memberId);
	public Mileage selectMileage(String memberId);
	public int insertMileage(UseMileageDto useMileageDto);
	public int updateShopProductDto(ShopProductDto  shopProductDto);
	public List<ShopProduct> selectProductByName(@Param("searchProduct") String searchProduct, @Param("searchOption")String searchOption,@Param("paging")PagingVO paging);
	
	/**
	 *   
	 * 구매내역 + 상품 조인해서 구매량이 많은 상위 5개 물품 가져옴
	 */
	public List<ProductBrandOrderAmountDto> selectSumAmountGroupByProductLimitN(Integer limitCount);
}
