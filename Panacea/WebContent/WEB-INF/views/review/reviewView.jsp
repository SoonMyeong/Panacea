<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.panacea.review.model.vo.*, java.util.*" %>
<%
	Review review = (Review)request.getAttribute("Review");
	ArrayList<ReviewComment> list = (ArrayList<ReviewComment>)request.getAttribute("commentlist");
	ArrayList<ReviewComment> mostLikeCommentList = (ArrayList<ReviewComment>)request.getAttribute("mostLikeCommentList");
	int cPage = (int)request.getAttribute("cPage");
%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="subnav-wrapper">
   <span id="subnav-title"></span>
   <div id="sub_menu">
       <p><a href="<%=request.getContextPath()%>/review/reviewList">게시판으로 돌아가기</a></p>
		<% if(loginPatient != null && loginPatient.getPatientId().equals(review.getPatientId())){ %>
		<p><a href="<%=request.getContextPath()%>/review/reviewUpdate?no=<%=review.getReviewNo()%>">수정하기</a></p>
		<p><a href="<%=request.getContextPath()%>/review/reviewDelete?no=<%=review.getReviewNo()%>" class = "btn-delete">삭제하기</a></p>
		<%} else if(loginAdmin != null){%>
		<p><a href="<%=request.getContextPath()%>/review/reviewUpdate?no=<%=review.getReviewNo()%>">수정하기</a></p>
		<p><a href="<%=request.getContextPath()%>/review/reviewDelete?no=<%=review.getReviewNo()%>" class = "btn-delete">삭제하기</a></p>
		<%} else if(loginPatient == null && loginAdmin == null && loginDoctor == null){%>
		<p><a href="<%=request.getContextPath()%>/common/login" id = "login">로그인</a></p>	
		<% } %>
   </div>
</div>

<div id="content-wrapper">
	<div id="review-container">
		<h2>후기 상세보기</h2>
		<table id="tbl-review-one">
			<tr>
				<td>제목</td>
				<td><%=review.getReviewTitle() %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%=review.getPatientId() %></td>
			</tr>
			<tr>
				<td>평점</td>
					<td>
					<input type="hidden" name="grade" value="<%=review.getGrade()%>" />
					<%for(int i=0; i < review.getGrade(); i++){ %>
						<img src="<%=request.getContextPath() %>/img/review/fillstar.png" alt="" class = "star"/>
					<%} for(int i=review.getGrade()+1; i<6; i++){ %>
					    <img src="<%=request.getContextPath() %>/img/review/emptystar.png" alt="" class = "star"/>
					<%} %>
					</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=review.getReviewDate() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
				<%=review.getReviewContent() %>
				</td>
			</tr>
		</table>


