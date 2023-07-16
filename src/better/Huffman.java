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
        EncodeResult res = Huffman.getStringCoder().encode("aabc");
        System.out.println("\nmain");
        System.out.println(res.getZipBytes().length);
        String s = Huffman.getStringCoder().decode(res);
        System.out.println(s);
    }
}
