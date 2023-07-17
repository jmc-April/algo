package better.imp;

import better.EncodeResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:14
 * @注释
 */
public class StringCoderImp implements better.StringCoder{
    @Override
    public EncodeResult encode(String data) {
        HuffmanTreeImp hf = new HuffmanTreeImp();
        Node root = hf.buildHuffmanTree(data);
        Map<String, String> huffmanCode = hf.getHuffmanCode(root);
        byte[] res = ByteUtils.intToBytes(huffmanCode, data);
        return new EncodeResultImp(res, huffmanCode);
    }

    @Override
    public String decode(byte[] zipBytes) {
        byte zeroNum = zipBytes[0];
        StringBuilder decoderesult = new StringBuilder();
        byte len = zipBytes[1]; // 码表长度
        System.out.println("zeroNum: " + zeroNum);
        System.out.println("len: " + len);
//        String[] char_code = new String[256];
        Map<Character, String> char_code = new HashMap<>();
        Map<String, Character> code_char = new HashMap<>();
        int i = 2, j = 2 + len, z = 2 + len*2;
        System.out.println(zipBytes.length);
        System.out.println("z"+z);
        String[] dataArray = new String[zipBytes.length];
        for(int i1 = 0; i1 < zipBytes.length; i1++){
            //8-bits
            int unsignedByte = Byte.toUnsignedInt(zipBytes[i1]);
            String x = Integer.toBinaryString(unsignedByte);
            String y = String.format("%8s", x).replace(' ', '0');
            dataArray[i1] = y;
        }
        String data = Arrays.toString(zipBytes);
        System.out.println(data);
        System.out.println(Arrays.toString(dataArray));
//        String mainBody = Arrays.toString(Arrays.copyOfRange(zipBytes, 2 + len, zipBytes.length));
        StringBuilder mainBody = new StringBuilder();
        for(int i1 = z; i1 < dataArray.length; i1++){
            mainBody.append(dataArray[i1]);
        }
        System.out.println("mainBody  :"+mainBody);
        String final_res = mainBody.substring(0, mainBody.length() - zeroNum);
        System.out.println("final_res :"+final_res);

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
            System.out.println(c+"     "+len_code);
            System.out.println(zipBytes[i]);
        }
        System.out.println("u" + u);
        for(int p = u;u < final_res.length() && p <= final_res.length(); p++){
            if(code_char.containsKey(final_res.substring(u, p))){
                System.out.println(code_char.get(final_res.substring(u, p)));
                decoderesult.append(code_char.get(final_res.substring(u, p)));
                u = p;
            }
        }
        return decoderesult.toString();
    }
}
