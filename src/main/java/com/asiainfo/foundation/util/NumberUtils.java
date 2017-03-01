package com.asiainfo.foundation.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

public abstract class NumberUtils extends org.springframework.util.NumberUtils
{
  public static double add(double v1, double v2)
  {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.add(b2).doubleValue();
  }

  public static double subtract(double v1, double v2)
  {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.subtract(b2).doubleValue();
  }

  public static double multiply(double v1, double v2)
  {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.multiply(b2).doubleValue();
  }

  public static double multiply(double v1, double v2, int scale)
  {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.multiply(b2).setScale(scale, 4).doubleValue();
  }

  public static double divide(double v1, double v2)
  {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.divide(b2).doubleValue();
  }

  public static double divide(double v1, double v2, int scale)
  {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }

    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.divide(b2, scale, 4).doubleValue();
  }

  public static double round(double v, int scale)
  {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b = new BigDecimal(v);
    return b.setScale(scale, 4).doubleValue();
  }

  public static int compare(Number left, Number right) throws IllegalArgumentException {
    Assert.notNull(left, "left number must not be null");
    Assert.notNull(right, "right number must not be null");

    if (left.getClass().equals(Byte.class)) {
      return ((Byte)left).compareTo((Byte)right);
    }
    if (left.getClass().equals(Short.class)) {
      return ((Short)left).compareTo((Short)right);
    }
    if (left.getClass().equals(Integer.class)) {
      return ((Integer)left).compareTo((Integer)right);
    }
    if (left.getClass().equals(Long.class)) {
      return ((Long)left).compareTo((Long)right);
    }
    if (left.getClass().equals(BigInteger.class)) {
      return ((BigInteger)left).compareTo((BigInteger)right);
    }
    if (left.getClass().equals(Float.class)) {
      return ((Float)left).compareTo((Float)right);
    }
    if (left.getClass().equals(Double.class)) {
      return ((Double)left).compareTo((Double)right);
    }
    if (left.getClass().equals(BigDecimal.class)) {
      return ((BigDecimal)left).compareTo((BigDecimal)right);
    }

    throw new IllegalArgumentException("Could not compare left number [" + left + "] of type [" + 
      left.getClass().getName() + "] to right number [" + right + "] of type [" + 
      right.getClass().getName() + "]");
  }

  public static int safeLongToInt(long l)
  {
    if ((l < -2147483648L) || (l > 2147483647L)) {
      throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
    }
    return (int)l;
  }

  public static String formatNumericFee(BigDecimal number)
  {
    return number.setScale(2, 4).toString();
  }

  public static String formatNumericTraffic(BigDecimal number)
  {
    return number.setScale(6, 4).toString();
  }

  public static String mbToTGM(BigDecimal mb)
  {
    StringBuffer sb = new StringBuffer(200);
    int g = 0;
    int t = 0;
    BigDecimal m = new BigDecimal(0);

    t = new Float(mb.divide(new BigDecimal(1048576), 1).floatValue()).intValue();
    mb = mb.subtract(new BigDecimal(t).multiply(new BigDecimal(1048576)));
    g = new Float(mb.divide(new BigDecimal(1024), 1).floatValue()).intValue();
    mb = mb.subtract(new BigDecimal(g).multiply(new BigDecimal(1024)));
    m = mb.setScale(2, 4);

    sb.append(new Integer(t).toString()).append(" TB ");

    sb.append(new Integer(g).toString()).append(" GB ");

    sb.append(m.toString()).append(" MB ");

    return sb.toString();
  }

  public static Integer readInt(byte[] value)
  {
    if ((value == null) || (value.length < 4))
      return null;
    try
    {
      ByteArrayInputStream bais = new ByteArrayInputStream(value);
      DataInputStream dis = new DataInputStream(bais);
      Integer retval = Integer.valueOf(dis.readInt());
      bais.close();
      dis.close();
      return retval; } catch (Exception e) {
    }
    return null;
  }

  public static Short readShort(byte[] value)
  {
    if ((value == null) || (value.length < 2))
      return null;
    try
    {
      ByteArrayInputStream bais = new ByteArrayInputStream(value);
      DataInputStream dis = new DataInputStream(bais);
      Short retval = Short.valueOf(dis.readShort());
      bais.close();
      dis.close();
      return retval; } catch (Exception e) {
    }
    return null;
  }

  public static byte[] intToByteArray(Integer value)
  {
    if (value == null) {
      return null;
    }
    try
    {
      byte[] data = (byte[])null;
      ByteArrayOutputStream bais = new ByteArrayOutputStream(4);
      DataOutputStream dos = new DataOutputStream(bais);
      dos.writeInt(value.intValue());
      data = bais.toByteArray();
      bais.close();
      dos.close();
      return data; } catch (Exception e) {
    }
    return null;
  }

  public static byte[] longToByteArray(long value)
  {
    try {
      byte[] data = (byte[])null;
      ByteArrayOutputStream bais = new ByteArrayOutputStream(8);
      DataOutputStream dos = new DataOutputStream(bais);
      dos.writeLong(value);
      data = bais.toByteArray();
      bais.close();
      dos.close();
      return data; } catch (Exception e) {
    }
    return null;
  }

  public static Long readUnsingedInt(byte[] data, int offset)
  {
    try {
      DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
      dis.skip(offset);
      Long retval = null;
      int tmp = dis.readInt();
      if (tmp < 0) {
        retval = Long.valueOf(tmp + 4294967296L);
      }
      else {
        retval = Long.valueOf(tmp);
      }

      dis.close();
      return retval; } catch (Exception e) {
    }
    return null;
  }

  public static boolean isNumeric(String str)
  {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);

    return isNum.matches();
  }
}