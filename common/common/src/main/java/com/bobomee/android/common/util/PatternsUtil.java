package com.bobomee.android.common.util;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created on 16/7/9.下午11:54.
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public class PatternsUtil {
  /**
   * 校验内容是否匹配指定正则表达式
   *
   * @param regex 正则表达式
   * @param inputValue 内容
   * @return 是否匹配 true为匹配，false为不匹配
   */
  public static boolean testRegex(String regex, String inputValue) {
    return Pattern.compile(regex).matcher(inputValue).matches();
  }

  /**
   * 判断手机号码是否格式正确
   *
   * @param value 手机号码字符串
   * @return true为正确，false为错误
   */
  public static boolean matcherPhoneNum(String value) {
    // 匹配11数字，并且13-19开头
    String regex = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";
    return testRegex(regex, value);
  }

  /**
   * 判断邮箱格式正确
   *
   * @param value 邮箱字符串
   * @return true为正确，false为错误
   */
  public static boolean matcherEmail(String value) {
    String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
        "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
        "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
    return testRegex(regex, value);
  }

  /**
   * 判断身份证格式正确性
   *
   * @param value 身份证字符串
   * @return true为正确，false为错误
   */

  public static boolean matcherIdentityCard(String value) {
    IDCardTester idCardTester = new IDCardTester();
    return idCardTester.test(value);
  }

  /**
   * 身份证校验
   * <P>
   * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
   * 地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。
   * 出生日期码表示编码对象出生的年、月、日，其中年份用四位数字表示，年、月、日之间不用分隔符。
   * 顺序码表示同一地址码所标识的区域范围内，对同年、月、日出生的人员编定的顺序号。顺序码的奇数分给男性，偶数分给女性。
   * 校验码是根据前面十七位数字码，按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。
   * 出生日期计算方法。
   * 15位的身份证编码首先把出生年扩展为4位，简单的就是增加一个19或18,这样就包含了所有1800-1999年出生的人;
   * 2000年后出生的肯定都是18位的了没有这个烦恼，至于1800年前出生的,那啥那时应该还没身份证号这个东东，⊙﹏⊙b汗...
   * 下面是正则表达式:
   * 出生日期1800-2099  /(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])/
   * 身份证正则表达式 /^[1-9]\d{5}((1[89]|20)\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dx]$/i
   * 15位校验规则 6位地址编码+6位出生日期+3位顺序号
   * 18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
   * 校验位规则     公式:∑(ai×Wi)(mod 11)……………………………………(1)
   * 公式(1)中：
   * i----表示号码字符从由至左包括校验码在内的位置序号；
   * ai----表示第i位置上的号码字符值；
   * Wi----示第i位置上的加权因子，其数值依据公式Wi=2^(n-1）(mod 11)计算得出。
   * i 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
   * Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
   * </P>
   *
   * @author Yoojia.Chen (yoojia.chen@gmail.com)
   * @version version 2015-05-21
   * @since 2.0
   */
  private static class IDCardTester {
    public boolean test(String content) {
      if (TextUtils.isEmpty(content)) {
        return false;
      }
      final int length = content.length();
      if (15 == length) {
        try {
          return isOldCNIDCard(content);
        } catch (NumberFormatException e) {
          e.printStackTrace();
          return false;
        }
      } else if (18 == length) {
        return isNewCNIDCard(content);
      } else {
        return false;
      }
    }

    final int[] WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    final char[] VALID = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

    public boolean isNewCNIDCard(String numbers) {
      //转换字符串中的字母为大写字母
      numbers = numbers.toUpperCase();
      int sum = 0;
      for (int i = 0; i < WEIGHT.length; i++) {
        final int cell = Character.getNumericValue(numbers.charAt(i));
        sum += WEIGHT[i] * cell;
      }
      int index = sum % 11;
      return VALID[index] == numbers.charAt(17);
    }

    public boolean isOldCNIDCard(String numbers) {
      //ABCDEFYYMMDDXXX
      String yymmdd = numbers.substring(6, 11);
      boolean aPass = numbers.equals(String.valueOf(Long.parseLong(numbers)));
      boolean yPass = true;
      try {
        new SimpleDateFormat("yyMMdd").parse(yymmdd);
      } catch (Exception e) {
        LogUtil.w("----IDCard 校验失败 : " + numbers);
        e.printStackTrace();
        yPass = false;
      }
      LogUtil.e("----IDCard aPass : " + aPass);
      LogUtil.e("----IDCard yPass : " + yPass);
      return aPass && yPass;
    }
  }
}
