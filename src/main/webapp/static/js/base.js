/**
 * Created by 颂 on 2016/7/1.
 */
function showShadow(){
    var dHeight = document.body.scrollHeight;
    var a = "background: #000;position: absolute;top:0;width:100%;z-index:998;display:block;opacity:.6;";
    var div = document.createElement("div");
    div.setAttribute("style",a);
    div.setAttribute("id","shade");
    var bodys = document.getElementsByTagName("body");
    bodys[0].appendChild(div);
    div.style.height = dHeight+"px";
}


function hideShadow(){
	var bodys = document.getElementsByTagName("body");
	var div = document.getElementById("shade");
	bodys[0].removeChild(div);
}


function err(target, message){
	var t = $(target);
	if (t.hasClass('textbox-text')){
		t = t.parent();
	}
	var m = t.next('.error-message');
	if (!m.length){
		m = $('<div class="error-message"></div>').insertAfter(t);
	}
	m.html(message);
}

$(function(){
	var obj = $('[data-innerHTML-default]');
	if(obj != undefined){
		var content = $.trim(obj.html());
		console.log(content);
		if(content == ''){
			obj.html(obj.attr('data-innerHTML-default'));
		}
	}
	
});
