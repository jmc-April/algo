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
    public Map<String, String> getHuffmanCode() {
        return huffmanCode;
    }
}
