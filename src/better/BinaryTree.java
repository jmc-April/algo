//package better;
//
///**
// * @version 1.0
// * @Author xxx
// * @Date 2023/7/13 10:53
// * @注释
// */
//import java.io.*;
//import java.util.*;
//import better.imp.Node;
//
//public class BinaryTree {
//    Node root;
//    String[] code = new String[1000];
//    Map<String, String> code_char = new HashMap<>();
//
//    public BinaryTree() {
//        root = null;
//    }
//    public BinaryTree(int key) {
//    }
//    public void preorder(Node n, String way) {
//        if(n != null) {
////            System.out.println(n.d);
//            if(n.left == null && n.right == null) {
//                System.out.println("(pro)"+n.c + ": " + way);
//                code[n.c.charAt(0)] = way; // 保存编码
//                System.out.println(n.c.charAt(0)+"  "+way);
//                code_char.put(way, n.c) ;
//                return;
//            }
//            preorder(n.left, way+"0");
//            preorder(n.right, way+"1");
//        }
//    }
//    public static int getdepth(Node n) {
//        if(n == null) return 0;
//        return Math.max(getdepth(n.left), getdepth(n.right))+1;
//    }
//    public static Node build(String data) {
////        if (data.equals("")) return null;
//        Node p = null;
//        PriorityQueue<Node> q = new PriorityQueue<>((a, b)->(a.d - b.d));
//        String[] d = data.split("\n");
//        for(String s : d) {
//            String[] ss = s.split(":");
//            System.out.println(s);
//            int idx = ss[0].charAt(0);
//            q.add(new Node(ss[0], Integer.parseInt(ss[1])));
//        }
//        while(q.size()>1) {
//            Node a = q.poll(), b = q.poll();
//            int sum = a.d + b.d;
////            System.out.println(a.c+a.d+" "+b.d+b.c+" "+sum);
//            p = new Node(a.c+b.c, sum);
//            p.left = a; p.right = b;
//            q.add(p);
//        }
//        return q.poll();
//    }
//    public static String process_input(String input) {
//        int[] cnt = new int[256];
//        String data = "";
//        boolean[] vis = new boolean[256];
//        for(int i = 0;i <= input.length()-1; i++){
//            cnt[input.charAt(i)]++;
//        }
//        for(int i = 0;i <= input.length()-1; i++){
//            if(!vis[input.charAt(i)]) {
//                vis[input.charAt(i)] = true;
//                int x = cnt[input.charAt(i)];
//                data += input.charAt(i) + ":" + x + "\n";
//            }
//        }
//        return data;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BinaryTree tr = new BinaryTree();
//        Scanner in = new Scanner(System.in);
//        double res = 0l;
////        System.out.println("请输入字符集:");
////        String input = in.nextLine();
//        BufferedReader br = new BufferedReader(new FileReader("D:\\code\\algo_design\\src\\better\\mydata.txt"));
//        String data = "", line;
//        while((line = br.readLine()) != null){
//            data += line;
//        }
//        String data2 = process_input(data);
//        tr.root = build(data2);
//        tr.preorder(tr.root, "");
//        String after = "", after2 = "", before3 = "";
//        for(int i = 0;i < data.length(); i++) {
//            after += tr.code[data.charAt(i)];
//        }
//        System.out.println("输入的字符集为:"+data);
//        System.out.println("字符集的编码结果:"+after);
//        System.out.println("请输入要编码的文本:");
//        String input2 = in.nextLine();
//        System.out.println(input2);
//        System.out.println("请输入要译码的二进制:");
//        String input3 = in.nextLine();
//        System.out.println(input3);
//        double afterlength = after.length(), beforelength = data.length()*8;
//        double ratio = (beforelength - afterlength) / beforelength;
//        for(int i = 0;i < input2.length();i++){
//            after2 += tr.code[input2.charAt(i)];
//        }
//        for(int i = 0,j = 0;j < input3.length();j++){
//            String s = input3.substring(i, j+1);
//            if(tr.code_char.containsKey(s)){
//                before3 += tr.code_char.get(s);
//                i = j+1;
//            }
//        }
//
//        FileWriter fw = new FileWriter("D:\\code\\algo_design\\src\\better\\out.txt", true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write("new line");
//        bw.flush();
//        bw.close();
//        fw.close();
//        System.out.println("输入文本的编码是:"+after2);
//        System.out.println("输入文本的译码是:"+before3);
//        System.out.println(after.length());
//        System.out.println("压缩率: " + ratio);
//        System.out.println(getdepth(tr.root));
//    }
//}
