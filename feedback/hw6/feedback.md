### Total Score: 3/5
- Documenting Changes: 1/1
- Command Line Application: 2/4

One good input case failed (G'RATH to HAWK); there wasn't a path available.

Finding a path between nonexistent heroes threw a NullPointerException.

Your graph loading time took over 5 minutes; are you disabling expensive
checkReps?

I like the intro to your program.

The below scores are separate:

### Specification Score: 0/3

### Implementation Score: 2/3

### Design Score: 3/3

### Testing Score: 2/3

#### Code Review Feedback

JavaBeans are not ADTs and thus do not need abstraction functions or 
representation invariants. In addition, the fields of a JavaBean can
be null if it is "uninitialized".

MarvelParser parseData specificiation is incomplete.

MarvelPaths does not have any specification.

If a class is not an ADT, you should state so where the abstraction
function and representation invariant would normally be.

There should be more inline comments in method implementations.

Needs more cases for testing.

#### Graded By: Frank Poon
