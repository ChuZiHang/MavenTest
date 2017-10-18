
/**
 * Created by 颂 on 2016/6/30.
 */
$(function(){
    // 登录页body定义高度

    var bodyHeight = $(document).height();
    $('.l_boby').css("height",bodyHeight-100);   //   减去padding-top100px

    //  登录页输入框自定义样式

    $("input").on("focus",function(){
        var $me = $(this);
        $me.css("border","1px solid rgb(130,151,176)")
    }).on("blur",function(){
        var $me = $(this);
        $me.css("border","1px solid rgb(228,228,228)")
    });

    // 记住密码自定义样式

    $(".remberico").click(function(){
        if($(this).hasClass("rpass")){
            $(this).removeClass("rpass")
        }
        else{
            $(this).addClass("rpass")
        }
    })

    // 左侧导航划过图标变化

    $(".navtree li").on("mouseover",function(){
        var i = $(this).index();
        switch (i)
        {
            case 0:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/kechengguanli_huang.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 1:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/jiaolianguanli_huang.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 2:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/huiyuan_huang.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 3:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/huiyuanguanli_huang.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 4:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/caiwu_huang.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 5:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/shezhi_huang.png) no-repeat",
                    "background-size":"100%"
                });
                break;
        }
    }).on("mouseout",function(){
        var i = $(this).index();
        switch (i)
        {
            case 0:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/kechengguanli_hui.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 1:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/jiaolianguanli_hui.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 2:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/huiyuan_hui.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 3:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/huiyuanguanli_hui.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 4:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/caiwu_hui.png) no-repeat",
                    "background-size":"100%"
                });
                break;
            case 5:
                $(this).find("i").css({
                    "background":"url(http://10.0.0.21:8087/qdj/images/shezhi_hui.png) no-repeat",
                    "background-size":"100%"
                });
                break;
        }
    }).on("click",function(event){
        $(this).find(".childNav").slideToggle("100",function(){
            if($(this).is(":hidden")){
                $(this).parent().find("em").removeClass("on");
            }
            else{
                $(this).parent().find("em").addClass("on");
            }
        });
    });

    // 消息展开收起

    $(".noticeTextBox").each(function() {
        var noticeHeight = $(this).outerHeight();
        if (noticeHeight > 57) {
            $(this).css("height", 57);
            $(this).parents(".noticeText").find(".messopera").show();
        }
        var mydd = $('.messopera');
        mydd.each(function () {
            var num = 0;
            $(this).click(function () {
                if (num == 0) {
                    $(this).parents(".noticeText").find(".noticeTextBox").css("height", "auto");
                    $(this).text("收起");
                    num++;
                }
                else {
                    $(this).parents(".noticeText").find(".noticeTextBox").css("height", 57);
                    $(this).text("展开");
                    num = 0;
                }
            })
        })
    });

    //   发送消息弹框
    $(".send_message_btn").click(function(){
        showShadow();
        $(".messagebox").show();
    });

    // 选择发送通知分店对象
    $(".chooseshopmess span i").click(function(){
        if($(this).hasClass('rpass')){
            $(this).removeClass('rpass')
        }
        else{
            $(this).addClass('rpass')
        }
    });

    // 选择全部分店
    $('.allshop').click(function(){
        if($('.allshop').hasClass('rpass')){
            $(this).removeClass('rpass')
            $('.chooseshopmess span i').removeClass('rpass')
        }
        else{
            $(this).addClass('rpass')
            $('.chooseshopmess span i').addClass('rpass')
        }
    });

    // 视图列表 大图查看方式

    $(".datu").click(function(){
        $(".view_bigimg").show();
        $(".view_list").hide();
        $(this).addClass("viewBar");
        $(".liebiao").removeClass("viewBar");
    });
    $(".liebiao").click(function(){
        $(".view_list").show();
        $(".view_bigimg").hide();
        $(this).addClass("viewBar");
        $(".datu").removeClass("viewBar");
    });

    // 新建课程
    $(".create_syllabus").on("click",function(){
        showShadow();
        $(".create_syllabus_box").show();
    });

    // 课程详细选择框
    $(".itemSelect").click(function(){
        $(".itemSelectList").show();
        return false;
    });

    // 课程详细选择框选择列表文字
    $(".itemSelectList li").click(function(){
        var itemSelectListText = $(this).text();
        $(".itemSelectList").hide();
        $(".itemSelectText").text(itemSelectListText);
        return false;
    });

    // 点击头像查看个人中心
    $(".joiner img").click(function(){
        showShadow();
        $("#Popup").show();
    });

    //  删除评论列表提示信息
    $(".commentdel").click(function(){
        $(this).parents("li").addClass("remove");
        showShadow();
        $("#Popup").show();
    });

    // 关闭自定义系统弹出框
    $(".PopupBtnyes").click(function(){
        $(this).parents("#Popup").hide();
        $(".remove").remove();
        $("#shade").remove();
    });

    // 回复用户评价
    $(".exchangecomment").click(function(){
        showShadow();
        $(".repaly").show();
        $(".replayText").val("");
    })

    $(".replayyes").click(function(){
        var t = $(".replayText").val();
        $(".commentopera span").text(t);
        $("#shade").remove();
        $(".repaly").hide();
    });

    //  新建收费团课是否支持退款
    $(".tuikuan i").click(function(){
        var rel=$(this).attr('rel');
        $(this).addClass("on").parent().siblings().find("i").removeClass("on");
        //alert(rel);
        if(rel == 1){
            $(".poundage").show();
            $(".j_sijiao").show();
        }
        else{
            $(".poundage").hide();
            $(".j_sijiao").hide();
        }
    });

    // 发布课程选择分店
    $(".chooseshop").click(function(){
        $(this).find(".choose_gev_shop").toggle();
    });
    $(".choosemerber").click(function(){
        $(".choosemerber_shop").toggle();
    });

    $(".choose_gev_shop p").click(function(){
        var text = $(this).text();
        $(this).parents(".chooseshop").find(".chooseText").text(text)
    })

    // 发布课程添加业务员
    $(".joinbusiness").click(function(){
        $(".joinbusinessName").show();
    });

    // 修改记录弹出框
    $(".setrecord").click(function(){
        showShadow();
        $(".setRecordBox").show();
    });


    // 团课列表发布课程
    $(".fabukecheng").click(function(){
        showShadow();
        $(".push-syllabus").show();
    });

    // 团课列表发布框
    $(".setfabu").click(function(){
        showShadow();
        $(".push-syllabus").show();
    });

    // 课程报名进行中tag
    $(".syllabus_detail_tag span").click(function(){
        var index = $(this).index();
        $(this).addClass("on").siblings().removeClass("on");
        if(index == 0){
            $(".syllabus_detail_show").show();
            $(".joinerInfor").hide();
            $(".signListbox").hide();
        }
        else if(index == 1){
            $(".syllabus_detail_show").hide();
            $(".joinerInfor").show();
            $(".signListbox").hide();
        }
        else{
            $(".syllabus_detail_show").hide();
            $(".joinerInfor").hide();
            $(".signListbox").show();
        }
    });

    //  团课退款流程弹出框
    $(".j_tuikuan").click(function(){
        $(".refund-apply").show();
        showShadow();
    });

    // 取消关闭弹框
    $(".close").click(function(){
        $(this).parents(".closebox").hide();
        $("#shade").remove();
    });

    $(".confirm").click(function(){
        $("#Popup").show();
        $(this).parents(".closebox").hide();
    });

    // 添加签到详细信息展示
    $(".j_xiangqing").on("click",function(){
        $(this).parents("li").next().toggle();
    });

    // 会员管理列表签到信息展示隐藏
    $(".qiandao").click(function(){
        $(this).parents("li").find(".sign").toggle();
    });

    // 签到信息展开查看签到人
    $(".sign-show").click(function(){
        $(this).parent().siblings().slideToggle("100",function(){
            if($(this).is(":hidden")){
                $(this).siblings().find(".sign-show").text("展开");
            }
            else{
                $(this).siblings().find(".sign-show").text("收起");
            }
        });
    })

    // 新建会员卡弹框
    $(".create_card").click(function(){
        showShadow();
        $(".create_card_cont").show();
    });

    // 群发短信弹框
    $(".send_message").click(function(){
        showShadow();
        $(".groupMessage").show();
    });

    // 选择发送对象
    $(".sendObject span").click(function(){
        $(this).addClass("on").siblings().removeClass("on")
    });

    // 文本框字符字数
    $(".messText").on("keyup",function(){
        var str = $(this).val();
        var len =str.length;
        if(len>=57){
            $(this).parents(".groupMessageBox").find(".fillNumber").text(57);
            $(this).val(str.substring(0,57))
        }
        else{
            $(this).parents(".groupMessageBox").find(".fillNumber").text(len);
        }
    });

    // 会员管理发送短信弹出框
    $(".send_mess").click(function(){
        showShadow();
        $(".sendMessage").show();
    });

    // 会员管理退款弹出框
    $(".member_tuikuan").click(function(){
        showShadow();
        $(".merber_refund").show();
    });

    // 提现支付宝绑定
    $(".bindAlipay").click(function(){
        showShadow();
        $(".fliiAlipay").show();
    });

    // 提现银行卡绑定
    $(".bindBank").click(function(){
        showShadow();
        $(".fliiBankpay").show();
    });


    // 提现银行卡手动填写分行
    $(".shoudong").click(function(){
        $(".xuanzefenhang").show();
        $(".shoudongshuru").hide();
    });
   // 提现银行卡手动选择分行
    $(".zidong").click(function(){
        $(".xuanzefenhang").hide();
        $(".shoudongshuru").show();
    });
    // 是否进入其他平台分销
    $(".terrace span i").click(function(){
        var rel=$(this).attr('rel');
        $(this).addClass("on").parent().siblings().find("i").removeClass("on");
    });



    $(".salesman-toggle").click(function(){
        $(this).parent().siblings().slideToggle(function(){
            if($(this).is(":hidden")){
                $(this).siblings().css("background","#fff");
                $(this).siblings().find(".salesman-toggle").text("展开");
            }
            else{
                $(this).siblings().css("background","#f5f5f5");
                $(this).siblings().find(".salesman-toggle").text("收起");
            }
        });
    });

    $(".j-zong").click(function(){
        showShadow();
        $(".joinsalesmanBox").show();
    });

    $(".changeauthority").click(function(){
        showShadow();
        $(".changeauthorityBox").show();
    })

});