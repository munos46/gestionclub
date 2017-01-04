$(window).load(function() {
	redimensionner();
});

function topbody(){
    $('html,body').animate({scrollTop: 0}, 'slow');
}
     	    
function redimensionner() {
	/* Taille de l'aire cliente */
    if ($(window).width() >= 400){
    	if ($(window).height() < $('#content').height()){
    		iValueHeight=$('#content').height();
      	}
    	else{
    		iValueHeight=$(window).height();
    	}
    	iValueWidth=48;
    }
     else{
    	 iValueHeight=48;
    	 iValueWidth=$(window).width();
    }

    $('#icon').height(iValueHeight);
    $('#icon').width(iValueWidth);
}
      		 
$(window).resize(function () {redimensionner();});
      		
function selection(element) {
      			
	$("#content_pie").hide(); 
  	$("#content_ok").hide();
  	$("#content_nok").hide(); 
  	$("#content_table").hide(); 
  	$("#content_about").hide(); 
      			
    if (element == "#content_pie")$("#content_pie").show();
    else if (element == "#content_ok")$("#content_ok").show();
    else if (element == "#content_nok")$("#content_nok").show();
    else if (element == "#content_table")$("#content_table").show();
    else if (element == "#content_about")$("#content_about").show();      			
  	redimensionner();
}
      	    
      		
$(document).ready(function(){
	
	selection("#content_pie");

    $("#icon_pie").click(function () {
    	selection("#content_pie");
    });
    $("#icon_ok").click(function () {
    	selection("#content_ok");
    });
    $("#icon_nok").click(function () {
    	selection("#content_nok");
    });
    $("#icon_table").click(function () {
    	selection("#content_table");         
    });
    $("#icon_about").click(function () {
    	selection("#content_about");   
    });
	$("#icon").click(function () {
    	topbody();  
    });
	
	$("a").click(function () {
    	selection("#content_table"); 
    });
	

	
})