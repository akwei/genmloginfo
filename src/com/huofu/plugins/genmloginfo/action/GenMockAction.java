package com.huofu.plugins.genmloginfo.action;

import com.huofu.plugins.genmloginfo.base.BaseCopyAction;


/**
 * 获得方法签名的插件
 * Created by akwei on 10/2/15.
 */
public class GenMockAction extends BaseCopyAction {

    public String createMethodLogInfo(String methodInfo) {
        return "cmd=mock-ex\n" +
                "n=" + methodInfo + "\n" +
                "ex_name=org.apache.thrift.TException\n" +
                "ex_sleep=10\n" +
                "ex_redo=true\n" +
                "ex_after=true\n" +
                "errorCode=110\n" +
                "message=测试异常\n" +
                "";
    }
}