<!-- ******** 작성자 Admin or Patient 에따라 분기  ***********  -->
 	<% String reviewCommentWriter = "";
 	if(loginPatient != null && loginDoctor == null){ 
		 reviewCommentWriter = loginPatient.getPatientId(); 
	}else if(loginAdmin != null && loginDoctor == null){
	 	 reviewCommentWriter =  loginAdmin.getAdminId();
	}else{} %>
	
	<div id="comment-contariner">
	<p id="comment-title">댓글쓰기</p>
	<div class="comment-editer">
		<form name="reviewCommentFrm"  action="<%=request.getContextPath()%>/review/reviewCommentInsert" method = "post">
			<textarea name="reviewCommentContent" cols="60" rows="3"></textarea>
			<button type="submit" id="btn-insert">OK</button>
			<input type="hidden" name="reviewCommentWriter" value="<%=reviewCommentWriter %>  " />
			<input type="hidden" name="reviewRef" value="<%=review.getReviewNo()%>" />
			<input type="hidden" name="reviewCommentRef" value="0" /> 
			<input type="hidden" name="reviewCommentLevel" value = "1" />
			<input type="hidden" name="cPage" value = "<%=cPage %>" />
			
		</form>	
	</div>
	<%if(mostLikeCommentList != null){ %>
	<table id="tbl-mostLikeComment"> 
	<p id="comment-title">Most Like Comment</p>
	<%for(ReviewComment rc : mostLikeCommentList){  
		   if(rc.getCommentLevel() == 1){ %>	 	
			<!--level1  -->	
				<tr class="level1">
					<td>
						<sub class = "comment-writer"><%=rc.getCommentWriter() %></sub>
						<sub class="comment-date"><%=rc.getCommentDate() %></sub>
						<br />
						<%= rc.getCommentContent() %>
					</td>
					<td><button class = "btn-likeIt" value = "<%=rc.getCommentNo()%>">LikeIt</button><%=rc.getLikeIt()%></td>
					<td><button class = "btn-Bad" value = "<%=rc.getCommentNo()%>">Bad</button><%=rc.getBad()%></td>
					<td colspan = "3">
						<button class="btn-reply" value = "<%=rc.getCommentNo() %>">답글</button>
						<!--삭제버튼 추가  -->
						<%if(loginPatient != null && loginPatient.getPatientId().equals(rc.getCommentWriter().trim())){ %>
							<button class="btn-delete" value = "<%=rc.getCommentNo()%>">삭제</button>
							<input type="hidden" class="delLevel" value = "<%=rc.getCommentLevel() %>">
						<%}else if(loginAdmin != null ){ %>
							<button class="btn-delete" value = "<%=rc.getCommentNo()%>">삭제</button>
							<input type="hidden" class="delLevel" value = "<%=rc.getCommentLevel() %>">
						<%}else{} %>
					</td>
				</tr>
		<% }
		}  /* end of for */ 
	}else if(mostLikeCommentList.size() == 0){ %>
   	<tr>
   	<td colspan = "4">댓글이 없습니다</td>
   	</tr>
   <% }%> <!--end of nullIf  -->
   </table>
	<br />
	
	<!--댓글 목록 테이블 -->
	<%if(list != null){ %>
	<table id="tbl-comment">
	<p id="comment-title">Comment</p>
	<%for(ReviewComment rc : list){  
		   if(rc.getCommentLevel()==1){ %>
		   	<!--level1  -->	
				<tr class="level1">
						<td>
						<sub class = "comment-writer"><%=rc.getCommentWriter() %></sub>
						<sub class="comment-date"><%=rc.getCommentDate() %></sub>
						<br />
						<%= rc.getCommentContent() %>
					</td>
					<td><button class = "btn-likeIt" value = "<%=rc.getCommentNo()%>">LikeIt</button><%=rc.getLikeIt()%></td>
					<td><button class = "btn-Bad" value = "<%=rc.getCommentNo()%>">Bad</button><%=rc.getBad()%></td>
					<td colspan = "3">
						<button class="btn-reply" value = "<%=rc.getCommentNo() %>">답글</button>
						<!--삭제버튼 추가  -->
						<%if((loginPatient != null) && (rc.getCommentWriter().trim().equals(loginPatient.getPatientId()))){ %>
							<button class="btn-delete" value = "<%=rc.getCommentNo()%>">삭제</button>
							<input type="hidden" class="delLevel" value = "<%=rc.getCommentLevel() %>"  />
						<%}else if(loginAdmin != null ){ %>
							<button class="btn-delete" value = "<%=rc.getCommentNo()%>">삭제</button>
							<input type="hidden" class="delLevel" value = "<%=rc.getCommentLevel() %>"  />
						<%} %>
					</td>
				</tr>
		<% } else { %>
			<!--level2  -->
				<tr class="level2">
					<td>
						<sub class = "comment-writer"><%=rc.getCommentWriter() %></sub>
						<sub class="comment-date"><%=rc.getCommentDate() %></sub>
						<br />
						<%= rc.getCommentContent() %>
					</td>
					<td colspan = "3">
						<!--삭제버튼 추가  -->
						<%if(loginPatient != null && loginPatient.getPatientId().equals(rc.getCommentWriter())){ %>
							<button class="btn-delete" value = "<%=rc.getCommentNo()%>">삭제</button>
							<input type="hidden" class="delLevel" value = "<%=rc.getCommentLevel() %>"  />
							<% System.out.println("commentLevel = "+ rc.getCommentLevel()); %>
						<%}else if(loginAdmin != null ){ %>
							<button class="btn-delete" value = "<%=rc.getCommentNo()%>">삭제</button>
							<input type="hidden" class="delLevel" value = "<%=rc.getCommentLevel() %>"  />
						<%}else{} %>
				   </td>
				</tr>
		
		<%} //end of If
	} //end of for %>
	</table>
	<% }%>
</div>
		
		<div class="pagination">
			<%=request.getAttribute("pageBar") %>
		</div>
	
	
	</div>
   
</div>
<script>


