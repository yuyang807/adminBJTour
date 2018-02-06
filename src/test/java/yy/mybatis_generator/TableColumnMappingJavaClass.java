package yy.mybatis_generator;
/**
 * 表字段映射java类
 * */
public class TableColumnMappingJavaClass {
    //字段名称
    private String columnName;
    //字段备注说明
    private String columnComment;
    //字段类型
    private String columnDataType;
    
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getColumnComment() {
        return columnComment;
    }
    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
    public String getColumnDataType() {
        return columnDataType;
    }
    public void setColumnDataType(String columnDataType) {
        this.columnDataType = columnDataType;
    }
}
