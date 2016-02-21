#Binary Search

While the target value has not been found and the loop hasn't terminated:
Find the middle value of the data and check if it is the value you're seaching for.
If it is higher, then restrict the search to the lower half of the data.
If it is lower, then restrict the search to the upper half of the data.
At the end, if no answer is found, it returns -1 to indicate that the answer does not exist.

Alternatively, use Java's Arrays.binarySearch() method that takes in two paramaters: the array to search through and the key to search for.

_Note: the data must already be sorted in alphanumeric order_
