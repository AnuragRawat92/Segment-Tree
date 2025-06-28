🌿 Lazy Propagation in Segment Tree (Java)
This repository demonstrates how to implement Lazy Propagation in a Segment Tree to efficiently support:

Range Updates: Add a value to all elements in a range [l, r]

Range Queries: Get the sum of elements in a range [l, r]

Lazy Propagation is essential when frequent updates are performed on segments of an array while still requiring efficient querying.

📘 Problem Statement
Given an array, implement a data structure that supports:

update(l, r, val) — Add val to all elements in the range [l, r]

query(l, r) — Return the sum of elements in the range [l, r]

🛠️ How Lazy Propagation Works
When an update over a range is requested:

Instead of updating all affected nodes immediately, we mark them as "lazy".

When a node is accessed later (either for update or query), we apply the pending update first and propagate it to its children.

This avoids repeated updates and speeds up operations.

🔍 Code Components
build(int ind, int low, int high, int[] arr)
Builds the segment tree from the original array.

update(int ind, int low, int high, int l, int r, int val)
Adds val to every element in the range [l, r] using lazy propagation.

query(int ind, int low, int high, int l, int r)
Returns the sum in the range [l, r] with lazy propagation applied.

propagate(int ind, int low, int high)
Applies and clears any pending lazy updates for the current node.

🧪 Example
Input
java
Copy
Edit
arr = {1, 3, 5, 7, 9, 11};
update(1, 3, 10);     // add 10 to range [1, 3]
query(1, 3);          // returns 45 = 13 + 15 + 17
query(0, 5);          // returns 76 = total sum after update
Output
Copy
Edit
45
76
📈 Time Complexity
Operation	Time
Build	O(n)
Update	O(log n)
Query	O(log n)

🧠 Why Use Lazy Propagation?
Without lazy propagation:

A range update could cost O(n)

With lazy propagation:

All operations remain O(log n) — fast and scalabl
