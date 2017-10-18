/**
 * Created by 颂 on 2016/6/30.
 */
window.onload = function(){
    var tips = document.getElementById("tips");
    //  账号验证
    function identity(identity){
        if(identity==''){
            tips.style.display = "block";
            tips.innerText = "请输入账号";
            return false;
        }
        return true;
    }
    // 密码验证
    function password(pw){
        var pa=/^(\w){6,20}$/;
        if(pw=='密码'||pw==''){
            tips.style.display = "block";
            tips.innerText = "请输入密码";
            return false;
        }
        if(!pa.test(pw)){
            tips.style.display = "block";
            tips.innerText = "请输入正确密码";
            return false;
        }
        return true;
    }
    var clogin  = document.getElementById("clogin");
    clogin.onclick = function(){
        var identityText  = document.getElementById("identity").value;
        var passwordText  = document.getElementById("password").value;
        if(!identity(identityText)|| !password(passwordText)){
            return false;
        }
        else{
        	document.getElementById("loginform").submit();
        }
    }
};
