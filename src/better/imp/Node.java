package better.imp;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/16 13:59
 * @注释
 */
public class Node {
    private String data;
    private int weight;
    private Node left;
    private Node right;
    public Node(){
    }
    public Node(String data, int weight){
        this.data = data;
        this.weight = weight;
    }

    public String getData(){
        return data;
    }
    public int getWeight(){
        return weight;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
}

