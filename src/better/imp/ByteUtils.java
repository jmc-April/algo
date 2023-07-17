package better.imp;

import java.nio.ByteBuffer;
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
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            tableTextLength.put(entry.getKey(), entry.getValue().length());
            tableAndEncodeText.append(entry.getValue());
            huffmanCodeLength += entry.getValue().length();
        }
        StringBuilder encodeText = new StringBuilder();
        for(int i = 0; i < data.length(); i++){
            encodeText.append(map.get(String.valueOf(data.charAt(i))));
        }
        tableAndEncodeText.append(encodeText);
        byte[] zipBytes = new byte[1000];
        System.out.println("data: " + data);
        System.out.println("编码:" + encodeText.toString());
        System.out.println(encodeText.length() + " " + huffmanCodeLength);
        int t = encodeText.length() + huffmanCodeLength;
        int x = (byte)(8 - t % 8);
        byte zeroNum = (byte) (x == 8 ? 0 : x);//补0的个数
        System.out.println("zeroNum: " + zeroNum);
        byte theKindOfCode = (byte) (map.size()); //码表长度
        for(int i = 0; i < zeroNum; i++){
            tableAndEncodeText.append("0");
        }
        int headLength = 2 + map.size()*2;
        int mainBodyLength = tableAndEncodeText.length() / 8;
        System.out.println(headLength + mainBodyLength);
        ByteBuffer buffer = ByteBuffer.allocate(headLength + mainBodyLength);

        zipBytes[0] = zeroNum;
        buffer.put(zeroNum);
        zipBytes[1] = theKindOfCode;
        buffer.put((byte) map.size());
        int i = 2;
        for( Integer value : tableTextLength.values()){
            zipBytes[i++] = value.byteValue();
            buffer.put(value.byteValue());
        }
        for(String key : map.keySet()){
            zipBytes[i++] = key.getBytes()[0];
            buffer.put(key.getBytes()[0]);
        }
        for(int j = 0; j < tableAndEncodeText.length(); j += 8){
            String s = tableAndEncodeText.substring(j, j + 8);
            zipBytes[i++] = (byte) Integer.parseInt(s, 2);
            buffer.put((byte) Integer.parseInt(s, 2));
        }
        //print tableAndEncodeText
        System.out.println("print tableAndEncodeText " + tableAndEncodeText.length());
        for(int j = 0; j < tableAndEncodeText.length(); j++){
            System.out.print(tableAndEncodeText.charAt(j));
        }
        //print zipBytes
        System.out.println("\nprint zipBytes");
        for(int j = 0; j < i; j++){
            System.out.print(zipBytes[j] + " ");
        }
        //print buffer
        System.out.println("\nprint buffer");
        for(int j = 0; j < i; j++){
            System.out.print(buffer.get(j) + " ");
        }

        System.out.println();
        System.out.println(buffer.array().length);
        return buffer.array();
    }

    public static String bytesToBitString(byte[] data) {

    }
}
