package yy.mybatis_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yy.bjtours.common.utils.DateUtil;

public class MakeJavaMybatisCode {
    public static final String url = "jdbc:mysql://localhost:3306/bj_tour_db";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "appuser";  
    public static final String password = "appuser";
    //需要生成的表名称,生成多个时以","分割开。为空时按整库生成
    public static final String tableNames = "t_line_addition";
//    public static final String tableNames = "t_addition,t_advice,t_car,t_carkind,t_carkind_service,t_carservice,t_guide,t_line,t_linekind,t_member,t_order,t_pic,t_show";
    
    
    public static final String filePath="D:/workspace/adminBJTour_v2/src/main/java/com/yy/bjtours/modules/web";
    
    //转换驼峰命名
    public static String toCamelCase(String str){
        str=str.toLowerCase();
        while (str.indexOf("_")>0) {
            int i = str.indexOf("_");
            String subChar = str.substring(i+1, i+2);
            str=str.replaceFirst("_"+subChar, subChar.toUpperCase());
        }
        return str;
    }
    
    //表名称转换java类名称
    public static String tableName2JavaClassName(String str){
        str=toCamelCase(str);
        String fristChar = str.substring(0, 1);
        str=str.replaceFirst(fristChar, fristChar.toUpperCase())+"Dto";
        return str;
    }
    
