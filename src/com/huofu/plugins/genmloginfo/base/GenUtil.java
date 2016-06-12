package com.huofu.plugins.genmloginfo.base;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * Created by akwei on 6/12/16.
 */
public class GenUtil {

    public static String buildMethodLogKey(PsiElement elementAt) {
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
