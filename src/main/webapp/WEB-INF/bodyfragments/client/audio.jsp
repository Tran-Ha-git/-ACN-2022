<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="container">
	<div class="audio">
		<div class="audio-info">
			<h2 class="product-title">${book.name}</h2>
			<p class="info-item">
				<span class="info-label">Tác giả</span> 
				<a href="#"><span
					class="info-item">${book.listAuthor}</span></a>
			</p>
		</div>
		<div class="audio-detail">
			<h4 class="detail-title">Giới thiệu sách</h4>
			<p class="detail-description">${book.description}</p>

			<div class="audio-player">
				<div class="audio-img">

					<div class="audio-bg">
						<div class="title-chapter">
							<span class="audio-item-no">${audio1st.priority}. </span>
							<span class="audio-item-name">${audio1st.name} </span> 							
						</div>
						<img src="${book.thumbnail}" alt="">
					</div>

				</div>
				<audio controls controlsList="nodownload" id="player">
					<source src="${audio1st.url}" type="audio/mp3" id="mp3_src">
					<source src="${audio1st.url}" type="audio/ogg" id="ogg_src">
					
					<!-- <source src="./../assets/audios/Eternal Hope - Kevin MacLeod.ogg" type="audio/ogg">
					<source src="./../assets/audios/Eternal Hope - Kevin MacLeod.mp3" type="audio/mpeg"> -->
					Your browser does not support the audio element.
				</audio>

			</div>


			<div class="relative">
				<div class="social-media">
					<a href="#" class="btn btn-primary facebook " role="button"	data-bs-toggle="button">
						 <i class="fa-brands fa-facebook-f"></i>
					</a>
					 <a href="#" class="btn btn-info text-white twitter " role="button" data-bs-toggle="button">
						<i class="fa-brands fa-twitter"></i>
					</a> 
					<a href="#" class="btn btn-danger pinterest " role="button" data-bs-toggle="button"> 
						<i class="fa-brands fa-pinterest"></i>
					</a> 
					<a href="#" class="btn btn-primary linkedin " role="button" data-bs-toggle="button"> 
						<i class="fa-brands fa-linkedin"></i>
					</a>
				</div>
				<div class="reading-format">
					<a href="#" class="btn btn-danger read-pdf" role="button" data-bs-toggle="button">Đọc PDF</a> 
					<a href="#" class="btn btn-primary read-online" role="button" data-bs-toggle="button">Đọc online</a>
				</div>
			</div>

			<ul class="audio-list">
				<li class="audio-item js-play active" >
					<a	class="audio-link"> 
						<span class="audio-item-no">${audio1st.priority}.</span> 
						<span class="audio-item-name">${audio1st.name} </span> 
					</a>
					 <input type="hidden" class="url-audio-item" value="${audio1st.url}">
					
				</li>
				<c:forEach items="${audios}" var="audio" >
					<li class="audio-item js-play " >
						<a	 class="audio-link">
						 <span class="audio-item-no">${audio.priority}. </span> 
						 <span class="audio-item-name">${audio.name} </span> 
						</a>
						<input type="hidden" class="url-audio-item" value="${audio.url}">
						
					</li>
				
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("li.audio-item").click(function(){
	  $("li.audio-item").removeClass("active");
	  $(this).addClass("active");
	  let priority= $(this).children("a.audio-link ").children("span.audio-item-no").html();
	  let name= $(this).children("a.audio-link ").children("span.audio-item-name").html();
	  let url= $(this).children(".url-audio-item").val()
	  
	  $(".audio-player .audio-img .audio-bg .title-chapter .audio-item-no").text(priority);
	  $(".audio-player .audio-img .audio-bg .title-chapter .audio-item-name").text(name);
	  
	  change(url);
	  
  });
});
function change(sourceUrl) {
    var audio = $("#player"); 
    $("#mp3_src").attr("src", sourceUrl);
    $("#ogg_src").attr("src", sourceUrl);
    /****************/
    audio[0].pause();
    audio[0].load();//suspends and restores all audio element

    //audio[0].play(); changed based on Sprachprofi's comment below
    audio[0].oncanplaythrough = audio[0].play();
    /****************/
}
</script>