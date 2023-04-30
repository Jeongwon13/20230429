<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Hello</title>
   
    <link rel="stylesheet" href="${contextPath}/resources/css/mainstyle.css">
   
</head>
<body>
    
    <main>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />

    <section class="mainSec">

    </section>

    
    <section class="mainSec">
    
    <c:choose>
    	<c:when test="${empty sessionScope.loginMember }">
    <form action="member/login" method="post" onsubmit="return loginValidate()">
        <fieldset id="login">
            <table>
            <tr>
                <th>아이디</th>
                <td><input type="text" name="memberEmail" ></td>
                <td><button>로그인</button></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="memberPw"></td>
            </tr>
            <tr> 
                <th></th>

                <td>
                    <a href="#">ID/PW찾기</a> 
                    <a href="#">회원가입</a> 
                </td>
                <td></td>
            </tr>
        </table>
        </fieldset>
         <c:if test="${ !empty cookie.saveId.value}">
                      <c:set var="chk" value="checked"/>
                            </c:if>
		                    <label>
		                        <input type="checkbox" name="saveId" ${chk}  id="saveId"> 아이디 저장
		                    </label>
		                    <article id="signup-find-area">
		                        <a href="${contextPath}/member/signUp">회원가입</a> 
		                        <span>|</span>
		                        <a href="#">ID/PW 찾기</a>
		                    </article>

    </form>
 	 	</c:when>
 	 	<c:otherwise>
 	 		<article class="login-area">
                            <!-- 회원 프로필 이미지 -->
            				<a href="${contextPath}/member/myPage/profile">
                                
                                <c:if test="${empty loginMember.profileImage}">
                                    <img src="${contextPath}/resources/images/user.png" id="member-profile">
                                </c:if>

                                <c:if test="${!empty loginMember.profileImage}">
                                    <img src="${contextPath}${loginMember.profileImage}" id="member-profile">
                                </c:if>

            				</a>

                            <!-- 회원 정보 + 로그아웃 버튼 -->                                       
                            <div class="my-info">
                                <div>
                                    <a href="${contextPath}/member/myPage/info" id="nickname">${loginMember.memberNickname}</a>

                                    <a href="${contextPath}/member/logout" id="logout-btn">로그아웃</a>
                                </div>

                                <p>
                                    ${loginMember.memberEmail}
                                </p>
                            </div>
            			</article>
 	 	</c:otherwise>
    </c:choose>
    </section>
    
    
    </main>
      

       <!-- jQuery 라이브러리 추가 -->
       <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
      
       <!-- main.js 연결 -->
       <script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>