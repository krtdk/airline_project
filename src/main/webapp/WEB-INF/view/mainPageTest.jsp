<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${\"관리자\".equals(principal.userRole)}">
		<%@ include file="layout/headerManager.jsp"%>
	</c:when>
	<c:otherwise>
		<%@ include file="layout/header.jsp"%>
	</c:otherwise>
</c:choose>

<link rel="stylesheet" href="/css/mainPage.css">

<div class="main--img--div">
	<div style="width: 1178px; margin-left: 24px;">
		<div class="menu--name--div">
			<span class="material-symbols-outlined" style="margin: 1px 6px 0 0">airplane_ticket</span>
			<span>항공권 예약</span>
		</div>
	</div>
	<div class="ticket--option--div">
		<form action="/ticket/selectSchedule/search" method="get">
			<input type="hidden" name="ticketType" value="1" id="ticketTypeInput">
			<ul class="ticket--type">
				<li id="ticketType1" onclick="selectedType(1);" class="selected--type">왕복
				<li id="ticketType2" onclick="selectedType(2);">편도
			</ul>
			<div class="d-flex align-items-end">
				<!-- 출발지 -->
				<div id="departureDiv">
					<p class="option--name--p">
						<span class="material-symbols-outlined">flight_takeoff</span>
						<span>출발지</span>
					</p>
					<input type="text" name="departure" placeholder="FROM" id="departure" autocomplete="off">
					<!-- 자동완성 및 공항 선택 -->
					<div class="airport--div" id="departureAirport">
						<div class="d-flex justify-content-between" style="margin-bottom: 15px;">
							<h5>자동 완성</h5>
							<button class="close--button" onclick="$('#departureAirport').hide();" type="button">
								<span class="material-symbols-outlined">close</span>
							</button>
						</div>
						<ul class="autocomplete--list" id="departureList"></ul>
						<div class="d-flex justify-content-center" style="width: 100%">
							<button class="all--airport departure--button" type="button">
								<ul class="d-flex justify-content-center" style="margin: 0;">
									<li><span class="material-symbols-outlined material--li" style="font-size: 22px;">location_on</span>
									<li style="font-size: 15px;">전체 공항 조회
								</ul>
							</button>
						</div>
					</div>
				</div>

				<!-- 출발지 도착지 서로 바꾸는 버튼 -->
				<button id="airportSwapBtn" onclick="airportSwap();" type="button">
					<span class="material-symbols-outlined" style="color: #4c4c4c; font-size: 28px;">swap_horiz</span>
				</button>

				<!-- 도착지 -->
				<div id="destinationDiv">
					<p class="option--name--p">
						<span class="material-symbols-outlined">flight_land</span>
						<span>도착지</span>
					</p>
					<input type="text" name="destination" placeholder="TO" id="destination" autocomplete="off">
					<!-- 자동완성 및 공항 선택 -->
					<div class="airport--div" id="destinationAirport">
						<div class="d-flex justify-content-between" style="margin-bottom: 15px;">
							<h5>자동 완성</h5>
							<button class="close--button" onclick="$('#destinationAirport').hide();" type="button">
								<span class="material-symbols-outlined">close</span>
							</button>
						</div>
						<ul class="autocomplete--list" id="destinationList"></ul>
						<div class="d-flex justify-content-center" style="width: 100%">
							<button class="all--airport destination--button" type="button">
								<ul class="d-flex justify-content-center" style="margin: 0;">
									<li><span class="material-symbols-outlined material--li" style="font-size: 22px;">location_on</span>
									<li style="font-size: 15px;">전체 공항 조회
								</ul>
							</button>
						</div>
					</div>
				</div>

				<!-- 탑승일 선택 -->
				<div id="flightDateDiv">
					<p class="option--name--p">
						<span class="material-symbols-outlined">calendar_month</span>
						<span>탑승일</span>
					</p>
					<input type="text" id="flightDate" placeholder="가는 날 ~ 오는 날" readonly>
					<!-- 날짜 선택 -->
					<!-- 왕복 -->
					<div class="datepicker--div--type1">
						<div class="d-flex justify-content-between" style="margin-bottom: 15px;">
							<h5>날짜 선택</h5>
							<button class="close--button" onclick="$('.datepicker--div--type1').hide();" type="button">
								<span class="material-symbols-outlined">close</span>
							</button>
						</div>
						<div style="background-color: #f3f3f3; padding: 10px; margin-bottom: 10px;">
							<div class="datepicker--div">
								<label for="flightDate1">가는 날</label>
								<input type="text" class="datepicker flight--date1" id="flightDate1" name="flightDate1" autocomplete="off">
							</div>
							<div class="datepicker--div">
								<label for="flightDate2">오는 날</label>
								<input type="text" class="datepicker flight--date2" id="flightDate2" name="flightDate2" autocomplete="off">
							</div>
						</div>
					</div>
					<!-- 편도 -->
					<div class="datepicker--div--type2">
						<div class="d-flex justify-content-between" style="margin-bottom: 15px;">
							<h5>날짜 선택</h5>
							<button class="close--button" onclick="$('.datepicker--div--type2').hide();" type="button">
								<span class="material-symbols-outlined">close</span>
							</button>
						</div>
						<div class="datepicker--div" style="background-color: #f3f3f3; padding: 10px; margin-bottom: 10px;">
							<label for="flightDate0">가는 날</label>
							<input type="text" class="datepicker flight--date" id="flightDate0" name="flightDate0" autocomplete="off">
						</div>
					</div>
				</div>

				<div id="passengerDiv">
					<p class="option--name--p">
						<span class="material-symbols-outlined">group</span>
						<span>탑승인원</span>
					</p>
					<input type="text" id="passenger" placeholder="탑승인원" value="성인1" readonly>
					<div id="ageTypeDiv">
						<div class="d-flex justify-content-between" style="margin-bottom: 15px;">
							<h5 style="margin-bottom: 10px;">탑승인원 선택</h5>
							<button class="close--button" onclick="$('#ageTypeDiv').hide();" type="button">
								<span class="material-symbols-outlined">close</span>
							</button>
						</div>
						<div class="d-flex align-items-end">
							<!-- 탑승 인원 선택 -->
							<div style="margin-right: 30px;" class="d-flex flex-column align-items-center">
								<!-- 성인은 최소 1명 -->
								<label>성인</label>
								<div class="age--type--div">
									<button class="minus--button" type="button">-</button>
									<input type="number" min="0" max="99" value="1" id="ageType1" readonly name="adultCount">
									<button class="plus--button" type="button">+</button>
								</div>
							</div>

							<div style="margin-right: 30px;" class="d-flex flex-column align-items-center">
								<label>소아</label>
								<div class="age--type--div">
									<button class="minus--button" type="button">-</button>
									<input type="number" min="0" max="99" value="0" id="ageType2" readonly name="childCount">
									<button class="plus--button" type="button">+</button>
								</div>
							</div>

							<div class="d-flex flex-column align-items-center">
								<label>유아</label>
								<div class="age--type--div">
									<button class="minus--button" type="button">-</button>
									<input type="number" min="0" max="99" value="0" id="ageType3" readonly name="infantCount">
									<button class="plus--button" type="button">+</button>
								</div>
							</div>
							<button class="age--calculater" type="button">
								<ul class="d-flex justify-content-center" style="margin: 0;">
									<li><span class="material-symbols-outlined material--li" style="font-size: 22px;">calculate</span>
									<li style="font-size: 15px;">나이 계산기
								</ul>
							</button>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-end" style="width: 100%">
					<button class="blue--btn--middle" id="schedulePageBtn" type="submit">
						<ul class="d-flex justify-content-center" style="margin: 0;">
							<li style="margin-right: 3px;">항공권 조회
							<li><span class="material-symbols-outlined material-symbols-outlined-white" style="font-size: 25px; margin-top: 3px;">search</span>
						</ul>
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<main>


	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	<!-- 여기에 Swiper 코드 추가 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	<h4 style="margin-top: 50px;">추천 항공권</h4>
	<div class="swiper-section" style="margin-top: 20px;">
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20241016112140155.png" alt="Image 1" />
					<p class="slide-text">
						<span class="slide-title">광주 - 제주</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">23,600원~</span></span>
						<span class="slide-date">2024.12.15~2025.01.15</span>
					</p>
				</div>
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20241016112446863.jpg" alt="Image 2" />
					<p class="slide-text">
						<span class="slide-title">서울(인천) - 덴파사르(발리)</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">155,400원~</span></span>
						<span class="slide-date">2024.12.15~2025.01.15</span>
					</p>
				</div>
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20241016112401120.jpg" alt="Image 3" />
					<p class="slide-text">
						<span class="slide-title">서울(인천) - 바탐</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">100,400원~</span></span>
						<span class="slide-date">2024.12.15~2025.01.15</span>
					</p>
				</div>
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20241023181800688.png" alt="Image 3" />
					<p class="slide-text">
						<span class="slide-title">부산 - 제주</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">29,600원~</span></span>
						<span class="slide-date">2024.12.15~2024.12.31</span>
					</p>
				</div>
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20241008195048126.png" alt="Image 3" />
					<p class="slide-text">
						<span class="slide-title">부산 - 가오슝</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">135,700원~</span></span>
						<span class="slide-date">2024.12.15~2025.01.15</span>
					</p>
				</div>
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20240911141209988.png" alt="Image 3" />
					<p class="slide-text">
						<span class="slide-title">무안 - 제주</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">23,600원~</span></span>
						<span class="slide-date">2024.12.15~2025.01.15</span>
					</p>
				</div>
				<div class="swiper-slide"><img src="https://static.jejuair.net/cms/images/immigration_card/20221020133622037.png" alt="Image 3" />
					<p class="slide-text">
						<span class="slide-title">서울(인천) - 오사카(간사이)</span>
						<span class="slide-description">편도총액 &nbsp;<span class="description-price">99,000원~</span></span>
						<span class="slide-date">2024.12.15~2025.01.15</span>
					</p>
				</div>
			</div>
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
			<div class="swiper-pagination"></div>
		</div>

		<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
		<script>
			const swiper = new Swiper('.swiper-container', {
				slidesPerView: 3,
				centeredSlides: true,
				spaceBetween: 10,
				loop: true,
				pagination: { el: '.swiper-pagination', clickable: true },
				navigation: { nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev' },
				speed: 500,
			});
		</script>
	</div>

<!-- 전체 공항 조회 모달 -->
<div class="modal fade header--modal all--airport--modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal--title--div">
				<h4 class="modal--title"></h4>
				<button class="close--button" onclick="$('.all--airport--modal').modal('hide');">
					<span class="material-symbols-outlined">close</span>
				</button>
			</div>
			<div class="modal-body d-flex">

				<!-- 지역 목록 -->
				<ul style="min-width: 210px; margin: 0 20px 0 0">
					<c:forEach var="r" items="${regionList}">
						<li class="region--li">${r.region}</li>
					</c:forEach>
				</ul>

				<!-- 지역을 선택하면 취항지 불러오기 -->
				<div style="width: 100%;">
					<h5 style="margin: 0">취항지</h5>
					<hr style="margin: 10px 0 20px;">
					<ul class="airport--ul">
					</ul>
				</div>

			</div>
		</div>
	</div>
</div>

<!-- 나이 계산기 모달 -->
<div class="modal fade header--modal age--calculater--modal">
	<div class="modal-dialog" style="max-width: 350px;">
		<div class="modal-content">
			<div class="modal--title--div">
				<h4 class="modal--title">나이 계산기</h4>
				<button class="close--button" onclick="$('.age--calculater--modal').modal('hide');">
					<span class="material-symbols-outlined">close</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="d-flex align-items-center justify-content-center" style="margin: 10px;">
					<div style="margin-right: 20px;">
						<div class="d-flex" style="margin-bottom: 10px;">
							<label for="birthDate" style="width: 80px; margin: 0">생년월일</label> <input type="text" id="birthDate" placeholder="yyyy-mm-dd">
						</div>
						<div class="d-flex">
							<label for="thisDate" style="width: 80px; margin: 0">탑승일</label> <input type="text" id="thisDate" placeholder="yyyy-mm-dd">
						</div>
					</div>
					<button class="search--btn" id="calculateBtn">
						<ul class="d-flex justify-content-center" style="margin: 0;">
							<li style="height: 24px; margin-right: 2px;">조회
							<li style="height: 24px;"><span class="material-symbols-outlined material-symbols-outlined-white" style="font-size: 18px; padding-top: 4px;">touch_app</span>
						</ul>
					</button>
				</div>
				<div id="calculaterResult"></div>
			</div>
		</div>
	</div>
</div>
</main>

	<div class="main-util pc-only">
		<div class="main-util__inner">
			<div class="main-util__cs">
				<h4 class="main-util__title">고객센터 1599-1500 (09:00~19:00)</h4>
				<p class="main-util__text"></p>
				<div class="button-wrap">
					<button class="button button--small" ep_event_area="고객센터" ep_event_button="고객센터 바로가기"
							ep_language_environment="ko-KR" ep_visit_channel_option="PC-WEB" ep_visit_login_yn="Y"
							event_name='click_footer'
							onclick="sendGAAttrEvent(event);location.href='/ko/customerService/csCenter/faqList.do'"
							type="button">
						<span class="button__text">고객센터 바로가기</span>
					</button>
				</div>
			</div>

			<div class="main-util__social">
				<h4 class="main-util__title">SNS</h4>
				<div class="main-social">
					<a class="main-social__list" ep_event_area="SNS" ep_event_button="www.youtube.com/user/funjejuair"
					   ep_language_environment="ko-KR" ep_visit_channel_option="PC-WEB" ep_visit_login_yn="Y"
					   event_name='click_footer' href="https://www.youtube.com/user/funjejuair"
					   onclick="sendGAAttrEvent(event);" target="_blank">
						<i class="icon"><img
								alt="" loading="lazy"
								src="https://static.jejuair.net/cms/images/sns_ch/20211027200700139.png"
								hred="https://www.youtube.com/user/funjejuair"></i>
						<span class="hidden"></span>
					</a>

					<a class="main-social__list" ep_event_area="SNS" ep_event_button="instagram.com/jejuair_official"
					   ep_language_environment="ko-KR" ep_visit_channel_option="PC-WEB" ep_visit_login_yn="Y"
					   event_name='click_footer' href="https://instagram.com/jejuair_official"
					   onclick="sendGAAttrEvent(event);" target="_blank">
						<i class="icon"><img
								alt="" loading="lazy"
								src="https://static.jejuair.net/cms/images/sns_ch/20211027200545316.png"></i>
						<span class="hidden">   </span>
					</a>

					<a class="main-social__list" ep_event_area="SNS" ep_event_button="www.tiktok.com/@jejuair.tiktok"
					   ep_language_environment="ko-KR" ep_visit_channel_option="PC-WEB" ep_visit_login_yn="Y"
					   event_name='click_footer' href="https://www.tiktok.com/@jejuair.tiktok"
					   onclick="sendGAAttrEvent(event);" target="_blank">
						<i class="icon"><img
								alt="" loading="lazy"
								src="https://static.jejuair.net/cms/images/sns_ch/20230213114244655.png"></i>
						<span class="hidden"></span>
					</a>

					<a class="main-social__list" ep_event_area="SNS" ep_event_button="www.facebook.com/funjejuair/"
					   ep_language_environment="ko-KR" ep_visit_channel_option="PC-WEB" ep_visit_login_yn="Y"
					   event_name='click_footer' href="https://www.facebook.com/funjejuair/"
					   onclick="sendGAAttrEvent(event);" target="_blank">
						<i class="icon"><img
								alt="" loading="lazy"
								src="https://static.jejuair.net/cms/images/sns_ch/20211027200713454.png"></i>
						<span class="hidden"></span>
					</a>


				</div>
			</div>

			<div class="main-util__contact">
				<h4 class="main-util__title">
					마케팅 제휴<span class="mailto">partnership@jejuair.net</span>
				</h4>
				<p class="main-util__text">브랜드 협업, 미디어 노출, 제휴 프로모션 등 제안을 기다립니다. <br>제안별로 담당자 검토 후 제휴가 진행됩니다.</p>
				<div class="main-util__mail">
					<a class="block" data-element="modal_anchor" data-modal-type="full"
					   data-target="#divModalEmailNoTerms" ep_event_area="마케팅 제휴"
					   ep_event_button="이메일 무단 수집 거부" ep_language_environment="ko-KR" ep_visit_channel_option="PC-WEB"
					   ep_visit_login_yn="Y" event_name='click_footer'
					   href="javascript:void(0);" onclick="sendGAAttrEvent(event);">
						이메일 무단 수집 거부</a>
				</div>
			</div>
		</div>
	</div>



<script src="/js/ticket.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<script>
	$(document).ready(function() {

		// 탑승객 수 증감
		$(".minus--button, .plus--button").on("click", function() {
			insertPassenger();
		});

		// 스케줄 조회 버튼
		$("#schedulePageBtn").on("click", function() {
			// 1 : 왕복, 2 : 편도
			let ticketType = $(".selected--type").index() + 1;
			$("#ticketTypeInput").val(ticketType);
			// 취항지 1
			let airport1 = $("#departure").val();
			if (airport1 == "") {
				alert("출발지가 선택되지 않았습니다.");
				return false;
			}

			// 취항지 2
			let airport2 = $("#destination").val();
			if (airport2 == "") {
				alert("도착지가 선택되지 않았습니다.");
				return false;
			}

			// 성인
			let ageType1 = parseInt($("#ageType1").val());
			if (ageType1 == 0) {
				alert("최소 1명의 성인이 필요합니다.");
				return false;
			}

			// 유아
			let ageType3 = parseInt($("#ageType3").val());
			if (ageType3 > ageType1) {
				alert('유아의 수는 동반 성인의 수보다 많을 수 없습니다.');
				return false;
			}

			// 왕복
			if (ticketType == 1) {
				// 왕복 - 탑승일 1 (가는 날)
				let flightDate1 = $("#flightDate1").val();
				// 왕복 - 탑승일 2 (오는 날)
				let flightDate2 = $("#flightDate2").val();

				// 선택하지 않은 날짜가 있다면
				if (flightDate1 == "" || flightDate2 == "") {
					alert("선택되지 않은 날짜가 존재합니다.");
					return false;
				}
				// 편도
			} else {
				// 탑승일
				let flightDate = $("#flightDate0").val();
				// 선택하지 않은 날짜가 있다면
				if (flightDate == "") {
					alert("선택되지 않은 날짜가 존재합니다.");
					return false;
				}
			}
		});

	});

	$(document).on("click", function(e) {
		if ($("#passengerDiv").has(e.target).length === 0) {
			$("#ageTypeDiv").hide();
		}
	});
	const swiper = new Swiper('.swiper-container', {
		slidesPerView: 1,
		spaceBetween: 10,
		loop: true,
		pagination: {
			el: '.swiper-pagination',
			clickable: true,
		},
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
		speed: 500,
	});
</script>

<style>
	.swiper-section {
		max-width: 1200px;
		margin: 0 auto;
	}
	.swiper-container {
		width: 100%;
		height: 500px;
		position: relative;
		overflow: hidden;
	}
	.swiper-slide {
		display: flex;
		flex-direction: column;
		align-items: center;
		text-align: center;
		padding: 1px;
	}
	.swiper-slide img {
		width: 250px;
		height: 250px;
		object-fit: cover;
		border-radius: 50%;
		padding: 2px;
		background-color: #f0f0f0;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
		margin-bottom: 10px;
	}
	.swiper-slide .slide-text {
		font-size: 14px;
		color: #333;
	}
	.swiper-button-next,
	.swiper-button-prev {
		color: #333;
		z-index: 10;
		top: 50%;
		transform: translateY(-50%);
	}
	.swiper-button-next {
		right: 10px;
	}
	.swiper-button-prev {
		left: 10px;
	}
	.swiper-pagination {
		display: none;
	}
	.main-util{
		background-color: #F8F8F8;
		width: 100%; /* 가로폭 전체 적용 */
		margin: 0; /* 여백 제거 */
		padding: 0; /* 여백 제거 */
		box-sizing: border-box; /* 패딩 포함 */
	}
	.main-util__inner {
		display: flex;
		justify-content: space-between;
		max-width: 1200px; /* 최대 폭 설정 */
		margin: 0 auto; /* 가운데 정렬 */
	}
	.main-util__cs, .main-util__social, .main-util__contact {
		flex: 1;
		max-width: 33.33%;
		padding: 10px;
		box-sizing: border-box;
		font-size: 0.9em; /* 텍스트 크기를 줄임 */
	}
	.main-util__title {
		font-size: 1em; /* 제목 크기 줄임 */
	}
	.main-util__text, .button__text, .mailto {
		font-size: 0.8em; /* 내용 텍스트 크기 줄임 */
	}
	.main-social .icon img {
		width: 30px; /* 아이콘 크기 줄임 */
		height: 30px;
		margin-right: 5px;
	}
	.button--small {
		padding: 5px 10px; /* 버튼 크기 줄임 */
	}
	.slide-text {
		text-align: center;
		margin-top: 10px;
	}

	.slide-title {
		font-size: 18px;
		font-weight: bold;
	}

	.slide-description {
		font-size: 14px;
		font-weight: normal;
		color: #555;
		display: block;
		margin: 5px 0;
	}

	.slide-date {
		font-size: 12px;
		color: #888;
		display: block;
	}
	.description-price{
		font-size: 25px;
		font-weight: bold;
		color: black;
	}
</style>

<input type="hidden" name="menuName" id="menuName" value="">

<%@ include file="layout/footer.jsp"%>
