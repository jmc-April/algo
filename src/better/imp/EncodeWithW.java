package better.imp;

import java.io.*;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/17 13:51
 * @注释
 */
public class EncodeWithW {
    private String data;
    private String filePath;
    public String getData() {
        if(filePath != null) {
            File file = new File(filePath);
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                data = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
