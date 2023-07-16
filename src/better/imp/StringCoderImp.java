package better.imp;

import java.util.Map;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:14
 * @注释
 */
public class StringCoderImp implements better.StringCoder{
    @Override
    public Map<String, String> encode(String data) {
        HuffmanTreeImp hf = new HuffmanTreeImp();
        Node root = hf.buildHuffmanTree(data);
        Map<String, String> huffmanCode = hf.getHuffmanCode(root);
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < data.length(); i++){
//            sb.append(huffmanCode.get(String.valueOf(data.charAt(i))));
//        }
//        return sb.toString();
        return huffmanCode;
    }

    @Override
    public String decode(String data) {
        return null;
    }
}
