package com.huofu.plugins.genmloginfo.base;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public abstract class BaseCopyAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (psiFile == null || editor == null) {
            return;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);
        String methodInfo = GenUtil.buildMethodLogKey(elementAt);
        if (methodInfo != null) {
            String info = this.createMethodLogInfo(methodInfo);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪切板
            StringSelection selection = new StringSelection(info);//构建String数据类型
            clipboard.setContents(selection, selection);//添加文本到系统剪切板
        }
    }

    public abstract String createMethodLogInfo(String methodInfo);
}
