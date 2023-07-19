package better;

import better.imp.ByteUtils;
import better.imp.EncodeWithW;
import better.imp.FileCoderImp;
import better.imp.StringCoderImp;
import t1.Tree;

import java.io.*;
import java.util.Scanner;
import java.util.SplittableRandom;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:15
 * @注释
 */
public class Huffman {
    private static Scanner in = new Scanner(System.in);
    public static StringCoder getStringCoder() {
        return new StringCoderImp();
    }
    public static FileCoderImp getFileCoder() {
        return new FileCoderImp();
    }
    public static String countRatio(String text, String binaryS){
        int textLen = text.length() * 8;
        int binaryLen = binaryS.length();
        return "压缩率："+((textLen - binaryLen) / textLen) * 100 + "%";
    }

    public static void main(String[] args) throws IOException {
        while(true){
            System.out.println("\n欢迎使用Huffman编码器！\n");
            System.out.println("1.压缩字符串");
            System.out.println("2.解压字符串");
            System.out.println("3.压缩文件");
            System.out.println("4.解压文件");
            System.out.println("5.退出");
            System.out.println("请输入你的选择：");
            int cmd = 0;
            try{
                cmd = in.nextInt();
            }catch (Exception e){
                System.out.println("输入错误，请重新输入！");
                in.nextLine();
                continue;
            }
            switch (cmd){
                case 1:
                    cmd1();
                    break;
                case 2:
                    cmd2();
                    break;
                case 3:
                    cmd3();
                    break;
                case 4:
                    cmd4();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
    }
    static void cmd1(){
        boolean hasW = false;
        System.out.println("字符串是否自带权重？(y/n)");
        String hasWStr = in.next();
        hasW = hasWStr.equals("y") || hasWStr.equals("Y");
        in.nextLine(); //读取换行符
        System.out.println("请输入字符集或者字符集所在的文件路径：");
        StringBuilder dataSet = new StringBuilder();
        String input = in.nextLine();
        File file = new File(input);
        if(file.exists() && file.isFile()){
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line = null;
                while((line = br.readLine()) != null){
                    if(hasW)
                        dataSet.append(line+"\n");
                    else
                        dataSet.append(line);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            dataSet.append(input);
        }
//                    System.out.println("dataSet:"+dataSet);
        System.out.println("请输入要编码的字符串：");
        String needToEncode = in.nextLine();
        EncodeResult res = Huffman.getStringCoder().encode(dataSet.toString(), hasW, needToEncode, true);
        System.out.println("编码后总信息\n"+res.getBinString());
    }
    public static void cmd2(){
        System.out.println("请输入要解码的二进制字符串：");
        String needToDecode = in.next();
        String text = Huffman.getStringCoder().decode(needToDecode);
        System.out.println("解码:"+text);
        System.out.println(countRatio(text, needToDecode));
    }
    public static void cmd3(){
        System.out.println("请输入要压缩的文件路径：");
        String filePath = in.next();
        EncodeResult res1 = Huffman.getFileCoder().encode(filePath);
        System.out.println("压缩后文件路径："+filePath+".txt");
    }
    public static void cmd4() throws IOException {
        System.out.println("请输入要解压的文件路径：");
        String resPath = in.next();
        System.out.println("解压后文件内容："+Huffman.getFileCoder().decode(resPath));
    }
}
