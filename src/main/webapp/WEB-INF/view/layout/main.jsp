<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${\"관리자\".equals(principal.userRole)}">
		<%@ include file="headerManager.jsp"%>
	</c:when>
	<c:otherwise>
		<%@ include file="header.jsp"%>
	</c:otherwise>
</c:choose>

	
	<main>
		
	</main>



<%@ include file="footer.jsp"%>
