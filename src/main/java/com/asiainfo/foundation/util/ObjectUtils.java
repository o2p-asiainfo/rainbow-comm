package com.asiainfo.foundation.util;

import java.lang.reflect.Array;
import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;

public class ObjectUtils extends org.springframework.util.ObjectUtils
{
  public static Object[] addToArray(Object[] array, Object[] objs)
  {
    Class<?> compType = Object.class;
    if (array != null) {
      compType = array.getClass().getComponentType();
    }
    else if (objs != null) {
      compType = objs.getClass().getComponentType();
    }
    int newArrLength = array != null ? array.length + objs.length : objs.length;
    Object[] newArr = (Object[])Array.newInstance(compType, newArrLength);
    if (array != null) {
      System.arraycopy(array, 0, newArr, 0, array.length);
    }
    System.arraycopy(objs, 0, newArr, newArr.length - objs.length, objs.length);
    return newArr;
  }

  public static boolean isArray(Object source) {
    if (source != null) {
      if ((source instanceof Object[])) {
        return true;
      }
      if (source.getClass().isArray()) {
        return true;
      }
    }
    return false;
  }

  public static Object[] safeToObjectArray(Object source)
  {
    if (source == null) {
      return new Object[0];
    }
    if ((source instanceof Object[])) {
      return (Object[])source;
    }
    if (!source.getClass().isArray()) {
      return new Object[] { source };
    }
    int length = Array.getLength(source);
    if (length == 0) {
      return new Object[0];
    }
    Class<? extends Object> wrapperType = Array.get(source, 0).getClass();
    Object[] newArray = (Object[])Array.newInstance(wrapperType, length);
    for (int i = 0; i < length; i++) {
      newArray[i] = Array.get(source, i);
    }
    return newArray;
  }

  public static boolean isEmpty(Object obj)
  {
    return (obj == null) || (((obj instanceof String)) && ("".equals(obj))) || 
      ((Collection.class.isInstance(obj)) && (((Collection<?>)obj).isEmpty())) || (
      ((obj instanceof Object[])) && (((Object[])obj).length <= 0));
  }

  public static boolean isNotEmpty(Object obj)
  {
    return !isEmpty(obj);
  }

  public static Object[] removeObjectFromArray(Object[] array, Object obj) {
    if (array == null) {
      return null;
    }
    int index = -1;
    for (int i = 0; i < array.length; i++) {
      if (nullSafeEquals(array[i], obj)) {
        index = i;
        break;
      }
    }
    if (index > -1) {
      return ArrayUtils.remove(array, index);
    }
    return array;
  }

  public static boolean exactlyEQ(Object v1, Object v2)
  {
    return v1 == null ? false : v1.equals(v2);
  }
}
