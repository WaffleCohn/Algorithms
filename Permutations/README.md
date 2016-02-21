#Permutations

For each index of the string or array, recursively add every other index to a prefix that is carried to the end.
First, check to see if the passed string, array, etc. has a length of zero. If so, the permutation is finished.
If this is not true, loop through the remainder of the passed object. For each index, add the corresponding piece of the passed object to the prefix and cut it out of the original object. Finally, recurse through these new values.