/************ 답글 작성 삭제 관련 스크립트 **************/
/* 로그인 여부 체크 */
$(function(){
	$("[name=reviewCommentContent]").focus(function(){
		if(<%= loginPatient==null && loginAdmin == null%>)
			fn_loginAlert();
	});
	//boardCommentFrm폼 유효성검사
	$("[name=reviewCommentFrm]").submit(function(e){
		if(<%= loginPatient==null && loginAdmin == null%>){
			fn_loginAlert();
			e.preventDefault();  //로그인안했을시 이벤트 발생 막음
			return;
		}
		var len = $("[name=reviewCommentContent]").val().trim().length;
		if(len==0){
			alert("댓글을 입력하세요");
			e.preventDefault(); //내용안적으면 이벤트 발생 막음
			$("[name=reviewCommentContent]").focus();
		}
		
	});
	
	//답글버튼 클릭시 이벤트 처리
	$(".btn-reply").on('click',function(){
		<% if(loginPatient != null || loginAdmin != null){%>
		//로그인한 경우
		var tr = $("<tr></tr>");
		var html = '<td style="display:none; text-align : left;" colspan="4">';
		html += '<form action="<%=request.getContextPath()%>/review/reviewCommentInsert" method = "post">';
		html += '<textarea name="reviewCommentContent" cols="60" rows="3"></textarea>';
		html += '<button type="submit" id="btn-insert">OK</button>';
		html += '<input type="hidden" name="reviewCommentWriter" value="<%=reviewCommentWriter%>" />';
		html += '<input type="hidden" name="reviewRef" value="<%=review.getReviewNo()%>" />';
		html += '<input type="hidden" name="reviewCommentRef" value="'+$(this).val()+'" />';
		html += '<input type="hidden" name="reviewCommentLevel" value = "2" />'
		html += '<input type="hidden" name="cPage" value = "<%=cPage%>" />'
		html += '</form></td>';
		tr.html(html);
		//생성된 노드를 페이지에 추가
		tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
		//한번클릭후에는 이벤트핸들러
		$(this).off("click");
		
		//이벤트핸들러 추가
		tr.find("form").submit(function(e){
			if(<%= loginPatient==null && loginAdmin == null%>){
				fn_loginAlert();
				e.preventDefault();  //로그인안했을시 이벤트 발생 막음
				return;
			}
			var len = $(this).children("textarea").val().trim().length;
			if(len==0){
				alert("댓글을 입력하세요");
				e.preventDefault(); //내용안적으면 이벤트 발생 막음
				$("[name=reviewCommentContent]").focus();
			}
			
		});
		//포커스
		tr.find("textarea").focus();

		<% } else { %>
		//로그인 안한 경우
		fn_loginAlert();
		<%}%>
		
	});
	
	//삭제버튼 클릭시 
	$(".btn-delete").on("click",function(){
		var delLevel = $(this).next().val().trim();
		console.log(delLevel);
		if(!confirm("정말 삭제하시겠습니까?"))return false;
		//삭제처리후 돌아올 현재 게시판 번호도 전송함
	   if(delLevel == 1){
	    location.href = "<%=request.getContextPath()%>/review/reviewCommentDelete?commentNo="+$(this).val()+"&reviewNo="+<%=review.getReviewNo()%>+"&cPage="+<%=cPage%>;
	   }else{
		location.href = "<%=request.getContextPath()%>/review/reviewRefCommentDelete?commentNo="+$(this).val()+"&reviewNo="+<%=review.getReviewNo()%>+"&cPage="+<%=cPage%>;   
	   }
      
   })
   //LikeIt 버튼 클릭시
   $(".btn-likeIt").on("click",function(){
	   <%if(loginPatient == null && loginAdmin == null && loginDoctor == null ){%>
	   alert("로그인 후 이용하실수 있습니다.");
	   return false;
	   <%}else{%>
	   location.href="<%=request.getContextPath()%>/review/likeit?commentNo="+$(this).val()+"&reviewNo="+<%=review.getReviewNo()%>+"&cPage="+<%=cPage%>; 
	   <%}%>
   })
   //Bad버튼 클릭시
   $(".btn-Bad").on("click",function(){
	   <%if(loginPatient == null && loginAdmin == null && loginDoctor == null ){%>
	   alert("로그인 후 이용하실수 있습니다.");
	   return false;
	   <%}else{%>
	   location.href="<%=request.getContextPath()%>/review/Bad?commentNo="+$(this).val()+"&reviewNo="+<%=review.getReviewNo()%>+"&cPage="+<%=cPage%>;
  	   <%}%>
    })
	
	
});
function fn_loginAlert(){
	alert("로그인후 이용하실 수 있습니다");
	$("[name = reviewCommentContent]").blur();
	return false;
	
}

</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>