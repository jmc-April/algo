package better;

import better.imp.FileCoderImp;
import better.imp.StringCoderImp;

import java.io.IOException;

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
    public static FileCoderImp getFileCoder() {
        return new FileCoderImp();
    }
    public static void main(String[] args) throws IOException {
        String text2Code = "hello world";
        EncodeResult res = Huffman.getStringCoder().encode(text2Code);
        System.out.println("\nmain");
        int after_len = res.getZipBytes().length;
        int before_len = text2Code.length() * 8;
        double ratio = (double)(before_len - after_len)/ before_len;

        System.out.println(res.getZipBytes().length);
        System.out.println(res.getZipBytes());
        byte[] zipBytes = res.getZipBytes();
        String s = Huffman.getStringCoder().decode(zipBytes);
        System.out.println(s);
        System.out.println("压缩后的长度：" + after_len);
        System.out.println("压缩前的长度：" + before_len);
        System.out.println("压缩率：" + ratio * 100 + "%");


        String filePath = "D:\\code\\algo_design\\src\\better\\mydata.txt";
        String resPath = "D:\\code\\algo_design\\src\\better\\mydata.txt.txt";
        EncodeResult res1 = Huffman.getFileCoder().encode(filePath);
        System.out.println(Huffman.getFileCoder().decode(resPath));
    }
}
