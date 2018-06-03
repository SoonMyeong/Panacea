<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import = "com.panacea.review.model.vo.*, java.util.*, com.panacea.review.model.exception.*" %>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%

ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
String srchedKeyword = (String)request.getAttribute("srchKeyword");

System.out.println("list.isEmpty@reviewList.jsp = "+ list.isEmpty());
%>

<script>
function srchValidate(){
	var id;
	$("[name=srchType]").each(function(){
		if($(this).is(":checked")){
			id = $(this).attr("id"); 
		}
	});
		
	if(id == "title" || id =="Id"){
		if($("#srchKeyword").val().trim().length == 0){
			alert("검색어를 입력하세요");
			$("#srchKeyword").focus();
			return false;
		}
	}
	
	return true;
}

$(function(){

	
	$("[name=srchType]").change(function(){
		console.log($(this));
		var id = $(this).attr("id");
		console.log(id);
		if(id=="title"){
			console.log(id);
			$("#srchKeyword").attr("placeholder","검색할 제목 입력").focus();
			$("#srchKey").show();
			$("#srchKey").children().show();
			$("#srchDate").css({"display" : "none"});
		}
		else if (id == "Id"){
			$("#srchKeyword").attr("placeholder","검색할 아이디 입력").focus();
			$("#srchKey").show();
			$("#srchKey").children().show();
			$("#srchDate").css({"display" : "none"});
		}else if(id == "Date"){
			$("#srchKey").css({"display" : "none"});
			$("#srchDate").css({"display" : "initial", "font" : "italic", "font-weight" : "1px" });
			
		}
	});
	
	
});

function fn_delChk(){
	if($(this).is(':checked')){
		if(!confirm("정말로 데이터를 삭제하시겠습니까?")){
			return false;
		}
		return true;	
	}else{
		console.log("ff");
		$(".checkAll").prop("checked",false);
		return false;
	}
	
}
function checkAll(){
	if($(".checkAll").is(':checked')){
		if(!confirm("정말로 모든 게시판의 데이터를 삭제하시겠습니까")){
			return false;
		}
		$(".delChk").prop("checked",true);
		return true;
	}else{
		$(".delChk").prop("checked",false);
	}
}

function fn_deleteByChk(){
	var reviewNo = new Array();
	$("input[name = delChk]:checked").each(function(){
		reviewNo += $(this).val()+",";
		
	});
	if(reviewNo.length ==0){
		alert("선택된 항목 없음");
		return false;
	}
	console.log(reviewNo);
	location.href="<%=request.getContextPath()%>/review/deleteByChk?reviewNo="+reviewNo;
}

$(function(){
	  $("#goToWrite").click(function(){
		<% if(loginPatient == null && loginAdmin == null && loginDoctor == null){ %>
			alert("로그인 후 이용가능합니다.");
			return false;
		<% } else if(loginPatient == null && loginAdmin != null && loginDoctor == null){ %>
			alert("환자만 작성 가능합니다.");
			return false;
		<% } else if(loginPatient == null && loginAdmin == null && loginDoctor !=null){ %>
			alert("환자만 작성 가능합니다.");
			return false;
	    <%} else if(loginPatient !=null && loginAdmin==null && loginDoctor == null){ %>
	    	location.href = "<%=request.getContextPath()%>/review/reviewForm";
	     	return true;
			
		<%} %>
	    });
	});
</script>



<div id="subnav-wrapper">
   <span id="subnav-title"></span>
   <div id="sub_menu">
       <p><a href="<%=request.getContextPath()%>/review/reviewList">전체보기</a></p>
       <p id="goToWrite"><a>글쓰기</a></p>
       <section id="srch-container">
			<form action="<%=request.getContextPath()%>/review/selectReview" name = "srchFrm">
				<span id="search-review-title">검색</span>
				<input type="radio" name="srchType" id="title"  class = "srchType" value = "title"  checked />
				<label for="title" class = "srchType">제목</label>
				<input type="radio" name="srchType" id="Id" class = "srchType" value = "Id"  />
				<label for="Id" class = "srchType">작성자</label>
				<input type="radio" name="srchType" id="Date" class = "srchType" value = "Date" />
				<label for="Date" class = "srchType">월별</label>
				<br />
				<div id="srchKey" >
				<br />
				<input type="search" name="srchKeyword" id="srchKeyword" placeholder ="<%= srchedKeyword %>"/>
				<br />
				</div>
				<div id="srchDate"  style = "display : none" >
				<br />
				<input type="radio" name="srchMon" value = "1" checked/>1 개월 <br />
				<input type="radio" name="srchMon" value = "3"/>3 개월 <br />
				<input type="radio" name="srchMon" value = "6"/>6 개월 <br />
				
 				</div>
				<br />
				<input type="submit" value="검색" onclick = "return srchValidate()" />
			</form>
		</section>
   </div>
</div>

<div id="content-wrapper">
   <div id="container-reviewList">
  <h2>후기 게시판 </h2>
  
  	<%if(loginAdmin != null && loginPatient == null && loginDoctor == null){ %>
  		<input type="button" id="del-btn" value = "삭제"  onclick = "fn_deleteByChk()"/>
  	<% } %>
  	<table id="tbl-review">
  		<tr>
  			<%if(loginAdmin != null && loginPatient == null && loginDoctor == null){ %>
  			<th><input type="checkbox" name="checkAll" class="checkAll" id="checkAll" onclick = "checkAll()" /></th>
  			<%} %>
  			<th>후기제목</th>
  			<th>작성자</th>
  			<th>평점</th>
  			<th>조회수</th>
  			<th>후기날짜</th>
  		</tr>
  		<%if(list != null && !list.isEmpty()) {%>
  			<% for (Review r : list){ %>
  				<tr>
  					<%if(loginAdmin != null && loginPatient == null && loginDoctor == null){ %>
  					<th><input type="checkbox" name="delChk" id="delChk" value = <%=r.getReviewNo() %> onclick = "fn_delChk();" /></th>
  					<%} %>
  					<td>
  					<a href="<%=request.getContextPath() %>/review/reviewView?reviewNo=<%=r.getReviewNo()%>">
  					<%=r.getReviewTitle() %>
  					</a>
  					</td>		
  					<td><%=r.getPatientId() %></td>
  					<td>
					<%for(int i=0; i < r.getGrade(); i++){ %>
						<img src="<%=request.getContextPath() %>/img/review/fillstar.png" alt="" class = "star"/>
					<%} for(int i=r.getGrade()+1; i<6; i++){ %>
					    <img src="<%=request.getContextPath() %>/img/review/emptystar.png" alt="" class = "star"/>
					<%} %>
					</td>
  					<td><%=r.getHits() %></td>
  					<td><%=r.getReviewDate() %></td>
  				</tr>	
  			<% } %>
  		<% }else if(list != null && list.isEmpty()) { %>
  		<tr>
  			<td class="none" colspan = "6">검색된 게시물이 없습니다</td>
  		</tr>
  		<%} %>
   	</table>
   	</div>
   	<div class="pagination">
	<%=request.getAttribute("pageBar") %>
	</div>
</div>

<script>
function fn_reviewWriter(){
	location.href = "<%=request.getContextPath()%>/review/reviewForm";
	
}


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>