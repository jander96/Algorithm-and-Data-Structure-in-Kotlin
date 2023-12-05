# Tree
![](https://scaler-topics-articles-md.s3.us-west-2.amazonaws.com/tree-data-structure-terminologies.webp)

The tree is a data structure of profound importance. It’s used to tackle many
recurring challenges in software development, such as:
- Representing hierarchical relationships.
-  Managing sorted data.
- Facilitating fast lookup operations.

#### Node
Like the linked list, trees are made up of nodes.
Each node encapsulates some data and keeps track of its children.

#### Parent and child
Trees are viewed starting from the top and branching toward the bottom — just like a
real tree, only upside-down.
Every node, except for the first one, is connected to a single node above, which is
referred to as a parent node. The nodes directly below and connected to the parent
node are known as child nodes. In a tree, every child has exactly one parent. That’s
what makes a tree, well, a tree

#### Root 
The topmost node in the tree is called the root of the tree. It’s the only node that has
no parent

#### Leaf 
A node that has no children is called a leaf:

#### Depth-first traversal
Depth-first traversal starts at the root node and explores the tree as far as possible
along each branch before reaching a leaf and then backtracking.

#### Level-order traversal
Level-order traversal is a technique that visits each node of the tree based on the
depth of the nodes. Starting at the root, every node on a level is visited before going
to a lower level.