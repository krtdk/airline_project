package edu.du.airline_project.service;

import edu.du.airline_project.repository.interfaces.BaggageRepository;
import edu.du.airline_project.repository.model.CarryOnLiquids;
import edu.du.airline_project.repository.model.CheckedBaggage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggageService {

	@Autowired
	private BaggageRepository baggageRepository;

	// 운송 제한 품목의 휴대 반입 액체류 안내 나라 이름 갖고 오기
	public List<CarryOnLiquids> readLiquids() {
		List<CarryOnLiquids> carryOnLiquids = baggageRepository.selectLiquids();
		return carryOnLiquids;
	}

	// 운송 제한 품목의 휴대 반입 액체류 안내 -> BaggageApiController
	public CarryOnLiquids readLiquidsByName(String name) {
		CarryOnLiquids carryOnLiquidsEntity = baggageRepository.selectLiquidsByName(name);

		return carryOnLiquidsEntity;
	}

	// 위탁 수하물 중 구간 선택에 따른 무료 수하물 허용량 -> BaggageApiController
	public List<CheckedBaggage> readCheckedBaggageBySection(String section) {
		List<CheckedBaggage> baggages = baggageRepository.selectCheckedBaggageBySection(section);

		return baggages;
	}

	// 위탁 수하물 카테고리
	public List<CheckedBaggage> readCheckedBaggage() {
		List<CheckedBaggage> checkedBaggageEntity = baggageRepository.selectCheckedBaggage();

		return checkedBaggageEntity;
	}


}
