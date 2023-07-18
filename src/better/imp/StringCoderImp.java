package better.imp;

import better.EncodeResult;

import java.nio.ByteBuffer;
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
    public EncodeResult encode(String data, boolean hasW, String needToEncode) {
        HuffmanTreeImp hf = new HuffmanTreeImp();
        Node root = hf.buildHuffmanTree(data, hasW);
        hf.setDepth(root);
        System.out.println("depth:"+hf.getDepth());
        hf.show(root);
//        String[][] resA = new String[(int)Math.pow(2, hf.getDepth())][hf.getDepth()*10];
//        hf.printTree(root, 0, 0, resA);
//        for(int i = 0; i < resA.length; i++){
//            int cnt = 0;
//            for(int j = 0; j < resA[i].length; j++){
//                if(resA[i][j] == null){
//                    resA[i][j] = " ";
//                    cnt++;
//                }
//                System.out.print(resA[i][j]);
//            }
//            System.out.println();
//            if (cnt == resA[i].length) break; //
//        }
        Map<String, String> huffmanCode = hf.getHuffmanCode(root);
        byte[] res = ByteUtils.intToBytes(huffmanCode, needToEncode);
        return new EncodeResultImp(res, huffmanCode);
    }

    @Override
    public String decode(byte[] zipBytes) {
        return ByteUtils.bytesToBitString(zipBytes);
    }
    public String decode(String binString){
        System.out.println("binString: \n" + binString);
        ByteBuffer byteBuffer = ByteBuffer.allocate(binString.length() / 8);
        for(int i = 0; i < binString.length(); i += 8){
//            System.out.print(binString.substring(i, i + 8) + " ");
            byteBuffer.put((byte)Integer.parseInt(binString.substring(i, i + 8), 2));
        }
//        System.out.println("zipBytes: \n" + Arrays.toString(zipBytes));
        return ByteUtils.bytesToBitString(byteBuffer.array());
    }
}
