<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
      <%@ page import="java.util.*, com.panacea.notice.model.vo.*" %>
    <%
	Notice n = (Notice)request.getAttribute("notice");
%>

<div id="notice-container">
	<h2>공지사항</h2>
	<table id="tbl-notice-one">
		 <tr >
            <th class="sibal">제 목</th>
            <td><%=n.getNoticeTitle() %></td>
        </tr>
        <tr>
            <th class="sibal">작성자</th>
            <td><%=n.getAdminId()%></td>
        </tr>
        <tr>
            <th class="sibal">첨부파일</th>
            <td>
                 <% if(n.getNoticeFile() != null){ %>
            <a href="javascript:fn_fileDownload('<%=n.getNoticeFile()%>')">
	            <img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
	            <%=n.getNoticeFile() %>
            </a>
            <% }%>
            </td>
        </tr>
        <%if(loginAdmin!=null){ %>
        <tr>
            <th class="sibal">수정</th>
            <td><input type="button" value="수 정 하 기" onclick="fn_updateNotice();"/>
            </td>
        </tr>
       <%} %>
        <tr>
        	<th colspan="2" class="content">내   용</th>
        </tr>
        <tr>
        	<th colspan="2" class="content">
        	<div>
        	<%=n.getNoticeContent()%>
        	</div>
        	</th>
        </tr>
    
        
	</table>
</div>
<script>


function fn_fileDownload(filePath){
	//ie는 자동으로 인코딩처리 하지 않음.
	filePath=encodeURIComponent(filePath);
	location.href="<%=request.getContextPath()%>/notice/noticeFileDownload?fname="+filePath;
	
}

function fn_updateNotice(){
	location.href="<%=request.getContextPath()%>/notice/noticeUpdate?no=<%=n.getNoticeNo()%>";
}
</script>
    
    
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>