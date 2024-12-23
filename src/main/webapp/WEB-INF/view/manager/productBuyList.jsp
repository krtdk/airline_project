<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../layout/headerManager.jsp"%>
<style>
.tab_wrap6 li {
	float: left;
	cursor: pointer;
}

#tab_title {
	text-align: center;
	margin-top: 20px;
}

.sortation {
	margin-left: 300px;
}

#checkboxList {
	margin-top: 20px;
	font-size: 20px;
}

form {
	background-color: rgb(240, 240, 240);
	padding: 30px 50px;
}

.container {
	margin-top: 20px;
}

.mileage--list {
	margin-top: 20px;
}

h4#tab_title {
	text-align: center;
	margin-top: 20px;
}

.searchKind, input[type=radio] {
	width: 17px;
	height: 14px;
}

.li--period, .li--period--s {
	width: 105px;
	height: 46px;
	background-color: white;
	text-align: center;
	padding: 10px;
	border: 1px solid #ccc;
}

.tab_wrap6 ul {
	height: 60px;
}

.tab_wrap6 {
	margin-left: 300px;
}

.calendar_wrap {
	margin-left: 300px;
}

.btn-light {
	padding-top: 0; 
	margin-top:8px;
	height: 30px;
	text-align: center;
	width: 121px;
}
#gifticon--search {
	background-color: gray;
	padding: 2px 6px;
	border: none;
	border-radius: 5px;
	color: white;
	height: 28px;
	margin-left: 10px;
}
#gifticonList{
	background-color: white;
}
label {
	cursor: pointer;
}

.buy{
	margin-right: 15px;
}


#sCalendar01, #sCalendar02, #searchMemberId {
	width: 120px;
	text-align: center;
	border: 1px solid #ccc;
	outline: none;
	border-radius: 5px;
	padding: 2px 2px;
}

</style>
<!-- 여기 안에 쓰기 -->
<main>
	<h2 class="page--title">마일리지샵 이용 내역</h2>
	<hr>
	<br>
	
	<div class="container">
		<form action="mileageList" method="get" id="form">
			<div id="checkboxList"></div>

			<dl>
				
				<dd>
					<strong class="sortation"></strong><label><input type="radio" name="chk" value="buy" checked="checked"><span class="buy">&nbsp;구매내역</span></label>
					<label><input type="radio" name="chk" value="revoke">&nbsp;취소내역</label>
				</dd>
			</dl>
			<dl>
				<dd id="dd_period_detail">
					<div class="tab_wrap6">
						<ul>
							<li id="liPeriod1" class="li--period"><a>1개월</a></li>
							<li id="liPeriod3" class="li--period"><a>3개월</a></li>
							<li id="liPeriod6" class="li--period"><a>6개월</a></li>
							<li id="liPeriod12" class="li--period"><a>1년</a></li>
							<li id="liPeriods" class="li--period--s"><a>직접 선택</a></li>
						</ul>
					</div>
				</dd>
			</dl>
			<dl>
				<dd>

					<div class="relative_calendar">
						<div class="calendar_wrap">
							<input type="text" name="startTime" title="시작일" id="sCalendar01" class="datepicker input_cal" placeholder="시작일 선택" data-dateformat="y.mm.dd D" data-type="single_infinite">
							<span class="mid_txt">&nbsp;~&nbsp;</span>
							<input type="text" name="endTime" title="종료일" id="sCalendar02" class="datepicker input_cal" placeholder="종료일 선택" data-dateformat="y.mm.dd D" data-type="single_infinite_last"> <a
								href="#none" class="btn_airport type2 calendar_focus"></a>
							<label style="margin-left: 20px;">회원 아이디</label>
							<input type="text" name="memberId" class="mamberId" id="searchMemberId" placeholder="회원 아이디">
							<button type="button" id="gifticon--search">
								<ul class="d-flex justify-content-center" style="margin: 0;">
									<li style="height: 24px; margin-right: 2px;">조회
									<li style="height: 24px;"><span class="material-symbols-outlined material-symbols-outlined-white" style="font-size: 18px; padding-top: 4px;">search</span></li>
								</ul>
							</button>
						</div>
					</div>
				</dd>
			</dl>
		</form>
		<form action="/gifticon/deleteGifticonById" method="post" id ="gifticonList">
			<table class="table table-bordered list--table">
				<thead id="gifticonList--tr--thead">

				</thead>
				<tbody id="gifticonList--tr--tbody">

				</tbody>
			</table>
			<input type="hidden" name="gifticonId" value="" id="gifticonId"> <input type="hidden" name="productId" value="" id="productId"> <input type="hidden" name="amount" value=""
				id="gifticonAmount"> <input type="hidden" name="name" value="" id="gifticonName"> <input type="hidden" name="brand" value="" id="gifticonBrand">
		</form>
	</div>
</main>

<script src="/js/buyProduct.js">
	
</script>

<input type="hidden" name="menuName" id="menuName" value="상품 판매 내역">

<%@ include file="../layout/footer.jsp"%>
