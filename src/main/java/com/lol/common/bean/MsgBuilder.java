package com.lol.common.bean;

import com.lol.common.Constants;

/**
 * 订单短信生成器类
 * 
 * @author cunxing
 *
 */
public class MsgBuilder {

    /**
     * 短信模板
     */
    private String template;

    /**
     * 信息类型
     * 
     * @param type
     * @return
     */
    public MsgBuilder setType(int type) {
        switch(type) {
            case MsgType.MEMBERCARD:
                this.template = "感谢您购买启动健%s-%s，会员卡有效期%s天，激活后即可开始享受每周海量课程！如有疑问请致电：%s";
                break;
            case MsgType.COURSE:
                this.template = "您已成功预订%s%s%s，数字验证码：%s，图片验证码请在服务号“启动健”内查看。如有疑问请致电：%s";
                break;
            case MsgType.COURSEGROUP:
                this.template = "您已成功预订%s%s，课程时间为%s-%s。数字验证码：%s，图片验证码请在服务号“启动健”内查看。如有疑问请致电：%s";
                break;
            case MsgType.COACH:
                this.template = "您已成功预订%s教练%s节私教课程，上课地点为%s。数字验证码：%s，图片验证码请在服务号“启动健”内查看。如有疑问请致电：%s";
                break;
            case MsgType.MEMBERCARD_ACTIVE:
                this.template = "您已成功激活启动健%s-%s，有效期%s-%s。如有疑问请致电：%s";
                break;
            case MsgType.MEMBER_CUT:
                this.template = "会员%s.旷课时间达到三次";
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * 创建信息内容
     * 
     * @param params
     *        <br>
     *        会员卡：会员卡适用店铺、会员卡名、会员卡时间、手机号；<br>
     *        课程：所属分店、课程时间、 课程名、验证码、手机号；<br>
     *        收费团课：所属分店、课程名、课程开始时间、课程结束时间、验证码、手机号；<br>
     *        私教：教练姓名、购买数量、所选分店、验证码、手机号；<br>
     *        激活会员卡：会员卡适用店铺、会员卡名、会员卡开始时间、会员卡到期时间、手机号；<br>
     * @return
     */
    public String create(Object[] params) {
        return String.format(this.template, params);
    }

    public static void main(String[] args) {
        MsgBuilder msgBuilder = new MsgBuilder();
        msgBuilder.setType(MsgType.MEMBERCARD);
        String msg = msgBuilder.create(new Object[]{"启动健通用", "月卡", 30, Constants.SMS_SPORT_PHONE});
        System.out.println(msg);
    }

}
