<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="index-img-wrapper">
	<img class="mySlides" src="<%=request.getContextPath() %>/img/index/h1.jpg" alt="panacea">
    <img class="mySlides" src="<%=request.getContextPath() %>/img/index/h2.jpg" alt="panacea">
    <img class="mySlides" src="<%=request.getContextPath() %>/img/index/h3.jpg" alt="panacea">
    <img class="mySlides" src="<%=request.getContextPath() %>/img/index/h4.jpg" alt="panacea">
    <img class="mySlides" src="<%=request.getContextPath() %>/img/index/h5.jpg" alt="panacea">
    <img class="mySlides" src="<%=request.getContextPath() %>/img/index/h6.jpg" alt="panacea">
    <a class="w3-btn-floating w3-btn"  onclick="plusDivs(-1)"><</a>
    <a class="w3-btn-floating-r w3-btn" onclick="plusDivs(1)">></a>
</div>

<script>
	var slideIndex = 1;
	showDivs(slideIndex);
	
	
	function plusDivs(n) {
	    showDivs(slideIndex += n);
	}
	
	function showDivs(n) {
	    var i;
	    var x = document.getElementsByClassName("mySlides");
	    if (n > x.length) {slideIndex = 1;} 
	    if (n < 1) {slideIndex = x.length;} 
	    for (i = 0; i < x.length; i++) {
	        x[i].style.display = "none"; 
	    }
	   x[slideIndex-1].style.display = "block";
	}
</script>

<script>
	var myIndex = 0;
	carousel();
	
	function carousel() {
	    var i;
	    var x = document.getElementsByClassName("mySlides");
	    for (i = 0; i < x.length; i++) {
	       x[i].style.display = "none";  
	    }
	    myIndex++;
	    if (myIndex > x.length) {myIndex = 1}    
	    x[myIndex-1].style.display = "block";  
	    setTimeout(carousel, 3000); // Change image every 2 seconds
	}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
