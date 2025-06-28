import java.util.*;

public class Main {

    static int[] seg;

    // Update frequency at position i by +1
    static void update(int ind, int low, int high, int i) {
        if (low == high) {
            seg[ind]++;
            return;
        }
        int mid = (low + high) / 2;
        if (i <= mid) update(2 * ind + 1, low, mid, i);
        else update(2 * ind + 2, mid + 1, high, i);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
    }

    // Query sum in range [l, r]
    static int query(int ind, int low, int high, int l, int r) {
        if (r < low || high < l) return 0;
        if (l <= low && high <= r) return seg[ind];
        int mid = (low + high) / 2;
        return query(2 * ind + 1, low, mid, l, r) +
               query(2 * ind + 2, mid + 1, high, l, r);
    }

    // Coordinate compression
    static int[] compress(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (int val : sorted) {
            if (!map.containsKey(val))
                map.put(val, id++);
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = map.get(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {8, 4, 2, 1}; // Output should be 6 inversions
        int[] compressed = compress(arr);
        int maxVal = Arrays.stream(compressed).max().getAsInt();
        seg = new int[4 * (maxVal + 1)];

        long invCount = 0;
        for (int i = compressed.length - 1; i >= 0; i--) {
            invCount += query(0, 0, maxVal, 0, compressed[i] - 1);
            update(0, 0, maxVal, compressed[i]);
        }

        System.out.println("Inversion Count: " + invCount);
    }
}
