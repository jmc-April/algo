package better.imp;

import better.EncodeResult;
import better.StringCoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/17 10:07
 * @注释
 */
public class FileCoderImp implements better.FileCoder {
    @Override
    public EncodeResult encode(String filePath) {
        // 1. 读取文件 2. 转换成字符串 3. 编码 4. 写入文件
        String data = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + ".txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            StringCoderImp sc = new StringCoderImp();
            EncodeResult encodeResult = sc.encode(data);
            String base64String = Base64.getEncoder().encodeToString(encodeResult.getZipBytes());
            bw.write(base64String);
            bw.flush();
            return encodeResult;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

        @Override
    public String decode(String filePath) throws IOException {
        String base64String = new String(Files.readAllBytes(Paths.get(filePath)));
        byte[] bytes = Base64.getDecoder().decode(base64String);
        StringCoderImp sc = new StringCoderImp();
        String decode = sc.decode(bytes);
        return decode;
    }
}
