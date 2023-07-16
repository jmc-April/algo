package t1;
/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/12 11:01
 * @注释
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node{
    String c;
    int d;
    Node left, right;
    public Node(String c, int d ) {
        this.c = c;
        this.d = d;
        left = right = null;
    }
}
public class BinaryTree {
    Node root;
    String[] code = new String[1000];

    public BinaryTree() {
        root = null;
    }
    public BinaryTree(int key) {
    }
    public void preorder(Node n, String way) {
        if(n != null) {
//            System.out.println(n.d);
            if(n.left == null && n.right == null) {
                System.out.println(n.c + ": " + way);
                code[n.c.charAt(0)] = way; // 保存编码
                return;
            }
            preorder(n.left, way+"0");
            preorder(n.right, way+"1");
        }
    }
    public static int getdepth(Node n) {
        if(n == null) return 0;
        return Math.max(getdepth(n.left), getdepth(n.right))+1;
    }
    public static Node build(String data) {
        if (data.equals("")) return null;
        Node p = null;
        PriorityQueue<Node> q = new PriorityQueue<>((a,b)->(a.d - b.d));
        String[] d = data.split("\n");
        for(String s : d) {
            String[] ss = s.split(":");
            int idx = ss[0].charAt(0)-'0';
            q.add(new Node(ss[0], (int)(Double.parseDouble(ss[1])*100)));
        }
        while(q.size()>1) {
            Node a = q.poll(), b = q.poll();
            int sum = a.d + b.d;
//            System.out.println(a.c+a.d+" "+b.d+b.c+" "+sum);
            p = new Node(a.c+b.c, sum);
            p.left = a; p.right = b;
            q.add(p);
        }
        return p;
    }
    public static void main(String[] args) throws IOException {
        BinaryTree tr = new BinaryTree();
        Scanner in = new Scanner(System.in);
        double res = 0l;
//        String data = "A:7.0\n" + "B:1.0\n" + "c:2.0\n" + "d:4.0\n" +" :5.0\n";
        BufferedReader br = new BufferedReader(new FileReader("D:\\code\\algo_design\\src\\letter-weight.txt"));
        String data = "", line;
        while((line = br.readLine()) != null){
            data += line + "\n";
        }
        System.out.println("data: " + data);
        String input = in.nextLine();
        tr.root = build(data);
        tr.preorder(tr.root, "");
        System.out.println(res);
        String after = "";
        for(int i = 0;i < input.length(); i++) {
           after += tr.code[input.charAt(i)];
        }
        double afterlength = after.length(), beforelength = input.length()*8;
        double ratio = (beforelength - afterlength) / beforelength;
        System.out.println(input);
        System.out.println(after);
        System.out.println(after.length()); System.out.println("压缩率: " + ratio);
        System.out.println(getdepth(tr.root));
//        for(int i = 0;i<tr.code.length; i++){
//            if(tr.code[i] != null) System.out.println((char)i + ": " + tr.code[i]);
//        }
    }
}
