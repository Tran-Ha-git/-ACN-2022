<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="container">
	<div class="audio">
		<div class="audio-info">
			<h2 class="product-title">${bookDTO.name}</h2>
			<p class="info-item">
				<span class="info-label">Tác giả</span> 
				<c:forEach items="${bookDTO.authors}" var="author" varStatus="loop">						
					<a href="/books?author=${author.slug}">
						<span class="info-item">${author.fullname}
						</span>
						<c:if test="${loop.index != bookDTO.authors.size() - 1}">
							<span class="mr-3">,</span>
					    </c:if>
					</a>
				</c:forEach>
			</p>
		</div>
		<div class="audio-detail">
			<h4 class="detail-title">Giới thiệu sách</h4>
			<p class="detail-description">${bookDTO.description }</p>

			<div class="audio-player">
				<div class="audio-img">

					<div class="audio-bg">
						<div class="title-chapter">
							<span class="audio-item-no" id="no-item-playing">${audioSelected.priority}. </span>
							<span class="audio-item-name">${audioSelected.name} </span> 
						</div>
						<img src="${bookDTO.thumbnail}" alt="${bookDTO.name}">
					</div>

				</div>
				<audio controls controlsList="nodownload" id="player">
					<source src="${audioSelected.url}" id="mp3_src" type="audio/ogg">
					<source src="${audioSelected.url}" id="ogg_src"	type="audio/mpeg">
					Your browser does not support the audio element.
				</audio>

			</div>

			<div class="relative">
				<div class="social-media">
					<a href="#" class="btn btn-primary facebook" role="button" data-bs-toggle="button"> <i class="fa-brands fa-facebook-f"></i></a> 
					<a href="#" class="btn btn-info text-white twitter" role="button" data-bs-toggle="button"> <i class="fa-brands fa-twitter"></i></a>
					<a href="#" class="btn btn-danger pinterest" role="button" data-bs-toggle="button"> <i class="fa-brands fa-pinterest"></i></a> 
					<a href="#" class="btn btn-primary linkedin" role="button" data-bs-toggle="button"> <i class="fa-brands fa-linkedin"></i></a>
				</div>
				<div class="bookmark-area">
					<input type="hidden" value="${bookDTO.slug}" id="book-slug">
					<input type="hidden" value="${audioSelected.id}" id="audio-id">
					
					<div class = "box-mark show-mark">
						<div class="book-mark-button"><i class="fa-regular fa-bookmark"></i></div>
						<span id="title-mark">Đánh dấu</span>
					</div>
					<div class = "box-unmark">
						<div class="book-mark-button"><i class="fa-solid fa-bookmark"></i></div>
						<span id="title-unmark">Bỏ đánh dấu</span>
					</div>
				</div>
				<div class="reading-format">
					<c:if test="${existsEbook}">
						<a href="/doc-online/${bookDTO.slug}?chapter=1" class="btn btn-primary read-online">Đọc online</a>
					</c:if>
					<c:if test="${existsPdf}">
						<a href="/pdf-read/${bookDTO.slug}" class="btn btn-danger read-pdf">Đọc PDF</a> 
					</c:if>
					<a href="/books/${bookDTO.slug}" class="btn btn-outline-primary read-online">Xem sách</a>
				</div>
			</div>

			<ul class="audio-list">
				<li class="audio-item js-play active" id="audio-id-${audioSelected.id}">
					<a	class="audio-link"> 
						<span class="audio-item-no">${audioSelected.priority}.</span> 
						<span class="audio-item-name">${audioSelected.name} </span> 
					</a>
					<input type="hidden" class="url-audio-item" value="${audioSelected.url}">
				</li>
				<c:forEach items="${audioDTOs}" var="audio" >
				<li class="audio-item js-play" id="audio-id-${audio.id}">
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
	  
	  let currentId = $(this).attr('id');
	  let audioId = currentId.replace("audio-id-", "");
	  $("#audio-id").val(audioId);
	  
	  change(url);
	  showBookmark();
  });
  
  document.getElementById("player").volume = 0.1;
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

/* bookmark */
$(document).ready(function () {
    $(".box-mark").click(function () {
    	 $.ajax({url: "/api/book/checkLogin", success: function(result){
  		   if(!result){
  			   alert("Vui lòng đăng nhập!")
  		   }else{  	
  			 let slug = $("#book-slug").val();
  			 let id = $("#audio-id").val();
  			 
  	    	 $.ajax({type: "POST", url: "/api/mark/" + slug+ "/audio/" + id, success: function(result){
  	    		 $(".box-unmark").addClass("show-mark");
  	  	    	$(".box-mark").removeClass("show-mark");
  	 		  }});
  		   }
  		}});
    	
    });
    
    $(".box-unmark").click(function () {
    	let slug = $("#book-slug").val();
    	let id = $("#audio-id").val();
		
    	 $.ajax({type: "DELETE", url: "/api/mark/" + slug + "/audio/id-" + id, success: function(result){
    		 $(".box-mark").addClass("show-mark");
    	    $(".box-unmark").removeClass("show-mark");
  		  }});
    });
    
    $.ajax({url: "/api/book/checkLogin", success: function(result){
		   if(result){
			   showBookmark();
		   }
	}});

    let searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has('chapter')) {
		let chapter = searchParams.get('chapter');
		$("li#audio-id-"+chapter).trigger("click");
	}else{
		let id = $("#audio-id").val();
		$("li#audio-id-"+id).trigger("click");
	}
  });

function playAudio(){
	audioPlayer.play();
}
  function showBookmark(){
	  let slug = $("#book-slug").val();
	  let id = $("#audio-id").val();
 
	  $.ajax({type: "GET", url: "/api/mark/"+slug+"/audio/"+id, success: function(result){
		    if(result){
		    	$(".box-unmark").addClass("show-mark");
		    	$(".box-mark").removeClass("show-mark");
		    }else{
		    	$(".box-mark").addClass("show-mark");
		    	$(".box-unmark").removeClass("show-mark");
		    }
		  }
  	});
  }
</script>