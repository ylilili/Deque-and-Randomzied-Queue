# Deque-and-Randomzied-Queue

Updated 1 hour ago
Programming Assignment: Deques and Randomized Queues Passed · 100/100 points. Correctness: 45/45 tests passed Memory: 134/134 tests passed Timing: 191/193 tests passed

Timing RandomizedQueue *----------------------------------------------------------- Test 1: make n calls to enqueue() followed by n calls to dequeue(); count calls to StdRandom

n = 10

dequeue() should call StdRandom at most once
number of elementary StdRandom operations = 2
failed enqueueing item 5 of 10
n = 100

dequeue() should call StdRandom at most once
number of elementary StdRandom operations = 2
failed enqueueing item 23 of 100
n = 1000

dequeue() should call StdRandom at most once
number of elementary StdRandom operations = 2
failed enqueueing item 27 of 1000
==> FAILED Test 3: make n calls to enqueue() and iterate over the n items; count calls to StdRandom

n = 10

iteration should call StdRandom() at most once per item
number of items = 10
number of elementary StdRandom() operations = 22
n = 100

iteration should call StdRandom() at most once per item
number of items = 100
number of elementary StdRandom() operations = 372
n = 1000

iteration should call StdRandom() at most once per item
number of items = 1000
number of elementary StdRandom() operations = 6536
==> FAILED
