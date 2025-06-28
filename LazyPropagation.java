import java.util.*;

public class Main {
    static int[] seg, lazy;

    // Build segment tree
    static void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }
        int mid = (low + high) / 2;
        build(2 * ind + 1, low, mid, arr);
        build(2 * ind + 2, mid + 1, high, arr);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }

    // Propagate lazy values
    static void propagate(int ind, int low, int high) {
        if (lazy[ind] != 0) {
            seg[ind] += (high - low + 1) * lazy[ind]; // apply lazy
            if (low != high) { // not a leaf
                lazy[2 * ind + 1] += lazy[ind];
                lazy[2 * ind + 2] += lazy[ind];
            }
            lazy[ind] = 0;
        }
    }

    // Range update [l, r] += val
    static void update(int ind, int low, int high, int l, int r, int val) {
        propagate(ind, low, high);
        if (r < low || high < l) return; // no overlap
        if (l <= low && high <= r) { // complete overlap
            lazy[ind] += val;
            propagate(ind, low, high);
            return;
        }
        int mid = (low + high) / 2;
        update(2 * ind + 1, low, mid, l, r, val);
        update(2 * ind + 2, mid + 1, high, l, r, val);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }

    // Range query [l, r]
    static int query(int ind, int low, int high, int l, int r) {
        propagate(ind, low, high);
        if (r < low || high < l) return 0; // no overlap
        if (l <= low && high <= r) return seg[ind]; // complete overlap
        int mid = (low + high) / 2;
        int left = query(2 * ind + 1, low, mid, l, r);
        int right = query(2 * ind + 2, mid + 1, high, l, r);
        return left + right;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        seg = new int[4 * n];
        lazy = new int[4 * n];

        build(0, 0, n - 1, arr);

        // Example: Add 10 to range [1, 3]
        update(0, 0, n - 1, 1, 3, 10);

        // Query sum in range [1, 3] → should reflect the update
        System.out.println(query(0, 0, n - 1, 1, 3)); // 3+10 + 5+10 + 7+10 = 45

        // Query sum in range [0, 5]
        System.out.println(query(0, 0, n - 1, 0, 5)); // total sum
    }
}
