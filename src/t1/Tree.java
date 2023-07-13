package t1;

/**
 * @version 1.0
 * @Author xxx
 * @Date 2023/7/12 21:34
 * @注释
 */
public class Tree {
    public static void main(String[] args) {
        int i, j, k, depth =7;
        for (j = 0; j < depth; j++) {
            int w = 1 << (depth - j + 1);
            if (j == 0) {
                System.out.printf("%" + w + "c\n", '_');
            } else {
                for (i = 0; i < 1 << (j - 1); i++) {
                    System.out.printf("%" + (w + 1) + "c", ' ');
                    for (k = 0; k < w - 3; k++) {
                        System.out.print("_");
                    }
                    System.out.print("/ \\");
                    for (k = 0; k < w - 3; k++) {
                        System.out.print("_");
                    }
                    System.out.printf("%" + (w + 2) + "c", ' ');
                }
                System.out.println();
                for (i = 0; i < 1 << (j - 1); i++) {
                    System.out.printf("%" + w + "c/%" + (w * 2 - 2) + "c_%-" + w + "c", '_', '\\', ' ');
                }
                System.out.println();
            }
            for (i = 0; i < 1 << j; i++) {
                System.out.printf("%" + (w - 1) + "c_)%" + (w - 1) + "c", '(', ' ');
            }
            System.out.println();
        }
        String a = " a";
        String b = a.substring(0,0);
        System.out.println(b.equals(""));
        System.out.println(b.length());
    }
}