package better.imp;

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
        byte[] zipBytes = new byte[1000];
        StringBuilder table = new StringBuilder();
        int huffmanCodeLength = 0; // 哈夫曼编码长度
        Map<String, Integer> tableTextLength = new HashMap<String, Integer>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            tableTextLength.put(entry.getKey(), entry.getValue().length());
            table.append(entry.getValue());
            huffmanCodeLength += entry.getValue().length();
        }
        System.out.println(table.toString());
        System.out.println(tableTextLength);
        StringBuilder encodeText = new StringBuilder();
        for(int i = 0; i < data.length(); i++){
            encodeText.append(map.get(String.valueOf(data.charAt(i))));
        }
        table.append(encodeText);
        System.out.println(encodeText.toString());
        System.out.println(encodeText.length() + " " + huffmanCodeLength);
        int t = encodeText.length() + huffmanCodeLength;
        int x = t % 8;
        byte zeroNum = (byte) (8 - x);//补0的个数
        byte theKindOfCode = (byte) (map.size()); //码表长度
        for(int i = 0; i < zeroNum; i++){
            table.append("0");
        }
        System.out.println(table);
        System.out.println(table.length());
        zipBytes[0] = zeroNum;
        zipBytes[1] = theKindOfCode;
        int i = 2;
        for( Integer value : tableTextLength.values()){
            zipBytes[i++] = value.byteValue();
        }
        for(String key : map.keySet()){
            zipBytes[i++] = key.getBytes()[0];
        }
        for(int j = 0; j < table.length(); j += 8){
            String s = table.substring(j, j + 8);
            zipBytes[i++] = (byte) Integer.parseInt(s, 2);
        }
        //print zipBytes
        for(int j = 0; j < i; j++){
            System.out.print(zipBytes[j] + " ");
        }
        return zipBytes;
    }

    public static int bytesToInt(byte[] bytes) {
        return (bytes[0] & 0xff) << 24 |
                (bytes[1] & 0xff) << 16 |
                (bytes[2] & 0xff) << 8 |
                bytes[3] & 0xff;
    }
}
