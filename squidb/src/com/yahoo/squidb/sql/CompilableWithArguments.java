/*
 * Copyright 2015, Yahoo Inc.
 * Copyrights licensed under the Apache 2.0 License.
 * See the accompanying LICENSE file for terms.
 */
package com.yahoo.squidb.sql;

import com.yahoo.squidb.utility.VersionCode;

abstract class CompilableWithArguments {

    @Override
    public String toString() {
        return toRawSql(CompileContext.defaultContextForVersionCode(VersionCode.LATEST));
    }

    @Deprecated
    public final String toRawSql(VersionCode sqliteVersion) {
        return buildSql(CompileContext.defaultContextForVersionCode(sqliteVersion), false, false).getSqlString();
    }

    public final String toRawSql(CompileContext compileContext) {
        return buildSql(compileContext, false, false).getSqlString();
    }

    @Deprecated
    protected final SqlBuilder buildSql(VersionCode sqliteVersion, boolean withBoundArguments,
            boolean forSqlValidation) {
        return buildSql(CompileContext.defaultContextForVersionCode(sqliteVersion), withBoundArguments,
                forSqlValidation);
    }

    protected final SqlBuilder buildSql(CompileContext compileContext, boolean withBoundArguments,
            boolean forSqlValidation) {
        SqlBuilder builder = new SqlBuilder(compileContext, withBoundArguments);
        appendToSqlBuilder(builder, forSqlValidation);
        return builder;
    }

    abstract void appendToSqlBuilder(SqlBuilder builder, boolean forSqlValidation);

}
