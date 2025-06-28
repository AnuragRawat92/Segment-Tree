import java.util.*;

public class Main {

    static int[] a = {1, 0, 3, 3, 4, 7};
    static int[] seg = new int[4 * a.length]; // Enough space for segment tree

    public static void f(int i, int l, int r) {
        if (l == r) {
            seg[i] = a[l];
            return;
        }
        int m = (l + r) / 2;
        f(2 * i + 1, l, m);
        f(2 * i + 2, m + 1, r);
        seg[i] = Math.min(seg[2 * i + 1], seg[2 * i + 2]);
    }

    public static int query(int ind, int low, int high, int l, int r) {
        // no overlao with range l,range
        if (r < low || high < l) return Integer.MAX_VALUE;

        // complete overlap
        if (l <= low && high <= r) return seg[ind];

        // partial overlap of node withrange [l,r]
        //left node call right node call
        // returb (leftnode+right node)
        int mid = (low + high) / 2;
        int leftnode = query(2 * ind + 1, low, mid, l, r);
        int rightnode = query(2 * ind + 2, mid + 1, high, l, r);
        return Math.min(leftnode, rightnode);
    }

    public static void update(int ind, int low, int high, int i, int val) {
        // point update 
        if (low == high) {
            seg[ind] = val;
            a[i] = val;
            return;
        }
        int mid = (low + high) / 2;
        if (i <= mid) update(2 * ind + 1, low, mid, i, val);
        else update(2 * ind + 2, mid + 1, high, i, val);
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    public static void main(String[] args) {
        f(0, 0, a.length - 1);

        // System.out.println("Segment Tree:");
        // for (int i = 0; i < 2 * a.length; i++) {
        //     System.out.print(seg[i] + " ");
        // }

        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while (q-- > 0) {
            // query given type 1 and type 2
            // type 1 min(l,r)
            // type 2 givem ind ,val
            // a[ind]=val
            int type = sc.nextInt();
            if (type == 1) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(0, 0, a.length - 1, l, r));
            } else if (type == 2) {
                int i = sc.nextInt();
                int val = sc.nextInt();
                update(0, 0, a.length - 1, i, val);
            }
        }
    }
}
