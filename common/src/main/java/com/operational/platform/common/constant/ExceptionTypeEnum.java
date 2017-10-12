package com.operational.platform.common.constant;

/**
 * 异常类型
 *
 * @author: Zhou Xuanang
 * @Date: 10:07 16/6/27.
 */
public enum ExceptionTypeEnum {
    SUCCESS("000000", "成功!"),
    HTTP_REQUEST_ERROR("000001", "http请求失败!"),
    REQUEST_WECHAT_SERVER_ERROR("000002", "请求微信服务器失败"),
    OPENID_NOT_EXIST_ERROR("000003", "openid不存在"),
    Get_Unionid_ERROR("000004", "获取微信unionid失败"),
    Get_User_Info_ERROR("000005", "获取用户基本信息失败"),
    Get_Investor_Info_ERROR("000006","获取投资人信息失败"),
    File_Download_ERROR("000007","文件下载失败"),
    Get_Investor_Career_ERROR("000008","获取投资人职业生涯信息失败"),
    Get_Investor_Case_ERROR("000009","获得投资人投资案例失败"),
    Get_Org_Info_ERROR("000010","获取机构信息失败"),
    Get_Exit_Case_ERROR("000011","获取退出案例失败"),
    Get_Contact_ERROR("000012","获取机构联系方式失败"),
    Get_Managers_ERROR("000013","获取管理团队失败"),
    Get_Statistics_ERROR("000014","获取统计信息失败"),
    Get_Invest_Trend_ERROR("000015","获取投资趋势失败"),
    Get_Exit_Trend_ERROR("000016","获取退出趋势失败"),
    Get_Funds_ERROR("000017","获取管理基金失败"),
    Get_Investor_List_ERROR("000018","获取投资人列表信息失败"),
    ;

    public final String code;

    public final String description;

    ExceptionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}