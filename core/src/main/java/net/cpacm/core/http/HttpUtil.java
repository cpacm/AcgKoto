
package com.caitu99.core.http;


/**
 * 所有的网络请求链接<br/>
 * all requests url
 *
 * @Auther: cpacm
 * @Date: 2015/10/27 0027-下午 5:38
 */
public class HttpUtil {

    /**
     * 预发域名
     */
    public final static String DOMAIN = "http://p.gateway.caitu99.com";
    public final static String WEIXINHTTP = "http://p.weixin.caitu99.com";
    public final static String STATICHTTP ="http://p.static.caitu99.com";

    /**
     * http地址和端口
     */
    //public final static String DOMAIN = "https://gateway.caitu99.com";
    public final static String HTTPURL = DOMAIN + "/gw/oauthentry";
    //public final static String STATICHTTP = "https://static.caitu99.com";
    //public final static String WEIXINHTTP = "https://weixin.caitu99.com";


    //android平台id和口令
    public final static String CLIENT_ID = "2";
    //TODO 切换线上环境
    //public final static String CLIENT_SECRET = "8cb1778faf49438ab40387116fa072df";//线下测试环境
    public final static String CLIENT_SECRET = "7ed3162ad92744ac9a03d5a59e633d8d";//线上生产环境

    /*------------------------------入口模块------------------------------*/
    public final static String ENTRY_TOKEN = DOMAIN + "/oauth/token";//token
    public final static String ENTRY_SMS = HTTPURL + "/sms/1.0/send";//发送登录验证码
    public final static String ENTRY_USER = HTTPURL + "/user.account/1.0/basicinfo";//获取用户信息
    public final static String ENTRY_CONFIG = HTTPURL + "/sys.config/1.0/list";//获取配置信息
    public final static String ENTRY_UPLOAD_APPMSG = HTTPURL + "/sys.app.version/1.0/add";//上传应用信息
    public final static String ENTRY_VERSION = HTTPURL + "/sys.app.version/1.0/update";//检查版本更新
    public final static String ENTRY_PUSH = HTTPURL + "/user.push/1.0/info";//推送注册id

    /*------------------------------生活模块------------------------------*/
    public final static String LIFE_PRODUCTSTORE = HTTPURL + "/goods.group/1.0/page";//积分生活商品首页
    public final static String LIFE_PHONE_MONEYLIST = HTTPURL + "/life.recharge.phone/1.0/list";//获取手机充值可选金额
    public final static String LIFE_PHONE_RECHARGE = HTTPURL + "/life.recharge/1.0/phone";//手机充值
    public final static String LIFE_RECORD_HISTORY = HTTPURL + "/life.product.o2o.exchange/1.0/history";//兑换记录
    public final static String LIFE_IMPORT_GIFT = HTTPURL + "/life.recharge/1.0/record";//导入奖励

    /*------------------------------积分模块------------------------------*/
    public final static String INTEGRAL_EXCHANGE_RATE = HTTPURL + "/integral.rule.exchange/2.0/list";//积分兑换比例
    public final static String INTEGRAL_POINT_EXPIREALL = HTTPURL + "/integral.card/1.0/expireall";//30/90/180到期积分
    public final static String INTEGRAL_QUERY_ALL = HTTPURL + "/user.card.integral/1.0/query";//获取首页各个平台总积分
    public final static String INTEGRAL_QUERY_CARDTYPE = HTTPURL + "/user.card.type.integral/1.0/query";//积分信息列表
    public final static String INTEGRAL_EXPIRED_TIME = HTTPURL + "/user.card.query.integral/1.0/expire";//首页最近到期时间
    public final static String INTEGRAL_EXPIRED_CARD = HTTPURL + "/integral.card/3.0/expire";//卡到期时间
    public final static String INTEGRAL_CARD_UPDATE = HTTPURL + "/integral.card.status/1.0/update";//修改卡的管理状态

    /*------------------------------账户模块------------------------------*/
    public final static String ACCOUNT_BIND_THIRD = HTTPURL + "/user.third/1.0/bind";//绑定第三方
    public final static String ACCOUNT_SUGGEST = HTTPURL + "/sys.suggest/1.0/submit";//意见反馈
    public final static String ACCOUNT_UPDATE_USER = HTTPURL + "/user.mobile/1.0/update";//用户信息更新
    public final static String ACCOUNT_UPLOAD_FACE = HTTPURL + "/user.photo/1.0/save";//上传头像
    public final static String ACCOUNT_INVITE_CODE = HTTPURL + "/user.manage.invitation/1.0/get";//获取用户的邀请码

    /*------------------------------消费模块------------------------------*/
    public final static String CONSUME_RECORD = HTTPURL + "/transaction.details/1.0/list";//交易记录
    public final static String CONSUME_RECHARGE = HTTPURL + "/transaction.recharge/1.0/recharge";//充值
    public final static String UNIONPAY_RECHARGE = HTTPURL + "/transaction.recharge.unionpay/1.0/recharge";//银联充值

