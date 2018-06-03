<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String loc = request.getContextPath() + (String)request.getAttribute("loc");
%>

<script>
   <%if(msg.equals("비밀번호 변경 성공!") || 
         (msg.equals("현재 비밀번호를 잘못 입력하셨습니다.") || 
               msg.equals("아이디 오류 발생! 다시 시도하세요.."))){%>
      alert("<%=msg%>");
      self.close();
   <%} else{%>
      alert("<%=msg%>");
      location.href="<%=loc%>";
   <%}%>
</script>