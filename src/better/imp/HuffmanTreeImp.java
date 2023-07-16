package better.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import better.HuffmanTree;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 14:07
 * @注释
 */
public class HuffmanTreeImp implements HuffmanTree {
    @Override
    public Node buildHuffmanTree(String data){
        PriorityQueue<Node> q = getNode(data);
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

    public PriorityQueue<Node> getNode(String data){
        PriorityQueue<Node> q = new PriorityQueue<>((a, b)->(a.getWeight() - b.getWeight()));
        int[] count = new int[256];
        boolean[] st = new boolean[256];
        for(int i = 0; i < data.length(); i++){
            count[data.charAt(i)]++;
        }
        for(int i = 0; i < data.length(); i++){
            if(!st[data.charAt(i)]){
                st[data.charAt(i)] = true;
                q.add(new Node(String.valueOf(data.charAt(i)), count[data.charAt(i)]));
            }
        }
        return q;
    }

}
