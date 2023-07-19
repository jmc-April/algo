package better.imp;

import better.EncodeResult;
import better.StringCoder;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/17 10:07
 * @注释
 */
public class FileCoderImp implements better.FileCoder {
    @Override
//    public EncodeResult encode(String filePath) {
//        // 1. 读取文件 2. 转换成字符串 3. 编码 4. 写入文件
//        String data = null;
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
//             BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + ".txt"))) {
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            data = sb.toString();
////            System.out.println(data);
//            StringCoderImp sc = new StringCoderImp();
//            EncodeResult encodeResult = sc.encode(data, false, data);
//            System.out.println(encodeResult.countRatio(data));
//            String base64String = Base64.getEncoder().encodeToString(encodeResult.getZipBytes());
//            bw.write(base64String);
////            for(byte b : encodeResult.getZipBytes()) {
////                bw.write(b+" ");
////            }
//            bw.flush();
//            return encodeResult;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public EncodeResult encode(String filePath) {
        // 1. 读取文件 2. 转换成字符串 3. 编码 4. 写入文件
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + ".txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            StringCoderImp sc = new StringCoderImp();
            EncodeResult encodeResult = sc.encode(data, false, data, false);
            // 将编码后的压缩数据写入文件
            bos.write(encodeResult.getZipBytes());//写入压缩数据,getZipBytes()返回的是byte[]
            bos.flush();
            return encodeResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
//    public String decode(String filePath) throws IOException {
////        String base64String = new String(Files.readAllBytes(Paths.get(filePath)));
////        byte[] bytes = Base64.getDecoder().decode(base64String);
////        StringCoderImp sc = new StringCoderImp();
////        String decode = sc.decode(bytes);
////        return decode;
////            try (FileInputStream fis = new FileInputStream(filePath);
////                 GZIPInputStream gis = new GZIPInputStream(fis);
////                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
////                byte[] buffer = new byte[1024];
////                int read;
////                while ((read = gis.read(buffer)) != -1) {
////                    baos.write(buffer, 0, read);
////                }
////                return baos.toString();
////            }
////        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
////             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
////            byte[] buffer = new byte[1024];
////            int read;
////            while ((read = bis.read(buffer)) != -1) {
////                baos.write(buffer, 0, read);
////            }
////            StringCoderImp sc = new StringCoderImp();
////            String decode = sc.decode(baos.toString());
////            return decode;
////        }
//        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
//             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            byte[] buffer = new byte[1024];
//            int read;
//            while ((read = bis.read(buffer)) != -1) {
//                baos.write(buffer, 0, read);
//            }
//            StringCoderImp sc = new StringCoderImp();
//            String decode = sc.decode(baos.toString());
//            return decode;
//        }
//    }
        public String decode(String filePath) {
            try(FileInputStream fis = new FileInputStream(filePath);
                ByteArrayOutputStream bos = new ByteArrayOutputStream()
            ) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                StringCoderImp sc = new StringCoderImp();
                String decode = sc.decode(bos.toByteArray());
                return decode;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

}
