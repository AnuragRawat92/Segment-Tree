📚 Segment Tree in Java — Min Query & Point Update
This Java implementation demonstrates how to use a Segment Tree to efficiently support:

Range Minimum Query (RMQ) — find the minimum value in a subarray

Point Update — update a single index with a new value

💡 Key Concepts
✅ Build Tree
java
Copy
Edit
f(ind, low, high);
Recursively builds the tree:

Leaf: store original array value

Internal: store min(left, right)

✅ Query
java
Copy
Edit
query(ind, low, high, l, r);
Find minimum value in range [l, r] with 3 overlap cases:

No Overlap:
If [l, r] does not intersect with [low, high]
→ return Integer.MAX_VALUE

Complete Overlap:
If [low, high] is fully within [l, r]
→ return seg[ind]

Partial Overlap:
If ranges partially overlap
→ recurse left & right, return min(left, right)

✅ Update
java
Copy
Edit
update(ind, low, high, i, val);
Point update: change a[i] to val, and update the segment tree accordingly.

🧪 Input Format
Supports two types of queries:

Type 1 — Minimum in range [l, r]

Copy
Edit
1 l r
Type 2 — Update index i to val

css
Copy
Edit
2 i val
🧾 Sample Input
Copy
Edit
6
1 1 4
1 2 5
2 3 0
1 1 4
2 0 2
1 0 2
✅ Sample Output
Copy
Edit
0
0
0
🛠️ Example Code Snippet
java
Copy
Edit
if (r < low || high < l) return Integer.MAX_VALUE;       // No overlap
if (l <= low && high <= r) return seg[ind];              // Complete overlap
// Partial overlap
int mid = (low + high) / 2;
return Math.min(query(...left...), query(...right...));
⚙️ Time Complexity
Build: O(n)

Query / Update: O(log n)
