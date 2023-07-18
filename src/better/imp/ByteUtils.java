package better.imp;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 19:35
 * @注释
 */
public class ByteUtils {
    public static byte[] intToBytes(Map<String, String> map, String data) {
        StringBuilder tableAndEncodeText = new StringBuilder();
        int huffmanCodeLength = 0; // 哈夫曼编码长度
        Map<String, Integer> tableTextLength = new HashMap<String, Integer>();
        System.out.println("Huffman编码表：");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            tableTextLength.put(entry.getKey(), entry.getValue().length());
            tableAndEncodeText.append(entry.getValue());
            huffmanCodeLength += entry.getValue().length();
        }
        StringBuilder encodeText = new StringBuilder();
        for(int i = 0; i < data.length(); i++){
            encodeText.append(map.get(String.valueOf(data.charAt(i))));
        }
        tableAndEncodeText.append(encodeText);
//        System.out.println("待编码数据: " + data);
        System.out.println("待编码数据的二进制结果:" + encodeText.toString());
        int t = encodeText.length() + huffmanCodeLength;
        int x = (byte)(8 - t % 8);
        byte zeroNum = (byte) (x == 8 ? 0 : x);//补0的个数
//        System.out.println("zeroNum: " + zeroNum);
        for(int i = 0; i < zeroNum; i++){
            tableAndEncodeText.append("0");
        }
        int headLength = 2 + map.size()*2; // 头部长度
        int mainBodyLength = tableAndEncodeText.length() / 8; // 主体长度
        ByteBuffer buffer = ByteBuffer.allocate(headLength + mainBodyLength);
        buffer.put(zeroNum);
        buffer.put((byte) map.size());
        int i = 2;
        for( Integer value : tableTextLength.values()){
            buffer.put(value.byteValue());
        }
        for(String key : map.keySet()){
            buffer.put(key.getBytes()[0]);
        }
        for(int j = 0; j < tableAndEncodeText.length(); j += 8){
            String s = tableAndEncodeText.substring(j, j + 8);
            buffer.put((byte) Integer.parseInt(s, 2));
        }
        //print tableAndEncodeText
//        System.out.println("主体文本编码(哈夫曼编码+待编码数据的二进制结果+补齐的0):");
//        for(int j = 0; j < tableAndEncodeText.length(); j++){
//            System.out.print(tableAndEncodeText.charAt(j));
//        }
        //print buffer
        System.out.println();
//        System.out.println("编码后的全部信息(文件头 + 文件主体):");
//        for(int j = 0; j < buffer.array().length; j++){
//            System.out.print(buffer.array()[j] + " ");
//        }
//        System.out.println();
//        System.out.println("总编码长度:"+buffer.array().length);
        return buffer.array();
    }

    public static String bytesToBitString(byte[] zipBytes) {
        byte zeroNum = zipBytes[0];
        StringBuilder decoderesult = new StringBuilder();
        byte len = zipBytes[1]; // 码表长度
//        System.out.println("zeroNum: " + zeroNum);
//        System.out.println("len: " + len);
        Map<Character, String> char_code = new HashMap<>();
        Map<String, Character> code_char = new HashMap<>();
        int i = 2, j = 2 + len, z = 2 + len*2;
        System.out.println(zipBytes.length);
//        System.out.println("z"+z);
        String[] dataArray = new String[zipBytes.length];
        for(int i1 = 0; i1 < zipBytes.length; i1++){
            //8-bits
            int unsignedByte = Byte.toUnsignedInt(zipBytes[i1]);
            String x = Integer.toBinaryString(unsignedByte);
            String y = String.format("%8s", x).replace(' ', '0');
            dataArray[i1] = y;
        }
        String data = Arrays.toString(zipBytes);
        System.out.println("byte形式的总编码结果:\n"+data);
        System.out.println("二进制字符串形式的总编码结果:\n"+Arrays.toString(dataArray));
//        String mainBody = Arrays.toString(Arrays.copyOfRange(zipBytes, 2 + len, zipBytes.length));
        StringBuilder mainBody = new StringBuilder();
        for(int i1 = z; i1 < dataArray.length; i1++){
            mainBody.append(dataArray[i1]);
        }
        System.out.println("主体文本编码:\n"+mainBody);
        String final_res = mainBody.substring(0, mainBody.length() - zeroNum);
        System.out.println("去除增加的0 :\n"+final_res);
        int u = 0; // 用于遍历final_res
        int table_text_len = 0;
        for(; i < 2 + len; i++, j++){
            char c = (char) zipBytes[j];
            int len_code = zipBytes[i];
            int r = z, l = z + len_code;
            table_text_len += len_code;
//            char_code[c] = final_res.substring(u, u + len_code);
            char_code.put(c, final_res.substring(u, u + len_code));
            code_char.put(final_res.substring(u, u + len_code), c);
            u += len_code;
        }
        for(int p = u;u < final_res.length() && p <= final_res.length(); p++){
            if(code_char.containsKey(final_res.substring(u, p))){
//                System.out.println(code_char.get(final_res.substring(u, p)));
                decoderesult.append(code_char.get(final_res.substring(u, p)));
                u = p;
            }
        }
        return decoderesult.toString();
    }
}
