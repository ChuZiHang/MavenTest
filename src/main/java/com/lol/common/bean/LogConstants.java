package com.lol.common.bean;

public interface LogConstants {

    // 0 产品操作日志 1 订单操作日志 2 课程日志 3 收费团课日志 4 私教日志
    int LOG_TYPE_PRODUCT = 0;

    int LOG_TYPE_ORDER = 1;

    int LOG_TYPE_COURSE = 2;

    int LOG_TYPE_COURESGRPUP = 3;

    int LOG_TYPE_COACH = 4;

    // 0 增加 1 删除 2 修改 3 查询 4 确认 5 验证 6 退款
    int LOG_ACTION_ADD = 0;

    int LOG_ACTION_DELETE = 1;

    int LOG_ACTION_MODIFY = 2;

    int LOG_ACTION_QUERY = 3;

    int LOG_ACTION_CONFIRM = 4;

    int LOG_ACTION_VERIFY = 5;

    int LOG_ACTION_REFUND = 6;
    // 相关文字信息根据自己做的业务添加到下面

    String LOG_CARD_ADD = "会员卡新增";

    String LOG_CARD_MODIFY = "会员卡修改";

    String LOG_CARD_DELETE = "会员卡删除";

    // 收费团课相关
    String LOG_MEMO_GROUP_DELECT = "收费团课删除";

    String LOG_MEMO_GROUP_ADD = "新建收费团课";

    String LOG_MEMO_GROUP_UPDATE = "收费团课修改";

    String LOG_MEMO_PRODUCT_GROUP_ADD = "发布团课";

    String LOG_MEMO_PRODUCT_GROUP_MODIFY = "团课产品修改";

    String LOG_MEMO_ORDER_GROUP_MODIFY = "团课产品订单申请退款";

    // 产品表
    // 10 卡 11 收费团课 12 课程 13 私教
    int PRODUCT_PRO_TYPE_CARD = 10;

    int PRODUCT_PRO_TYPE_GROUP = 11;

    int PRODUCT_PRO_TYPE_COURSE = 12;

    int PRODUCT_PRO_TYPE_COACH = 13;

    // 0 进行中 1 进行中 2已完成
    int PRODUCT_COURSE_STATUS_B = 0;

    int PRODUCT_COURSE_STATUS_J = 1;

    int PRODUCT_COURSE_STATUS_Y = 2;
}
