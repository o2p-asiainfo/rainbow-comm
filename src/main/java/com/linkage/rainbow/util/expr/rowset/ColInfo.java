package com.linkage.rainbow.util.expr.rowset;
import java.io.Serializable;

/**
 * 
 * 每一个字段的信息
 * @version 1.0
 * @author 陈亮 2011-03-04
 *         <hr>
 *         修改记录
 *         <hr>
 *         1、修改人员:陈亮 修改时间:2011-03-04<br>
 *         修改内容:新建
 *         <hr>
 *
 */
public class ColInfo implements Serializable{
  /**
     * 
     */
  private static final long serialVersionUID = -5626592952349676521L;
  public ColInfo(){
  }

  boolean autoIncrement;
  boolean caseSensitive;
  boolean currency;
  int nullable;
  boolean signed;
  boolean searchable;
  int columnDisplaySize;
  String columnLabel;
  String columnName;
  String columnComment;
  String schemaName;
  int colPrecision;
  int colScale;
  String tableName;
  String catName;
  int colType;
  String colTypeName;
}