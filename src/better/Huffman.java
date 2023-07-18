package better;

import better.imp.ByteUtils;
import better.imp.EncodeWithW;
import better.imp.FileCoderImp;
import better.imp.StringCoderImp;
import t1.Tree;

import java.io.IOException;
import java.util.Scanner;
import java.util.SplittableRandom;

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
        Scanner in = new Scanner(System.in);
        System.out.println("--------------------HuffmanCoder--------------------");
//        while(true){
//            System.out.println("1.压缩字符串");
//            System.out.println("2.解压字符串");
//            System.out.println("3.压缩文件");
//            System.out.println("4.解压文件");
//            System.out.println("5.退出");
//            System.out.println("请输入你的选择：");
//            int cmd = in.nextInt();
//            if(cmd == 5) break;
//            else if(1 == cmd){
//                System.out.println("请输入要压缩的字符串：");
//                String text = in.next();
//                EncodeResult res = Huffman.getStringCoder().encode(text);
//                byte[] zipBytes = res.getZipBytes();
//                String s = Huffman.getStringCoder().decode(zipBytes);
//                System.out.println(s);
//                System.out.println(res.countRatio(text));
//            }
//
//        }

        String text2Code = "aabbccccdeeeeeeeeiosdfpnvdouqeirpejndfakdnoqieyrqpefndfvlakhdpqehtopfndfgvqpehtiqnejinfpdnfqew";
        String needToEncode = text2Code;
        EncodeResult res = Huffman.getStringCoder().encode(text2Code, false, needToEncode);
        System.out.println("\nmain");
//        int after_len = res.getZipBytes().length;
//        int before_len = text2Code.length() * 8;
//        double ratio = (double)(before_len - after_len)/ before_len;
        byte[] zipBytes = res.getZipBytes();
        String s = Huffman.getStringCoder().decode(zipBytes);
        System.out.println("编码后总信息"+res.getBinString());
        System.out.println("解码:"+s);
        System.out.println(res.countRatio(needToEncode));


//        String filePath = "D:\\code\\algo_design\\src\\better\\mydata.txt";
//        String resPath = "D:\\code\\algo_design\\src\\better\\mydata.txt.txt";
//        EncodeResult res1 = Huffman.getFileCoder().encode(filePath);
//        System.out.println(Huffman.getFileCoder().decode(resPath));
//        String ss = "10101000";
//        System.out.println(ByteUtils.intToBytes(ss));

//        String fileName = "D:\\code\\algo_design\\src\\letter-weight.txt";
//        EncodeWithW encodeWithW = new EncodeWithW();
//        encodeWithW.setFilePath(fileName);
//        String needToEncode = in.next();
//        EncodeResult res = Huffman.getStringCoder().encode(encodeWithW.getData(), true, needToEncode);
//        System.out.println(res.countRatio(needToEncode));
//        System.out.println(res.getBinString());
//        byte[] zipBytes = res.getZipBytes();
////        System.out.println(Huffman.getStringCoder().decode(zipBytes));
//        System.out.println("-----------------------------------------------------");
//        System.out.println(Huffman.getStringCoder().decode(res.getBinString()));
    }
}