    public static void main(String[] args) {
        try {
            String tableSchema = url.substring(url.lastIndexOf("/")+1);
            String mainPackage = filePath.substring(filePath.indexOf("java")+5).replace("/", ".");
            Class.forName(name);//指定连接类型  
            Connection conn = DriverManager.getConnection(url, user, password);//获取连接  
            PreparedStatement pst = conn.prepareStatement("select table_name,table_comment from information_schema.tables where table_schema='"+tableSchema+"'");//准备执行语句  
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                TableMappingJavaClass tableMappingJavaClass=new TableMappingJavaClass();
                String tableName = resultSet.getString(1);
                String tableComment = resultSet.getString(2);
                tableMappingJavaClass.setTableName(tableName);
                tableMappingJavaClass.setTableComment(tableComment);
                if (tableNames!=null&&tableName.trim()!="") {
                    String[] tables = tableNames.split(",");
                    boolean flag=false;
                    for (String string : tables) {
                        if (tableName.equals(string)) {
                            flag=true;
                            break;
                        }
                    }
                    if (!flag) {
                        continue; 
                    }
                }
                
                System.out.println("---------tableName="+tableName+",tableComment:"+tableComment);
                String sql = "select COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE,COLUMN_KEY from information_schema.columns where table_name='"+tableName+"' and table_schema='"+tableSchema+"'";
                PreparedStatement pst1 = conn.prepareStatement(sql);
                ResultSet resultSet1 = pst1.executeQuery();
                StringBuffer dto_buf=new StringBuffer();
                StringBuffer idao_buf=new StringBuffer();
                dto_buf.append("package "+mainPackage+".dto;\n\n");
                idao_buf.append("package "+mainPackage+".dao;\n\n");
                idao_buf.append("");
                List<TableColumnMappingJavaClass> columnlist=new ArrayList<TableColumnMappingJavaClass>();
                Map<String, String> mySqlDateTypeMap=new HashMap<String, String>();
                String primaryKey = null;
                while (resultSet1.next()) {
                	String columnKey = resultSet1.getString(4);
                	if (null!=columnKey&&columnKey.toLowerCase().indexOf("pri")!=-1 && resultSet1.getString(1).equals("id")) {
                		primaryKey=resultSet1.getString(1);
                		continue;
                	}
                    String columnDataType = resultSet1.getString(3);
                    String columnJavaDataType = MySqlDateTypeMappingEnum.getEnum(columnDataType).getDesc();
                    if (!mySqlDateTypeMap.containsKey(columnJavaDataType)) {
                        if ("datetime".equals(columnDataType)||"timestamp".equals(columnDataType)) {
                            dto_buf.append("import java.util.Date;\n");
                            mySqlDateTypeMap.put(columnJavaDataType, columnJavaDataType);
                        }
                        if ("decimal".equals(columnDataType)) {
                            dto_buf.append("import java.math.BigDecimal;\n");
                            mySqlDateTypeMap.put(columnJavaDataType, columnJavaDataType);
                        }
                    }
                    TableColumnMappingJavaClass tableColumnMappingJavaClass=new TableColumnMappingJavaClass();
                    tableColumnMappingJavaClass.setColumnName(resultSet1.getString(1));
                    tableColumnMappingJavaClass.setColumnComment(resultSet1.getString(2));
                    tableColumnMappingJavaClass.setColumnDataType(columnDataType);
                    columnlist.add(tableColumnMappingJavaClass);
                }
//                dto_buf.append("import com.qianbao.ipos.common.IDto;\n");
//                dto_buf.append("\n");
                dto_buf.append("import com.yy.bjtours.common.persistence.DataEntity;\n");
                dto_buf.append("\n");
                dto_buf.append("/**\n");
                dto_buf.append("  * ").append(tableComment).append("(").append(tableName).append(")").append("映射Dto\n");
                dto_buf.append("  * @version ").append(DateUtil.DToSLine(new Date()));
                dto_buf.append("  * */ \n");
                String className = tableName2JavaClassName(tableName);
                dto_buf.append("public class ").append(className).append(" extends DataEntity<").append(className).append("> {\n");
//                dto_buf.append("public class ").append(className).append(" implements IDto {\n");
                
                StringBuffer setgetBuf=new StringBuffer();
                StringBuffer baseColumnList=new StringBuffer();
                StringBuffer insertMapperBuf=new StringBuffer();
                StringBuffer insertMapperBuf1=new StringBuffer();
                StringBuffer whereMapperBuf=new StringBuffer();
                StringBuffer updateMapperBuf=new StringBuffer();
                for (TableColumnMappingJavaClass tableColumnMappingJavaClass : columnlist) {
                    String columnComment = tableColumnMappingJavaClass.getColumnComment().toLowerCase();
                    String columnName = tableColumnMappingJavaClass.getColumnName().toLowerCase();
                    baseColumnList.append(columnName).append(", ");
                    //表字段的驼峰命名字段
                    String camelCaseColumnName = toCamelCase(columnName);
                    String columndataType = tableColumnMappingJavaClass.getColumnDataType().toLowerCase();
                    insertMapperBuf.append("        <if test=\"").append(camelCaseColumnName).append(" != null \">\n");
                    insertMapperBuf1.append("        <if test=\"").append(camelCaseColumnName).append(" != null \">\n");
                    whereMapperBuf.append("        <if test=\"").append(camelCaseColumnName).append(" != null \">\n");
                    insertMapperBuf.append("          ").append(columnName).append(",\n").append("        </if>\n");
                    insertMapperBuf1.append("          #{").append(camelCaseColumnName).append("},\n").append("        </if>\n");
                    //表字段映射的java数据类型字段
                    String javadataType = MySqlDateTypeMappingEnum.getEnum(columndataType).getDesc();
                    whereMapperBuf.append("          ").append(columnName).append(" = #{").append(camelCaseColumnName).append("} AND\n").append("        </if>\n");
                    if (!columnName.equals(primaryKey)&&!"update_date".equals(columnName)) {
                        updateMapperBuf.append("        <if test=\"").append(camelCaseColumnName).append(" != null \">\n");
                        updateMapperBuf.append("          ").append(columnName).append(" = #{").append(camelCaseColumnName).append("} ,\n").append("        </if>\n");
                    }
                    if ("version".equals(columnName)) {
                        updateMapperBuf.append("        <if test=\"").append(camelCaseColumnName).append(" != null \">\n");
                        updateMapperBuf.append("          ").append(columnName).append(" = #{").append(camelCaseColumnName).append("} ,\n").append("        </if>\n");
                    }
                    /**
                     * Dto属性字段注解及填充
                     * */
                    dto_buf.append("    /**\n     * ").append(columnComment).append("\n").append("     * */\n");
                    dto_buf.append("    private ").append(javadataType).append(" ").append(camelCaseColumnName).append(";\n");
                    //表字段名称首字母大写
                    String firstChar = camelCaseColumnName.substring(0, 1);
                    String firstCharUpperColumnName = camelCaseColumnName.replaceFirst(firstChar, firstChar.toUpperCase());
                    setgetBuf.append("    /**\n     * set").append(firstCharUpperColumnName).append(" ").append(columnComment).append("\n").append("     * */\n");
                    setgetBuf.append("    public void set").append(firstCharUpperColumnName).append("(").append(javadataType).append(" ").append(camelCaseColumnName).append(") {\n");
                    setgetBuf.append("        this.").append(camelCaseColumnName).append("=").append(camelCaseColumnName).append(";\n").append("    }\n\n");
                    setgetBuf.append("    /**\n     * get").append(firstCharUpperColumnName).append(" ").append(columnComment).append("\n").append("     * */\n");
                    setgetBuf.append("    public ").append(javadataType).append(" get").append(firstCharUpperColumnName).append("() {\n");
                    setgetBuf.append("        return ").append(camelCaseColumnName).append(";\n").append("    }\n\n");
                    if(javadataType.equals("Date")){
                        dto_buf.append("    /**\n     * ").append("范围查询"+columnComment+"使用").append("\n").append("     * */\n");
                        dto_buf.append("    private ").append("Date start"+firstCharUpperColumnName).append(";\n");
                        dto_buf.append("    /**\n     * ").append("范围查询"+columnComment+"使用").append("\n").append("     * */\n");
                        dto_buf.append("    private ").append("Date end"+firstCharUpperColumnName).append(";\n");
                        
                        setgetBuf.append("    /**\n     * set").append("Start"+camelCaseColumnName).append(" ").append(columnComment).append("\n").append("     * */\n");
                        setgetBuf.append("    public void set").append("Start"+camelCaseColumnName).append("(Date start"+firstCharUpperColumnName).append(") {\n");
                        setgetBuf.append("        this.").append("start"+firstCharUpperColumnName).append("=").append("start"+firstCharUpperColumnName).append(";\n").append("    }\n\n");
                        setgetBuf.append("    /**\n     * get").append("Start"+camelCaseColumnName).append(" ").append(columnComment).append("\n").append("     * */\n");
                        setgetBuf.append("    public Date get").append("Start"+camelCaseColumnName).append("() {\n");
                        setgetBuf.append("        return ").append("start"+firstCharUpperColumnName).append(";\n").append("    }\n\n");
                        
                        setgetBuf.append("    /**\n     * set").append("End"+camelCaseColumnName).append(" ").append(columnComment).append("\n").append("     * */\n");
                        setgetBuf.append("    public void set").append("End"+camelCaseColumnName).append("(Date end"+firstCharUpperColumnName).append(") {\n");
                        setgetBuf.append("        this.").append("end"+firstCharUpperColumnName).append("=").append("end"+firstCharUpperColumnName).append(";\n").append("    }\n\n");
                        setgetBuf.append("    /**\n     * get").append("End"+camelCaseColumnName).append(" ").append(columnComment).append("\n").append("     * */\n");
                        setgetBuf.append("    public Date get").append("End"+camelCaseColumnName).append("() {\n");
                        setgetBuf.append("        return ").append("end"+firstCharUpperColumnName).append(";\n").append("    }\n\n");
                        
                        whereMapperBuf.append("        <if test=\"").append("start"+firstCharUpperColumnName).append(" != null \">\n");
                        whereMapperBuf.append("          ").append(columnName).append(" >= #{").append("start"+firstCharUpperColumnName).append("} AND\n").append("        </if>\n");
                        whereMapperBuf.append("        <if test=\"").append("end"+firstCharUpperColumnName).append(" != null \">\n");
                        whereMapperBuf.append("          ").append(columnName).append(" <![CDATA[<=]]> #{").append("end"+firstCharUpperColumnName).append("} AND\n").append("        </if>\n");
                    }
                }
                
                dto_buf.append("    /**\n     * 排序字段加排序规则组合体columnName desc\n     * */\n");
                dto_buf.append("    private String orderByStr;\n");
                setgetBuf.append("    /**\n     * 排序字段加排序规则组合体columnName desc\n     * */\n");
                setgetBuf.append("    public void setOrderByStr(String orderByStr) {\n        this.orderByStr=orderByStr;\n    }\n\n");
                setgetBuf.append("    public String getOrderByStr() {\n        return orderByStr;\n    }\n\n");
                
                
                //mapper生成
                StringBuffer mapperXml=new StringBuffer();
                mapperXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
                mapperXml.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
                mapperXml.append("<mapper namespace=\"").append(mainPackage).append(".dto.").append(className).append("\">\n\n");
                mapperXml.append("    <sql id=\"Base_Column_List\" >\n");
                mapperXml.append("         ").append(baseColumnList.subSequence(0, baseColumnList.length()-2)).append("\n");
                mapperXml.append("    </sql>\n\n");
                mapperXml.append("    <insert id=\"insert\" parameterType=\"").append(mainPackage).append(".dto.").append(className).append("\">\n");
                mapperXml.append("       INSERT into ").append(tableName).append("\n");
                mapperXml.append("       <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n");
                mapperXml.append(insertMapperBuf).append("       </trim>\n");
                mapperXml.append("       <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >\n");
                mapperXml.append(insertMapperBuf1).append("       </trim>\n");
                mapperXml.append("    </insert>\n\n");
                mapperXml.append("    <delete id=\"deleteByPrimaryKey\" parameterType=\"").append(mainPackage).append(".dto.").append(className).append("\">\n");
                mapperXml.append("       DELETE FROM ").append(tableName).append(" WHERE ").append(primaryKey).append(" = #{").append(toCamelCase(primaryKey)).append("};\n");
                mapperXml.append("    </delete>\n\n");
                mapperXml.append("    <delete id=\"delete\" parameterType=\"").append(mainPackage).append(".dto.").append(className).append("\">\n");
                mapperXml.append("       DELETE FROM ").append(tableName).append(" WHERE \n");
                mapperXml.append("       <trim suffix=\"\" suffixOverrides=\"AND\">\n");
                mapperXml.append(whereMapperBuf).append("       </trim>\n");
                mapperXml.append("    </delete>\n\n");
                mapperXml.append("    <update id=\"update\" parameterType=\"").append(mainPackage).append(".dto.").append(className).append("\">\n");
                mapperXml.append("       UPDATE ").append(tableName).append("\n");
                mapperXml.append("       <trim prefix=\"SET\" suffixOverrides=\",\">\n");
                mapperXml.append(updateMapperBuf);
                mapperXml.append("       </trim>\n");
                mapperXml.append("       WHERE ").append(primaryKey).append(" = #{").append(toCamelCase(primaryKey)).append("}\n");
                mapperXml.append("    </update>\n\n");
                mapperXml.append("    <select id=\"query\" parameterType=\"").append(mainPackage).append(".dto.").append(className).append("\"");
                mapperXml.append(" resultType=\"").append(className).append("\">\n");
                mapperXml.append("       SELECT <include refid=\"Base_Column_List\" />").append("\n");
                mapperXml.append("       FROM ").append(tableName).append("\n");
                mapperXml.append("       WHERE ").append("\n");
                mapperXml.append("       <trim suffix=\"\" suffixOverrides=\"AND\">\n");
                mapperXml.append(whereMapperBuf).append("       </trim>\n");
                mapperXml.append("       <if test=\"orderByStr != null\">\n");
                mapperXml.append("       order by ${orderByStr}\n");
                mapperXml.append("       </if>\n");
                mapperXml.append("    </select>\n");
                mapperXml.append("    <select id=\"count\" parameterType=\"").append(mainPackage).append(".dto.").append(className).append("\"");
                mapperXml.append(" resultType=\"java.lang.Long\">\n");
                mapperXml.append("       SELECT count(*) FROM ").append(tableName).append("\n");
                mapperXml.append("       WHERE ").append("\n");
                mapperXml.append("       <trim suffix=\"\" suffixOverrides=\"AND\">\n");
                mapperXml.append(whereMapperBuf).append("       </trim>\n");
                mapperXml.append("    </select>\n");
                
                
                
                //配置文件生成路径
                File dtoFile=new File(filePath+"/dto/");
                if (!dtoFile.exists()) {
                    dtoFile.mkdirs();
                }
                File file=new File(dtoFile, className+".java");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                dto_buf.append(setgetBuf);
                dto_buf.append("}");
                fileOutputStream.write(dto_buf.toString().getBytes("UTF-8"));
                fileOutputStream.close();
                
                //配置文件生成路径
                File xmlFile=new File(filePath.substring(0, filePath.indexOf("main")+5)+"/resources/mappings/modules/web/");
//                File xmlFile=new File(filePath.substring(0, filePath.indexOf("main")+5)+"/resources/config/mappers/");
                if (!xmlFile.exists()) {
                    xmlFile.mkdirs();
                }
                File xml=new File(xmlFile, className+".xml");
                if (xml.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(xml);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader br=new BufferedReader(inputStreamReader);
                    boolean addFlag=false;
                    String str=null;
                    while ((str = br.readLine()) != null) {
                        if (addFlag) {
                            mapperXml.append(str).append("\n");
                        }
                        if (null!=str) {
                            if (str.indexOf("#######")>0) {
                                addFlag=true;
                                mapperXml.append(str).append("\n");
                            }
                        }
                    }
                    br.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    xml.delete();
                }else {
                    mapperXml.append("<!-- ##############################################自定义sql分界线##################################################################### -->\n");
                    mapperXml.append("</mapper>");
                }
                file.createNewFile();
                FileOutputStream xmlFileOutputStream=new FileOutputStream(xml);
                xmlFileOutputStream.write(mapperXml.toString().getBytes("UTF-8"));
                xmlFileOutputStream.close();
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
    }
}
