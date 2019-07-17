### Total Score: 21/36

### Answers Score: 15/26
- Pseudocode (Problem 0): 7/8
- Altered Rep Invs (Problems 1a, 2b, and 2c): 3/6
- Mutability (Problem 1b): 0/2
- checkRep Usage (Problems 1c, 2a, and 3a): 3/6
- RatPoly Design (Problem 3b): 2/4

### Code Style Score: 6/10

#### Specific Feedback

- For 0b, your algorithm should also consider the case where the remaining dividend 
is 0.
- For 1a, missing hashCode and checkRep.
- For 2b, missing hashCode, checkRep and equals.
- For 2c, missing checkRep. 
- For 1b, you need to talk about immutability of the ADT and @modifies.
- For 1c, immutability is guaranteed by the Java compiler through final keyword
and strictly immutable data types.
- For 2a, same as 1c.
- For 3a, Immutability does not dispense with the need for checkReps.  Only immutability
guaranteed by the compiler (through final primitive/immutable fields) allows us
to dispense with them from public methods.  The fact that a method does or does
not mutate anything does not have any bearing on whether checkReps are useful.
- For 3b, missing advantage of using two lists.

#### General Feedback

- Boolean Zen in RatTerm. Instead of checking if (x == true), use if (x).
- In RatTerm, you should not create new NAN objects. Instead, just return the static NAN field.
