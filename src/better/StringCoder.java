package better;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:29
 * @注释
 */
public interface StringCoder {
    EncodeResult encode(String data, boolean hasW, String needToEncode);

    String decode(byte[] encodeResult);
    String decode(String binString);
}
