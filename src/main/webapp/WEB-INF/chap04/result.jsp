<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
<%--  <%--%>
<%--    String name = (String) request.getAttribute("name");--%>
<%--    String crew = (String) request.getAttribute("crew");--%>
<%--  %>--%>


<%-- getter로 불러와도 되지만, 필드의 이름만 적어도 getter를 자동 호출한다.--%>
<%-- getter가 없으면 에러난다 --%>
  <h1>댄서 등록 결과 페이지 입니다.</h1>
  <h2>${dancer.getName()}님(소속: ${dancer.getCrewName()})이 정상 등록되었습니다~</h2>
  <h3>댄스 수준: ${dancer.getDanceLevel()}</h3>

  <a href="/chap04/dancer/form">새로운 댄서 등록하러 가기</a> <br>
  <a href="/chap04/show-list">댄서 목록 조회하기</a> <br>
</body>
</html>