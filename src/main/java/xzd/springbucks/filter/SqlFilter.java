package xzd.springbucks.filter;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.TableStat;
import lombok.extern.slf4j.Slf4j;
import java.util.List;


@Slf4j
public class SqlFilter extends FilterEventAdapter {
    @Override
    protected void statementExecuteBefore(StatementProxy statement, String sql) {
        SQLStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> stmtList = parser.parseStatementList();
        if(stmtList.size()>1){
            log.error("do not allow split sql:{}",sql);
        }
        for(SQLStatement sqlStatement : stmtList){
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            sqlStatement.accept(visitor);
            for (TableStat.Condition condition : visitor.getConditions()) {
                if (condition.getOperator().equals("IN") && condition.getValues().size() > 10) {
                    log.error("sql condition count out of range:{}", sql);
                }
            }
        }
        super.statementExecuteBefore(statement, sql);
    }
}
