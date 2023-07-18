package better.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.jar.JarEntry;

import better.HuffmanTree;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:07
 * @注释
 */
public class HuffmanTreeImp implements HuffmanTree {
    private int depth = 0;
    @Override
    public Node buildHuffmanTree(String data, boolean hasW){
        PriorityQueue<Node> q = getNode(data, hasW);
        while(q.size() > 1){
            Node left = q.poll();
            Node right = q.poll();
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            q.add(parent);
        }
        return q.poll();
    }

    @Override
    public Map<String, String> getHuffmanCode(Node root) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        getHuffmanCode(root, "", map, map1);
        return map;
    }

    public int printTreeVertically(Node root, int row, int col, String[][] res) {
        /** 返回值row表示的是当前节点所在的行数, 这个row将会一直增大
         * 打印思想: 递归打印左子树, 返回row, 然后打印右子树, 返回row
         *
         *
         */
        if(root == null){
            return row;
        }
//        setDepth(root);
        if(root.getData() == null) res[row][col] = "+"; //表示非叶子节点
        else res[row][col] = root.getData(); //表示叶子节点
        if(root.getLeft() != null){
            for(int i = row + 1; i <= row + 2; i++){
                res[i][col] = "|";
            }
            for(int i = col + 1; i <= col + 4; i++){
                res[row + 2][i] = "_";
            }
            row = Math.max(row, printTreeVertically(root.getLeft(), row + 2, col + 4, res));
        }
        if(root.getRight() != null){
            for(int i = row + 1; i <= row + 2; i++){
                res[i][col] = "|";
            }
            /**
             * 从当前row 向上打印"|", 直到遇到第一个不为空的字符串,
             * 不然无法从当前子树的根节点连接到右节点
             */
            for(int i = row; i >= 0; i--){
                if(res[i][col] != null) break;
                else res[i][col] = "|";
            }
            for(int i = col + 1; i <= col + 4; i++){
                res[row + 2][i] = "_";
            }
            row = Math.max(row, printTreeVertically(root.getRight(), row + 2, col + 4, res));
        }
        return row;
    }
    @Override
    public int printTreeHorizontally(Node root, int row, int col,String[][] res) {
        /** 返回值row表示的是当前节点所在的行数, 这个row将会一直增大
         * 打印思想: 递归打印左子树, 返回row, 然后打印右子树, 返回row
         *
         *
         */
        if(root == null){
            return col;
        }
//        setDepth(root);
        if(root.getData() == null) res[row][col] = "+"; //表示非叶子节点
        else res[row][col] = root.getData(); //表示叶子节点
        if(root.getLeft() != null){
            for(int i = col + 1; i <= col + 4 ;i++){
                res[row][i] = "_";
            }
            for(int i = row + 1; i <= row + 2; i++){
                res[i][col+4] = "|";
            }
            col = Math.max(col, printTreeHorizontally(root.getLeft(), row + 2, col + 4, res));
        }
        if(root.getRight() != null){
            for(int i = col + 1; i <= col + 4; i++){
                res[row][i] = "_";
            }
            /**
             * 从当前row 向上打印"|", 直到遇到第一个不为空的字符串,
             * 不然无法从当前子树的根节点连接到右节点
             */
            for(int i = col; i >= 0; i--){
                if(res[row][i] != null) break;
                else res[row][i] = "_";
            }
            for(int i = row + 1; i <= row + 2; i++){
                res[i][col + 4] = "|";
            }
            col = Math.max(col, printTreeHorizontally(root.getRight(), row + 2, col + 4, res));
        }
        return col;
    }
    public void show(Node root){
//        String[][] resA = new String[(int)Math.pow(2, depth)][depth*10];
        String[][] resA = new String[depth*10][(int)Math.pow(2, depth)*10];
        printTreeHorizontally(root, 0, 0, resA);
        for(int i = 0; i < resA.length; i++){
            int cnt = 0;
            for(int j = 0; j < resA[i].length; j++){
                if(resA[i][j] == null){
                    resA[i][j] = " ";
                    cnt++;
                }
                System.out.print(resA[i][j]);
            }
            System.out.println();
            if (cnt == resA[i].length) break; //
        }
    }

    private void getHuffmanCode(Node root, String way, Map<String, String> map, Map<String, String> map1){
        if(root == null){
            return;
        }
        if(root.getLeft() == null && root.getRight() == null){
            map.put(root.getData(), way);
            map1.put(way, root.getData());
        }
        getHuffmanCode(root.getLeft(),way + "0", map, map1);
        getHuffmanCode(root.getRight(),way + "1", map, map1);
    }

    public PriorityQueue<Node> getNode(String data, boolean hasW){
        PriorityQueue<Node> q = new PriorityQueue<>((a, b)->(a.getWeight() - b.getWeight()));
        int[] count = new int[256];
        boolean[] st = new boolean[256];
        if(!hasW){
            for(int i = 0; i < data.length(); i++){
                count[data.charAt(i)]++;
            }
            for(int i = 0; i < data.length(); i++){
                if(!st[data.charAt(i)]){
                    st[data.charAt(i)] = true;
                    q.add(new Node(String.valueOf(data.charAt(i)), count[data.charAt(i)]));
                }
            }
        }
        else {
            String[] d = data.split("\n");
            for(String s : d) {
                String[] ss = s.split(":");
                q.add(new Node(ss[0], (int) Double.parseDouble(ss[1])*100));
            }
        }
        return q;
    }
    public int getDepth(Node root){
        if(root == null){
            return 0;
        }
        return Math.max(getDepth(root.getLeft()), getDepth(root.getRight())) + 1;
    }
    public void setDepth(Node root){
        this.depth = getDepth(root);
    }
    public int getDepth(){
        return this.depth;
    }

}
