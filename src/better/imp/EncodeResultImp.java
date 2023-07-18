package better.imp;

import better.EncodeResult;

import java.util.Map;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 19:31
 * @注释
 */
public class EncodeResultImp implements EncodeResult {
    private byte[] zipBytes;
    private int ratio;
    private Map<String, String> huffmanCode;

    public EncodeResultImp(byte[] zipBytes, Map<String, String> huffmanCode) {
        this.zipBytes = zipBytes;
        this.huffmanCode = huffmanCode;
    }

    @Override
    public byte[] getZipBytes() {
        return zipBytes;
    }

    @Override
    public String getBinString() { // byte数组转化为8位的二进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : zipBytes) {
            String x = Integer.toBinaryString(b & 0xFF);// b&0xFF将byte转化为int，防止丢失高位
            String y = String.format("%08d", Integer.parseInt(x));
            System.out.print(y + " ");
            sb.append(y);
        }
        System.out.println();
        return sb.toString();
    }

    @Override
    public String countRatio(String data) {
        EncodeResult res = new EncodeResultImp(zipBytes, huffmanCode);
        int after_len = res.getZipBytes().length;
        int before_len = data.length() * 8;
        System.out.println("压缩后的长度：" + after_len);
        System.out.println("压缩前的长度：" + before_len);
        double ratio = (double)(before_len - after_len)/ before_len;
        return "压缩率：" + ratio * 100 + "%";
    }

    @Override
    public Map<String, String> getHuffmanCode() {
        return huffmanCode;
    }

}
