#Levenshtein distance

This algorithm is associated with finding how many
changes is needed to transform one string into another
string.

For example: To transform "cat" into "dart", we would
need to replace 'c' with 'd' at index 0 and insert an
'r' at index 2. Hence, the levenstein distance would
be 2.

Alternatively, this could be calculated by using
dynamic programming. By drawing a table of minimum
changes as following:

'''
  cat
 0123
d1123
a2212
r3322
t4432
'''

We see that the answer is in the lower right corner
is two.

The rules of calculating the table are of the following:

1. There is a null character followed by the word by character for the column and row headers.
2. The first row and first column numbers from 0 incrementing by one. You should end with the length of that respective word.
3. Starting from the upper left corner (not filled with a number) start calculating, working from left to right up to down.
4. If the two corresponding characters are equal in a cell, ALWAYS copy the number in the r - 1, c - 1 cell.
5. If the two corresponding characters are NOT equal, take the minimum of the three upper adjacent cells and add one. 
