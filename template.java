import java.util.*;

public class Main {

    static class SegmentTree {
        int[] seg;
        int[] arr;
        int n;

        SegmentTree(int[] input) {
            this.arr = input;
            this.n = input.length;
            this.seg = new int[4 * n];
            build(0, 0, n - 1);
        }

        void build(int ind, int low, int high) {
            if (low == high) {
                seg[ind] = arr[low];
                return;
            }
            int mid = (low + high) / 2;
            build(2 * ind + 1, low, mid);
            build(2 * ind + 2, mid + 1, high);
            seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
        }

        int query(int ind, int low, int high, int l, int r) {
            if (r < low || high < l) return Integer.MAX_VALUE;
            if (l <= low && high <= r) return seg[ind];
            int mid = (low + high) / 2;
            int left = query(2 * ind + 1, low, mid, l, r);
            int right = query(2 * ind + 2, mid + 1, high, l, r);
            return Math.min(left, right);
        }

        void update(int ind, int low, int high, int i, int val) {
            if (low == high) {
                seg[ind] = val;
                arr[i] = val;
                return;
            }
            int mid = (low + high) / 2;
            if (i <= mid) update(2 * ind + 1, low, mid, i, val);
            else update(2 * ind + 2, mid + 1, high, i, val);
            seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array1 = {1, 3, 5, 7, 9, 11};
        int[] array2 = {2, 4, 6, 8, 10, 12};
        SegmentTree st1 = new SegmentTree(array1);
        SegmentTree st2 = new SegmentTree(array2);

        int q = Integer.parseInt(sc.nextLine());

        while (q-- > 0 && sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) continue;

            String[] parts = line.trim().split("\\s+");
            int type = Integer.parseInt(parts[0]);

            if (type == 1 && parts.length >= 5) {
                int l1 = Integer.parseInt(parts[1]);
                int r1 = Integer.parseInt(parts[2]);
                int l2 = Integer.parseInt(parts[3]);
                int r2 = Integer.parseInt(parts[4]);

                int min1 = st1.query(0, 0, st1.n - 1, l1, r1);
                int min2 = st2.query(0, 0, st2.n - 1, l2, r2);
                System.out.println("Min: " + Math.min(min1, min2));

            } else if (type == 2 && parts.length >= 3) {
                int i = Integer.parseInt(parts[1]);
                int val = Integer.parseInt(parts[2]);
                st1.update(0, 0, st1.n - 1, i, val);

            } else if (type == 3 && parts.length >= 3) {
                int i = Integer.parseInt(parts[1]);
                int val = Integer.parseInt(parts[2]);
                st2.update(0, 0, st2.n - 1, i, val);
            }
        }
    }
}
