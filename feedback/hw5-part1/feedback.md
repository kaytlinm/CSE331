### Total Score: 22/25

### Answers Score: 17/20
- Problem 1a: 4/7

The representation invariant for `IntQueue1` ought to be:
```
entries != null && !entries.contains(null)
```

The abstraction function for `IntQueue2` ought to be:
```
AF(this) = [..., entries[(i + front) % entries.length], ...]
           for front <= i < size
```

The representation invariant for `IntQueue2` ought to be:
```
entries != null && 0 <= front < entries.length && 0 <= size <= entries.length
```

- Problem 1b: 1/1
- Problem 1c: 6/6
- Problem 2: 3/3
- Problem 3: 3/3

### Following Directions Score: 5/5



The below scores are separate:

### Documentation Score: 1/3

You need to mention that no two edges can have the same parent, child and label.
You need to describe what happens when the client tries to remove edges or nodes that don't exist.
Additionally, you need to describe what happens when an existing node or edge is added again.

### Design Score: 3/3

### Testing Score: 1/3

Your test suite is not exhaustive in nature. You need to test for edge cases (like the ones 
mentioned in the feedback above.)

#### Code Review Feedback

None.