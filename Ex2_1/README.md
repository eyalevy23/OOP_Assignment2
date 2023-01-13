# Ex2_1
This Java program demonstrates the use of threads in counting the number of lines in a set of text files.

## Method summary

- `createTextFiles(int n, int seed, int bound)` generates `n` text files, where the number of lines in each file is a random number between `1` and `bound`, inclusive. The random number generator is initialized with the `seed` value. The method returns an array of strings containing the names of the generated files.

- `getNumOfLines(String[] fileNames)` reads through an array of file names and returns the total number of lines in all of the files.

- `getNumOfLinesThreads(String[] fileNames)` uses a separate `MyThread` class to count the lines in each file and returns the total number of lines in all of the files.

- `public int getNumOfLinesThreadPool(String[] fileNames)` This method uses a thread pool to count the number of lines in multiple files. It creates an ExecutorService with a fixed thread pool of size 25 and creates an array of Future objects. Then, it submits a task for each element in the fileNames array, which is to count the number of lines in the corresponding file. The method waits for all tasks to complete and retrieves the results by calling the get() method of each Future object. Finally, it shuts down the thread pool and returns the total number of lines.

- `random(int seed, int bound)` generates a random integer between `1` and `bound`, inclusive, using the provided `seed` value to initialize the random number generator.


## How to Use
To use this program, simply run the Ex2_1 class. The program will create 100 text files, each with a random number of lines between 1 and 1000. The program will then measure the time it takes to count the number of lines in all of the files using both a single thread and multiple threads or without any threads. The results will be printed to the console.

## Configuration
The number of text files and the range for the number of lines in each file can be modified by adjusting the arguments passed to the createTextFiles method in the main function.

## Output
The program will output the names of the text files that were created, the total number of lines counted using a single thread, and the total number of lines counted using multiple threads. The program will also output the time it took to count the lines in each case.

# times with threads and without:

## why is threadpool faster then thread for short task:
1.Thread creation is a relatively expensive operation, as it involves allocating memory for the thread's stack and scheduling the thread with the operating system. By using a thread pool, you can reuse a fixed number of threads for multiple tasks, rather than creating a new thread for each task. This can reduce the overhead of thread creation and make your program more efficient.

2.When a thread is created, it must be scheduled by the operating system to run on a CPU. This can take some time, especially on systems with many threads competing for CPU time. By using a thread pool, you can reduce the number of threads that need to be scheduled, which can improve the responsiveness of your program.

3.The threads in a thread pool can be configured with different priority levels, allowing you to prioritize certain tasks over others. This can be useful if you have a mix of long-running and short-running tasks and you want to ensure that the short-running tasks are completed as quickly as possible.

4.A thread pool can also help to prevent thread starvation, which can occur when a large number of short-running tasks are submitted to a single thread. By using a thread pool, you can ensure that each task has a chance to be executed, even if there are many tasks waiting to be processed.

## why is thred shorter then reguler task for short task:
thread is shorter for task because it can run a lot of task at the same time meanwhile for loop or while loop can do only one task at the time .
Using threads can allow you to make better use of the processing power of a computer by allowing multiple tasks to be executed concurrently. This can be especially useful when you have tasks that may be blocked waiting for some external resource (such as a file or database), as it allows other tasks to continue executing while the blocked task is waiting.

However, it's important to note that using threads can also add complexity to your program, as you need to manage the concurrent execution of the threads and ensure that they are synchronized correctly to avoid race conditions and other issues.