    /*------------------------------银行模块------------------------------*/
    public final static String BANK_AUTH_BANK = HTTPURL + "/user.auth/1.0/info";//获取用户的绑定银行卡
    public final static String BANK_DETAIL_INFO = HTTPURL + "/user.bank.info/1.0/get";//根据银行卡号获取银行卡信息
    public final static String BANK_AUTH_USER = HTTPURL + "/user.user/1.0/auth";//身份认证
    public final static String BANK_AUTH_SENDSMS = HTTPURL + "/user.sms/1.0/sendauth";//发送身份认证短信
    public final static String BANK_WITHDRAW = HTTPURL + "/cash.integral/1.0/cashing";//提现

    /*------------------------------支付密码模块------------------------------*/
    public final static String PAYPASS_UPDATE = HTTPURL + "/user.paypass/1.0/update";//更新支付密码
    public final static String PAYPASS_CHECK = HTTPURL + "/user.paypass/1.0/check";//判断支付密码是否正确
    public final static String PAYPASS_RESET = HTTPURL + "/user.paypass/1.0/modify";//重置密码
    public final static String PAYPASS_AUTH = HTTPURL + "/user.judge/1.0/author";//支付密码判断是否实名认证
    public final static String PAYPASS_EXIST = HTTPURL + "/user.paypass/1.0/exist";//判断是否设置支付密码

    /*------------------------------账单模块------------------------------*/
    public final static String BILL_COOKIE_POST = HTTPURL + "/api/mail/qq/front/login/1.0";//上传cookie并开始获取邮箱列表
    public final static String BILL_EMAIL_LIST = HTTPURL + "/mail.list/1.0/get";//获取邮箱列表
    public final static String BILL_EMAIL_DELETE = HTTPURL + "/mail.one/1.0/del";//删除邮箱
    public final static String BILL_EMAIL_LOGIN = HTTPURL + "/mail.spider/1.0/login";//邮箱登录
    public final static String BILL_EMAIL_VERIFY = HTTPURL + "/mail.spider/1.0/verify";//邮箱验证码校验
    public final static String BILL_EMAIL_PWDALONE = HTTPURL + "/mail.spider/1.0/pwdalone";//邮箱独立密码校验
    public final static String BILL_EMAIL_RESULT = HTTPURL + "/mail.spider/1.0/checkresult";//邮箱解析结果
    public final static String BILL_EMAIL_REFRESH = HTTPURL + "/mail.spider/1.0/refresh";//邮箱更新
    public final static String BILL_EMAIL_REQUIRE = HTTPURL + "/mail.one/1.0/get";//获取单个邮箱的信息
    public final static String BILL_RECYCLE_COUNT = HTTPURL + "/user.card.dustbin/1.0/count";//待管理卡片个数
    public final static String BILL_RECYCLE_LIST = HTTPURL + "/user.card.dustbin/1.0/list";//待管理积分列表
    /*-------手动查询模块---------*/
    public final static String BILL_HAND_CARDLIST = HTTPURL + "/integral.manual/1.0/list";//可查询列表
    public final static String BILL_GENGEATE_PAGE = HTTPURL + "/integral.manual/1.0/generatelogin";//获取账号登陆界面信息
    public final static String BILL_MANUAL_LOGIN = HTTPURL + "/integral.manual/1.0/login";//账号登陆

    /*------------------------------APP内信息------------------------------*/
    public final static String MESSAGE_SIGNSTATUS = HTTPURL + "/user.sign/1.0/signstatus";//获取签到信息
    public final static String MESSAGE_SIGN = HTTPURL + "/user.sign/1.0/signevery";//每日签到
    public final static String MESSAGE_NOTIFY = HTTPURL + "/sys.app/1.0/notice";//首页公告
    public final static String MESSAGE_RECORD = HTTPURL + "/push.message/1.0/list";//app内信息记录
    public final static String MESSAGE_RECORD_DELETE = HTTPURL + "/push.message/1.0/del";//app内信息记录删除

    /*------------------------------其他模块------------------------------*/
    public final static String HOME_BANNER = HTTPURL + "/sys.banner/1.0/rotary";//首页banner
    public final static String FAQ_URL = STATICHTTP + "/apppage/app/app_faq.html";//faq页面地址
    public final static String ORDER_URL = WEIXINHTTP + "/pages/goods-order.html";//订单页面地址
    public final static String TICKET_URL = WEIXINHTTP + "/pages/coupon-list.html";//礼券页面地址
    public final static String CARDAPPLY_URL = WEIXINHTTP + "/manage/manage.html";//信用卡申请界面

    public final static String FOOD_URL = WEIXINHTTP + "/pages/goods-list.html?from=1&typeId=10001";//美食地址
    public final static String SHOP_URL = WEIXINHTTP + "/pages/goods-list.html?from=1&typeId=10002";//购物地址
    public final static String PLAY_URL = WEIXINHTTP + "/pages/goods-list.html?from=1&typeId=10003";//娱乐地址
    public final static String SERVICE_URL = WEIXINHTTP + "/pages/goods-list.html?from=1&typeId=10004";//生活服务地址

}
