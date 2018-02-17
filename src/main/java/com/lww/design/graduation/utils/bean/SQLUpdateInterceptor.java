package com.lww.design.graduation.utils.bean;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author liweiwei
 * @date   2018/2/13
 * @description
 */

//@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SQLUpdateInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SQLUpdateInterceptor.class);

    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("I'm In");
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
        Object parameter = args[PARAMETER_INDEX];
        final BoundSql boundSql = ms.getBoundSql(parameter);

        args[MAPPED_STATEMENT_INDEX] = newMappedStatement(ms, parameter, boundSql);

        return invocation.proceed();
    }

    private Object newMappedStatement(MappedStatement ms, Object parameter, BoundSql boundSql) {

        String sql = removeUnixDateTime(boundSql.getSql());


        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),
                sql,
                new ArrayList<>(boundSql.getParameterMappings()),
                parameter);

        System.err.println(newBoundSql.getSql());

        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

    private String removeUnixDateTime(String sql) {
        if (sql.contains("modified_at = unix_timestamp()")) {
            return sql.replaceAll(",\\s*[\\n]\\s*modified_at = unix_timestamp\\(\\)","");
        }
        return sql;
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties arg0) {
    }

    public static void main(String[] args) {
        String sql = "update xx set xx = xx ,\n" +
                " modified_at = unix_timestamp() where id = 1";
        System.err.println(sql.replaceAll(",\\s*[\\n]\\s*modified_at = unix_timestamp\\(\\)", ""));
        sql.replaceAll("\\smodified_at = unix_timestamp\\(\\)$", "");
    }

}
