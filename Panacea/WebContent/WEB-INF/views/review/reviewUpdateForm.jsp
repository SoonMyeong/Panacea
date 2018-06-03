<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import = "com.panacea.review.model.vo.*, java.util.*" %>
<%
Review review = (Review)request.getAttribute("review");
%>
<script>
function validate(){
	var title = $("[name=title]").val();
	var content = $("[name=content]").val();
	
	if(title.trim().length==0){
		alert("제목을 입력하세요.");
		return false;
	}
	
	if(title.length>=0){
		alert("제목은 30글자까지 입력 가능합니다.");
		return false;
	}
	
	if(content.trim().length==0){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
}
</script>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="subnav-wrapper">
   <span id="subnav-title"></span>
   <div id="sub_menu">
       <p><a href="<%=request.getContextPath()%>/review/reviewList">게시판으로 돌아가기</a></p>
       <p><a href="<%=request.getContextPath()%>/review/reviewView?reviewNo=<%=review.getReviewNo()%>">뒤로</a></p>
   </div>
</div>

<div id="content-wrapper">
	<div id="review-container">
		<h2>후기 수정</h2>
		<form action="<%=request.getContextPath()%>/review/reviewUpdateEnd" method="post" >
			<table id="tbl-review-form">
				<tr>
					<input type="hidden" name="reviewNo" value="<%=review.getReviewNo()%>" />
		            <th>제 목</th>
		            <td>
		            	<input type="text" name="title" value="<%=review.getReviewTitle() %>" required/>
		            </td>
		        </tr>
		        <tr>
		            <th>작성자</th>
		            <td>
		            	<input type="text" name="writer" value="<%=review.getPatientId() %>" readonly required/>
		            </td>
		        </tr>
		        <tr>
			        <th>평점</th>
			        <td>
		      			<select name="grade" id="grade">
						<option value="1" <%if(review.getGrade() == 1){ %>selected = "selected"<%} %>>1 점</option>
						<option value="2" <%if(review.getGrade() == 2){ %>selected = "selected"<%} %>>2 점</option>
						<option value="3" <%if(review.getGrade() == 3){ %>selected = "selected"<%} %>>3 점</option>
						<option value="4" <%if(review.getGrade() == 4){ %>selected = "selected"<%} %>>4 점</option>
						<option value="5" <%if(review.getGrade() == 5){ %>selected = "selected"<%} %>>5 점</option>
						</select>
					</td>
		        </tr>
		        <tr>
		            <th>내 용</th>
		            <td>
		            	<textarea name="content" cols="50" rows="5"><%=review.getReviewContent()%></textarea>
		            </td>
		        </tr>
		        <tr>
		        	<th colspan="2">
		        		<input type="submit" value="수 정 하 기" onclick="return validate()"/>
		        	</th>
		        </tr>
			</table>
		</form>
	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>