package com.vip.spider.constant;

/**
 * 异常类型
 *
 * @author: Zhou Xuanang
 * @Date: 10:07 16/6/27.
 */
public enum ExceptionTypeEnum {
    SUCCESS("000000", "成功!"),
    LIST_PAGE_LOAD_ERROR("000001", "列表页请求失败!"),
    FAVOUR_PAGE_LOAD_ERROR("000002", "优惠详情页面请求失败!"),
    FAVOUR_URL_PARSE_ERROR("000003", "优惠详情链接获取错误!"),
    LIST_POSITION_PARSE_ERROR("000004", "优惠信息列表位置解析错误!"),
    LIST_PARAMS_PARSE_ERROR("000005", "列表参数解析错误!"),
    FAVOUR_PAGE_PARSE_ERROR("000006", "优惠详情页面解析失败!"),
    FAVOUR_PAGE_LEVEL_PARSE_ERROR("000007", "优惠详情下一层解析失败"),
    ATTACHMENTS_DOWNLOAD_ERROR("000008", "附件下载失败!"),
    PROTOCOL_NOT_FIX_ERROR("000009", "无法确定请求协议http/https!"),
    ELEMENT_NOT_EXIST_ERROR("000010", "元素不存在"),
    HTTP_REQUEST_ERROR("000011", "http请求超时"),
    COMMENT_ERROR("000012", "评论失败"),
    GET_COMMENT_ERROR("000013", "获取评论失败"),
    NOT_LOGIN_ERROR("000014", "未登录"),
    UNKNOW_ERROR("000015", "未知错误"),
    INIT_HTTPCLIENT_ERROR("000016", "初始化httpClient错误"),
    MESSAGE_SEND_ERROR("000017", "信息发送失败"),;

    public final String code;

    public final String description;

    ExceptionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

}