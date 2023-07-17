package better;

import java.io.IOException;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:31
 * @注释
 */
public interface FileCoder {

    EncodeResult encode(String filePath);

    String decode(String filePath) throws IOException;
}
