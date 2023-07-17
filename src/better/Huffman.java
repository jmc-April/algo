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
        EncodeResult res = Huffman.getStringCoder().encode("abcdefg");
        System.out.println("\nmain");
        System.out.println(res.getZipBytes().length);
        System.out.println(res.getZipBytes());
        byte[] zipBytes = res.getZipBytes();
        String s = Huffman.getStringCoder().decode(zipBytes);
        System.out.println(s);
    }
}
