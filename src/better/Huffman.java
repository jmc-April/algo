package better;

import better.imp.StringCoderImp;

import java.util.Map;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:15
 * @注释
 */
public class Huffman {
    public static StringCoder getStringCoder() {
        return new StringCoderImp();
    }
    public static void main(String[] args) {
        Map<String, String> res = Huffman.getStringCoder().encode("hello world");
        System.out.println(res);
        String s = "asb";
        byte[] b = s.getBytes();
        System.out.println(b.length);
        System.out.println(b[0]);
        System.out.println(b.toString());

    }
}
