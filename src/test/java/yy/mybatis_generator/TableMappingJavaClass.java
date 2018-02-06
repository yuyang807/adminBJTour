package yy.mybatis_generator;

import java.util.List;

/**
 * 表映射java类
 * */
public class TableMappingJavaClass {
    //类引用import
    private StringBuffer importStringBuffer;
    //表名称
    private String tableName;
    //表说明备注
    private String tableComment;
    //表字段列表
    private List<TableColumnMappingJavaClass> columnList;
    
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableComment() {
        return tableComment;
    }
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
    public List<TableColumnMappingJavaClass> getColumnList() {
        return columnList;
    }
    public void setColumnList(List<TableColumnMappingJavaClass> columnList) {
        this.columnList = columnList;
    }
    public StringBuffer getImportStringBuffer() {
        return importStringBuffer;
    }
    public void setImportStringBuffer(StringBuffer importStringBuffer) {
        this.importStringBuffer = importStringBuffer;
    }
}
