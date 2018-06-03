<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%@ page import="java.util.*, com.panacea.notice.model.vo.*" %>
    	<%
			List<Notice> list = (List<Notice>)request.getAttribute("list");
    		int cPage = (int)request.getAttribute("cPage");
    	
%>



<div id="subnav-wrapper">
	<span id="subnav-title">공지사항</span>
	<span id="search-review-title">검색</span>
	<div id="sub_menu">
	 <div id="search-container">
		<select name="searchType" id="searchType">
			<option value="admin_Id">아이디</option>
			<option value="notice_Title">제목</option>
		</select>
		
		<div id="search-admin_Id">
			<form action="<%=request.getContextPath()%>/notice/noticeFinder">
				<input type="hidden" name="searchType" value="admin_Id" />
				<input type="search" name="searchKeyword" size="20" class="aa" placeholder="검색할 아이디를 입력하세요" required/>
				<button type="submit">검색</button>
			</form>
		</div>
		
		<div id="search-notice_Title">
			<form action="<%=request.getContextPath()%>/notice/noticeFinder">
				<input type="hidden" name="searchType" value="notice_Title" />
				<input type="search" name="searchKeyword" size="20" class="aa" placeholder="검색할 제목을 입력하세요" required/>
				<button type="submit">검색</button>
			</form>
		</div>
	
	</div>
	</div>
</div>

<style>


</style>

<script>
$(function(){
	
	$("#searchType").change(function(){
		$("#search-admin_Id").hide();	
		$("#search-notice_Title").hide();
		
		//$("#search-"+$(this).val()).show();
		$("#search-"+$(this).val()).css('display','inline-block');
	});	
});

</script>

<div id="content-wrapper">
<div id="notice-container">
	<h2>공지사항</h2>
	
	<%-- 관리자인 경우 공지사항 작성가능 --%>
	<%if(loginAdmin!=null){ %>
	<input type="button" value="글쓰기" id="btn-add" onclick="fn_insertNotice();" />
	<input type="button" value="삭제" id="btn-delete" onclick="fn_deleteNotice();" />
	<script>
	function fn_insertNotice(){
		location.href="<%=request.getContextPath()%>/notice/noticeForm";
	}
	</script>
<%} %>
	<table id="tbl-notice" class="tal">
		<tr>
		<th class="center">
		
         	<%if(loginAdmin != null){ %>
			<input type="checkbox" name="checkAll" id="th_checkAll" onclick="checkAll();"/>
			<%} else { %>
         	번호
         	<%} %>
		</th>
		
			<th class="title">제목</th>
			<th class="writer">작성자</th>
			<th class="file">첨부파일</th>
			<th class="date">작성일</th>
		</tr>
		
		<% for(int i=0;i<list.size(); i++){ %>
		
		
	
        <tr class="aaa" >
         <td class="center">
         	<%if(loginAdmin != null){ %>
         	<input type="checkbox" name="checkRow" value="<%= list.get(i).getNoticeNo() %>" />
         	<%} else { %>
         	<%=(i+1)+((cPage-1)*10) %>
         	<%} %>
         	</td>
            <td class="title" >
                <a href="<%=request.getContextPath() %>/notice/noticeView?no=<%=  list.get(i).getNoticeNo() %>" >
                    <%=  list.get(i).getNoticeTitle() %>
                </a>
            </td>
            <script>
		if(<%=list.get(i).getEmphasize() %> >0 && $(".aaa").length <4) {
			var a=$(".aaa");
		
		
		$(".aaa").css({'background':'#def2ff','color':'navy'});

			}
		</script>
            <td><%=  list.get(i).getAdminId() %></td>
            <td>
            <% if( list.get(i).getNoticeFile() != null){ %>
            <img alt="첨부파일" class="imgs" src="<%=request.getContextPath() %>/img/file.png">
            <% }%>
            </td>
            <td class="date"><%=  list.get(i).getNoticeDate() %></td>
        </tr>
        <% } %>
   
	</table>
	   	
</div>
<div class="pagination"><%=request.getAttribute("pageBar")%></div>
</div>   


 <script>

   
		
	
 
 
 
function checkAll(){
    if( $("#th_checkAll").is(':checked') ){
      $("input[name=checkRow]").prop("checked", true);
    }else{
      $("input[name=checkRow]").prop("checked", false);
    }
}

function fn_deleteNotice(){
var test =new Array();

$("input[name=checkRow]:checked").each(function(){


	test += $(this).val()+",";

	

});
alert(test);


location.href="<%=request.getContextPath()%>/notice/noticeDelete?no="+test;

	//1. 배열에 넣어서 한방에 서블릿으로 보낸다
	
	//2. 서블릿에서 배열을 받아서 
	//3. 그 배열의 인덱스만큼 포문을 돌려서 삭제한다.


}

</script>   

    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>