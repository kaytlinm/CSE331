### Total Score: 19/25

### Answers Score: 12/15
- Fibonacci: 4/5
6b.`testBaseCase` fails for the same reason that `testThrowsIllegalArgumentException` does.
- Ball: 1/1
- BallContainer: 2/4
Only noted advantages of one of the approaches.
- Box: 5/5

### Style Score: 7/10
1. Your implementation of getBallsFromSmallest is overly complicated. this is where `Comparator` becomes helpful.  Try implementing `getBallsFromSmallest` again, but this time leave the interface for `Ball` unchanged, and use a `Comparator' to define an ordering instead.

2. Your BallContainer add/remove methods are more complicated than they need to be. Look at the documentation for Set.add and Set.remove and see whether you need to explicitly handle cases the cases of adding something that already exists in the set and removing something that doesn't exist in the set.