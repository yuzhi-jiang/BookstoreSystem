package com.example.BookstoreSystem.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @author yefeng
 * @version 1.0.0
 * @className com.example.BookstoreSystem.bean.Result
 * @Description TODO
 * @createTime 2022/05/10
 * @SEE
 * @Doc 结果
 */ //@JsonInclude(value = JsonInclude.Include.NON_NULL)//当data为空时不返回data
/*如
{
	"errCode": 200,
	"errMsg": "登录成功",
	"data":null
}变为
{
	"errCode": 200,
	"errMsg": "登录成功"
}*/
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Result {
    /**
     * 错误代码
     */
    private Integer errCode;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 数据
     */
    private Object data;

    /**
     * json对象
     *
     * @return {@code JSONObject}
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = JSONObject.parseObject(getJsonString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return getJsonString();
    }
    //成功
    public static Result success(Object data) {
        return new Result(ResCode.SUCCESS, data);
    }
    /**
     * @return @return {@code Result }
     * @throws
     * @title success
     * @description TODO
     * @author yefeng
     * @Param @param errMsg 犯错味精
     * @updateTime 2022/05/07
     */
    public static Result success(String errMsg) {
        return new Result(ResCode.SUCCESS, errMsg);
    }

    /**
     * @return @return {@link Result }
     * @throws
     * @title success
     * @description TODO
     * @author yefeng
     * @Param
     * @updateTime 2022/05/07
     */
    public static Result success() {
        return new Result(ResCode.SUCCESS);
    }

    //失败
    public static Result fail(String errMsg) {
        return new Result().setResultCode(ResCode.FAIL).setErrMsg(errMsg);
    }

    public static Result fail() {
        return new Result(ResCode.FAIL);
    }

    //失败
    public static Result failure(String errMsg) {
        return new Result().setResultCode(ResCode.FAIL).setErrMsg(errMsg);
    }

    public static Result failure() {
        return new Result(ResCode.FAIL);
    }


    public Result() {
    }

    public Result(ResCode resCode) {
        this.errCode = resCode.getErrCode();
        this.errMsg = resCode.getErrMessage();
    }

    public Result setResultCode(ResCode resCode) {
        this.errCode = resCode.getErrCode();
        this.errMsg = resCode.getErrMessage();
        return this;
    }

    public Result(ResCode resCode, Object data) {
        this.errCode = resCode.getErrCode();
        this.errMsg = resCode.getErrMessage();
        this.data = data;
    }

    public Result(Integer errCode, String errMsg, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public Result setErrCode(Integer errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Result setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public Object getData() {
        if (data == null) return "";
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 得到json字符串
     *
     * @return {@link String}
     */
    private String getJsonString() {
        String res = "{";
        if (errCode != null) res += "\"errCode\":" + errCode;
        if (errMsg != null) res += ",\"errMsg\":\"" + errMsg + "\"";
        if (data != null) res += ",\"data\":" + data;
        res += "}";
        return res;
    }
    public static void main(String[] args) {
        Result success = Result.success();
        System.out.println(success);

        Result fail = Result.fail();
        System.out.println(fail);

        Result fail1 = Result.fail("失败");
        System.out.println(fail1);

        String jsonString = Result.success(1).getJsonString();
        System.out.println(jsonString);


        Result result = new Result();
        result.setResultCode(ResCode.SUCCESS);
        System.out.println(result);

        Result result1 = new Result().setErrCode(401).setErrMsg("失败");
        System.out.println(result1);


    }
}
