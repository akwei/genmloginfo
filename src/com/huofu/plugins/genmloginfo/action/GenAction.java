package com.huofu.plugins.genmloginfo.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


/**
 * 获得方法签名的插件
 * Created by akwei on 10/2/15.
 */
public class GenAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (psiFile == null || editor == null) {
            return;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);
        String methodInfo = this.buildMethodLogKey(elementAt);
        if (methodInfo != null) {
            String info = this.createMethodLogInfo(methodInfo);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪切板
            StringSelection selection = new StringSelection(info);//构建String数据类型
            clipboard.setContents(selection, selection);//添加文本到系统剪切板
        }
    }

    private String createMethodLogInfo(String methodInfo) {
        return "cmd=log-open\n" +
                "log_args=true\n" +
                "n=" + methodInfo + "\n" +
                "open=true\n" +
                "log_result=true";
    }


    private String buildMethodLogKey(PsiElement elementAt) {
        PsiClass psiClass = PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
        if (psiClass == null) {
            return null;
        }
        PsiMethod psiMethod = PsiTreeUtil.getParentOfType(elementAt, PsiMethod.class);
        if (psiMethod == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(psiClass.getQualifiedName()).append('.');
        sb.append(psiMethod.getName()).append('(');
        PsiParameterList psiParameterList = psiMethod.getParameterList();
        int i = 0;
        for (PsiParameter psiParameter : psiParameterList.getParameters()) {
            PsiType psiType = psiParameter.getType();
            sb.append(psiType.getCanonicalText());
            if (i < psiParameterList.getParametersCount() - 1) {
                sb.append(',');
            }
            i++;
        }
        sb.append(')');
        return sb.toString();
    }
}
