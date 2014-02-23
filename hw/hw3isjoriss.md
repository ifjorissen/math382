-----------
-----------
MATH    382
Spring 2014
Homework  3
Due:2/24/14
-----------
-----------

1. (Companion to Lab 2, part I)

The "i-th order statistic" of a collection of n values
       a_0 < a_1 < ... a_(n-1)
is the i-th smallest value (where the 0-th smallest is just the minimum
value.  In other words, it is just the value a_i.  Devise an algorithm 
SELECT(i,A) for the following:

  Given: an i and a collection of values stored in an array A[0..n-1],
  Find: the i-th order statistic of the elements of A.
  
Your algorithm should not assume that A is sorted, nor should it sort
A.  You should instead give a divide-and-conquer algorithm, one thatâ€™s
very similar to QUICKSORT.  It should PARTITION around a value, and
recursively SELECT from the appropriate portion of the partitioned
array.

a) Give the pseudocode for the algorithm.

```
	
```

b) What is the running time of the algorithm, as a function of n,
in the case where PARTITION always divides the array elements into
equal-sized groups? What is the running time if the partitions are
always poor?

2. (Companion to Lab 2, part II)

Analyze the runtime of THREE-QUICKSORT.  You may assume that
THREE-PARTITION divides the array into three portions of the same
size.  Give a recurrence for the running time (say, accounting for the
number of comparisons made) and then solve the recurrence.

3. Consider the following sorting algorithm:

TWO-THIRDS-SORT(A,p,r):
----------------------
1 if A[p] > A[r] then
2   SWAP(A,p,r)
3 if p - r <= 1 then return
4 else
5   k := floor((r-p+1)/3)
6   TWO-THIRDS-SORT(A,p,r-k)
7   TWO-THIRDS-SORT(A,p+k,r)
8   TWO-THIRDS-SORT(A,p,r-k)

a) Give a recurrence for the number of times that line 1 of
   TWO-THIRDS-SORT executes when run on an array A[1..n], that is,
   when TWO-THIRDS-SORT(A,1,n) is called on such an array A.

b) Solve the recurrence and give bounds on the number of times line 1
   is executed, as a function of n.  Hint: your upper bound should be
   some polynomial in n.

c) Where does this algorithm sit, in terms of running time, relative
   to the sorting algorithms we've studied so far?

4. The Nozama Corporation has recently developed a data center for
handling all the web traffic for its customer transactions, where
each transaction involves finding an "e-needle" in an "e-haystack".
Here's how it works:

Any customer request, say, of a haystack of size n, gets sent
to a randomly chosen machine within the data center.  When it
receives a haystack of size n, it divides that haystack into
sqrt(n) stacks each of size sqrt(n) and sends those sqrt(n) smaller
requests to sqrt(n) inactive machines.  When each of those machines
receives such a request, it does the same division of labor.  That
is, it divides that sqrt(n)-sized haystack into sqrt(sqrt(n))
stacks of size sqrt(sqrt(n)) and sends those requests to
sqrt(sqrt(n)) inactive machines.  This "divide and conquer" request
management scheme terminates when a machine receives an e-haystack
of size 2, and the machine with the 2-pile containing the e-needle
responds to the customer.

a) Assuming that the time divide up the request and forward it to
some number of machines always takes 1 millisecond, and that a
machine facing an e-needle-containing e-haystack of size two and
reply to the customer in 1 millisecond, how many miliseconds does
the data center take to reply to a request of size n?  Give a
recurrence and solve the recurrence.  You can choose a family of
sizes that is convenient for your recurrence.

b) How many machines get enlisted to tackle a request of size n?

5. CLRS exercise 7-4