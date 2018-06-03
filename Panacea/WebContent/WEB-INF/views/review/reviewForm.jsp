<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<% int rno =(int)request.getAttribute("rno"); %>
 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
function fn_validate(){
	var title = $("[name=title]").val();
	var content = $("[name=content]").val();
	if(title.trim().length==0){
		alert("제목을 입력하세요");
		return false;
	}
	
	if(title.length>=30){
		alert("제목은 30글자까지 입력 가능합니다");
		return false;
	}

	if(content.trim().length==0){
	    alert("내용을 입력하세요");
	    return false;
	}
	return true;
}



</script>


<div id="subnav-wrapper">
   <span id="subnav-title"></span>
   <div id="sub_menu">
       <p><a href="<%=request.getContextPath()%>/review/reviewList">게시판으로 돌아가기</a></p>

   </div>
</div>

<div id="content-wrapper">
	<div id="review-container">
		<h2>후기 작성</h2>
		<form action="<%=request.getContextPath()%>/review/reviewFormEnd" method="post">
			<table id="tbl-review-form">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" required/>
						<input type="hidden" name="rno" value="<%=rno %>" />
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="writer" value="<%=loginPatient.getPatientId()%>" readonly required"/>
					</td>
				</tr>
				<tr>
					<th>평점</th>
					<td>
						<select name="grade" id="grade">
						<option value="1">1 점</option>
						<option value="2">2 점</option>
						<option value="3">3 점</option>
						<option value="4">4 점</option>
						<option value="5">5 점</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="content" cols="50" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="등 록 하 기" onclick="return fn_validate();" />
					</th>
				</tr>
			</table>
		</form>
	</div>
   
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>