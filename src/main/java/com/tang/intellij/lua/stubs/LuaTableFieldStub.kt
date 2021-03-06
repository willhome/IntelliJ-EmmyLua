/*
 * Copyright (c) 2017. tangzx(love.tangzx@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tang.intellij.lua.stubs

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubBase
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.util.PsiTreeUtil
import com.tang.intellij.lua.lang.type.LuaTableType
import com.tang.intellij.lua.psi.*
import java.util.*

/**
 * table field stub
 * Created by tangzx on 2017/1/14.
 */
interface LuaTableFieldStub : StubElement<LuaTableField> {
    val typeName: String?
    val fieldName: String?
}
class LuaTableFieldStubImpl : StubBase<LuaTableField>, LuaTableFieldStub {
    private var tableField: LuaTableField? = null
    private var _typeName: String? = null
    private var _fieldName: String? = null

    constructor(field: LuaTableField,
                parent: StubElement<*>,
                elementType: IStubElementType<*, *>) : super(parent, elementType) {
        tableField = field
    }

    constructor(typeName: String,
                fieldName: String,
                stubElement: StubElement<*>,
                elementType: IStubElementType<*, *>) : super(stubElement, elementType) {
        _typeName = typeName
        _fieldName = fieldName
    }

    override val typeName: String?
        get() {
            if (_typeName == null && tableField != null) {
                val table = PsiTreeUtil.getParentOfType(tableField, LuaTableExpr::class.java)
                val optional = Optional.ofNullable(table)
                        .filter { s -> s.parent is LuaExprList }
                        .map<PsiElement> { it.parent }
                        .filter { s -> s.parent is LuaAssignStat }
                        .map<PsiElement> { it.parent }
                        .map<String> { s ->
                            val assignStat = s as LuaAssignStat
                            LuaPsiImplUtil.getTypeName(assignStat, 0)
                        }
                _typeName = optional.orElse(LuaTableType.getTypeName(table))
            }
            return _typeName
        }

    override val fieldName: String?
        get() {
            if (_fieldName != null)
                return _fieldName
            return tableField?.fieldName
        }
}