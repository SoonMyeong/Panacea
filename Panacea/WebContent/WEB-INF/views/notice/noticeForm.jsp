<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%@ page import="java.util.*, com.panacea.notice.model.vo.*" %>
    
    
    
    <%
	Notice n = (Notice)request.getAttribute("notice");
%>
<script>
function validate(){
	
	if($("[name=title]").val().trim().length == 0){
		alert("제목을 입력하세요.");
		$("[name=title]").focus();
		return false;
	}
	
	if($("[name=title]").val().length >= 30){
		alert("제목은 30글자 까지 작성 가능합니다.");
		$("[name=title]").focus();
		return false;
	}
	
	var content = $("[name=content]").val();
	if(content.trim().length==0){
		alert("내용을 입력하세요.");
		return false;
	}

	return true;
}
</script>

<div id="notice-container">
	<h2>공지사항 작성</h2>
	<form action="<%=request.getContextPath()%>/notice/noticeFormEnd" method="post" enctype="multipart/form-data">
		<table id="tbl-notice-form">
			 <tr>
	            <th>제 목</th>
	            <td>
	            	<input type="text" name="title" required/>
	            </td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td>
	            	<input type="text" name="writer" value="<%=loginAdmin.getAdminId() %>" readonly required/>
	            </td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td id="file">
	            	<input type="file" name="up_file" />
	            </td>
	        </tr>
	        <tr>
	        <th>강조</th>
	        <td>강조하기<input type="radio" name="emphasize" onclick="fn_emphasize();"/>
	         기본<input type="radio" name="emphasize" onclick="fn_emphasize();"  checked="checked"/></td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td>
	            	<textarea name="content" cols="50" rows="5"></textarea>
	            </td>
	        </tr>
	        <tr>
	        	<th colspan="2">
	        		<input type="submit" value="등 록 하 기" onclick="return validate()"/>
	        	</th>
	        </tr>
		</table>
	</form>
</div>

<script>

function fn_emphasize(){
   if ($("input:radio[name='emphasize']").is(":checked") == true){
      $("input[name=emphasize]").val('1');
      }else{
         $("input[name=emphasize]").val('2');
      }
   } 

</script>
    
    
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>