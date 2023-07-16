package better;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/15 22:42
 * @注释
 */
public class ncode {
    public static void main(String[] args) {
        String s = "qweasd";
        byte[] b = s.getBytes();
        System.out.println(Arrays.toString(b));
    }
}
