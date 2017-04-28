// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.tang.intellij.lua.psi.LuaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.tang.intellij.lua.psi.*;

public class LuaFieldListImpl extends ASTWrapperPsiElement implements LuaFieldList {

  public LuaFieldListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LuaVisitor visitor) {
    visitor.visitFieldList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LuaVisitor) accept((LuaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LuaTableField> getTableFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LuaTableField.class);
  }

  @Override
  @NotNull
  public List<LuaTableFieldSep> getTableFieldSepList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LuaTableFieldSep.class);
  }

}
