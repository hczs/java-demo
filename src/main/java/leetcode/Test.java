package leetcode;

import cn.hutool.crypto.SecureUtil;

/**
 * @author ：hc
 * @date ：Created in 2021/8/29 21:49
 * @modified By：
 */
public class Test {
    public static void main(String[] args) {
        String s = SecureUtil.md5("991215hc");
        System.out.println(s);
    }
}
