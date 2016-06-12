package com.huofu.plugins.genmloginfo.action;

import com.huofu.plugins.genmloginfo.base.BaseCopyAction;


/**
 * 获得方法签名的插件
 * Created by akwei on 10/2/15.
 */
public class GenAction extends BaseCopyAction {

    public String createMethodLogInfo(String methodInfo) {
        return "cmd=log-open\n" +
                "log_args=true\n" +
                "n=" + methodInfo + "\n" +
                "open=true\n" +
                "log_result=true";
    }
}