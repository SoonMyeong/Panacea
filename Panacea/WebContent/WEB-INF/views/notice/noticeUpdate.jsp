<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="com.panacea.notice.model.vo.*" %>
<%
   Notice n = (Notice)request.getAttribute("notice");
%>
<style>
span#fname{
   position:absolute;
   left:86px;
   top:10px;
   width:285px;
   background:#f5f5f5;
}
</style>
<script>
$(function(){
   $("[name=up_file]").change(function(){
      //$(this).val()은 선택한 파일명임.
      if($(this).val()==""){
         $("#fname").show();
      }   
      else{
         $("#fname").hide();
      }
   });   
});

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
   console.log($("#delete").val());
   return true;
}
</script>
<div id="notice-container">
   <h2>공지사항 수정</h2>
   <form action="<%=request.getContextPath()%>/notice/noticeUpdateEnd" method="post" enctype="multipart/form-data">
      <table id="tbl-notice-form">
         <tr>
            <th>글번호</th>
            <td>
               <input type="text" name="no" value="<%=n.getNoticeNo()%>" readonly required/>
            </td>
         </tr>
         <tr>
               <th>제 목</th>
               <td>
                  <input type="text" name="title" value="<%=n.getNoticeTitle() %>" required/>
               </td>
           </tr>
           <tr>
               <th>작성자</th>
               <td>
               <input type="text" name="writer" value="<%=n.getAdminId() %>" readonly required/>
               </td>
                  
           </tr>
           <tr>
               <th>첨부파일</th>
               
              
               <td id="file" style="position:relative;">
                  <%if(n.getNoticeFile() != null){ %>
                  
                     <!-- 파일태그에 value속성은 임의로 변경할 수 없음. -->
                     <input type="file" name="up_file"/>
                     <span id="fname"><%=n.getNoticeFile() %></span>
                     <!-- 파일변경대비 기존파일이름 필드 -->
                     <input type="hidden" name="old_file" value="<%=n.getNoticeFile()%>"/>
                  <%} else { %>               
                     <input type="file" name="up_file"/>
                  <%} %>
               </td>
           </tr>
           
           <tr>
              <th>파일 여부</th>
              
              <td>파일 삭제<input type="radio" id="delete" name="filedelete"  onclick="fn_delete();" />
                 파일 유지<input type="radio" id="delete" name="filedelete"  onclick="fn_delete();" checked="checked" />
              </td>
             
           </tr>
           <tr>
               <th>내 용</th>
               <td>
                  <textarea name="content" cols="50" rows="5"><%=n.getNoticeContent() %></textarea>
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
   <script>
   

   
function fn_delete(){
   if ($("input:checkbox[name='filedelete']").is(":checked") == true){
      $("input[name=filedelete]").val('1');
      }else{
         $("input[name=filedelete]").val('2');
      }
   } 


function fn_emphasize(){
	   if ($("input:radio[name='emphasize']").is(":checked") == true){
	      $("input[name=emphasize]").val('1');
	      }else{
	         $("input[name=emphasize]").val('2');
	      }
	   } 
   
   
      
      </script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>






