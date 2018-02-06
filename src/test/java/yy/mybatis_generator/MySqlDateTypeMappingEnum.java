package yy.mybatis_generator;

/**
 * mysql数据库映射java类型
 */
public enum MySqlDateTypeMappingEnum{
    
    bigint_("bigint","Long"), 
    long_("long","Long"), 
    varchar_("varchar","String"),
    datetime_("datetime","Date"),
    smallint_("smallint","Integer"),
    timestamp_("timestamp","Date"),
    date_("date","String"),
    dateChar_("char","String"),
    double_("double","Double"),
    decimal_("decimal","BigDecimal"),
    boolean_("boolean","Integer"),
    float_("float","Float"),
    int_("int","Integer"),
    tinyint_("tinyint","Integer"),
    text_("text","String"),
    mediumint_("mediumint","int"),
    enum_("enum","String"),
    bit_("bit","String");
    
    private final String code;
    private final String desc;
    
    MySqlDateTypeMappingEnum(String code, String desc) {
       this.code = code;
       this.desc = desc;
   }
   
   public String getCode() {
       return code;
   }
   
   public String getDesc(){
       return desc;
   }
   
   public static MySqlDateTypeMappingEnum getEnum(String code) {
       System.out.println("----------code:"+code);
       for(MySqlDateTypeMappingEnum e : MySqlDateTypeMappingEnum.values()) {
           if (code.equals(e.getCode())) {
               return e;
           }
       }
       return null;
   }
 
}

