import java.util.*;

public class Main {

    static class Pair {
        int o, c, f;

        Pair(int o, int c, int f) {
            this.o = o;
            this.c = c;
            this.f = f;
        }
    }

    static Pair[] seg;

    static Pair merge(Pair left, Pair right) {
        int matched = Math.min(left.o, right.c);
        int f = left.f + right.f + matched;
        int o = left.o + right.o - matched;
        int c = left.c + right.c - matched;
        return new Pair(o, c, f);
    }

    static void build(int ind, int low, int high, String s) {
        if (low == high) {
            if (s.charAt(low) == '(')
                seg[ind] = new Pair(1, 0, 0);
            else
                seg[ind] = new Pair(0, 1, 0);
            return;
        }
        int mid = (low + high) / 2;
        build(2 * ind + 1, low, mid, s);
        build(2 * ind + 2, mid + 1, high, s);
        seg[ind] = merge(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    static Pair query(int ind, int low, int high, int l, int r) {
        if (r < low || high < l) return new Pair(0, 0, 0); // no overlap
        if (l <= low && high <= r) return seg[ind];       // complete overlap

        int mid = (low + high) / 2;
        Pair left = query(2 * ind + 1, low, mid, l, r);
        Pair right = query(2 * ind + 2, mid + 1, high, l, r);
        return merge(left, right); // partial overlap
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        seg = new Pair[4 * n];

        build(0, 0, n - 1, s);

        int q = sc.nextInt();
        while (q-- > 0) {
            int l = sc.nextInt() - 1;
            int r = sc.nextInt() - 1;

            // Each matching pair contributes 2 characters to the balanced string
            System.out.println(2 * query(0, 0, n - 1, l, r).f);
        }
    }
}
