# Ex2_1

This class contains methods for generating and reading text files.

## Method summary

- `createTextFiles(int n, int seed, int bound)` generates `n` text files, where the number of lines in each file is a random number between `1` and `bound`, inclusive. The random number generator is initialized with the `seed` value. The method returns an array of strings containing the names of the generated files.

- `getNumOfLines(String[] fileNames)` reads through an array of file names and returns the total number of lines in all of the files.

- `getNumOfLinesThreads(String[] fileNames)` uses a separate `MyThread` class to count the lines in each file and returns the total number of lines in all of the files.

- `random(int seed, int bound)` generates a random integer between `1` and `bound`, inclusive, using the provided `seed` value to initialize the random number generator.


# Ex2_1
This Java program demonstrates the use of threads in counting the number of lines in a set of text files.

## How to Use
To use this program, simply run the Ex2_1 class. The program will create 100 text files, each with a random number of lines between 1 and 1000. The program will then measure the time it takes to count the number of lines in all of the files using both a single thread and multiple threads. The results will be printed to the console.

## Configuration
The number of text files and the range for the number of lines in each file can be modified by adjusting the arguments passed to the createTextFiles method in the main function.

The seed for the random number generator can also be changed by modifying the seed argument passed to the createTextFiles method.

## Output
The program will output the names of the text files that were created, the total number of lines counted using a single thread, and the total number of lines counted using multiple threads. The program will also output the time it took to count the lines in each case.

# time:
## why is threadpool faster then thread :
1.Thread creation is a relatively expensive operation, as it involves allocating memory for the thread's stack and scheduling the thread with the operating system. By using a thread pool, you can reuse a fixed number of threads for multiple tasks, rather than creating a new thread for each task. This can reduce the overhead of thread creation and make your program more efficient.

2.When a thread is created, it must be scheduled by the operating system to run on a CPU. This can take some time, especially on systems with many threads competing for CPU time. By using a thread pool, you can reduce the number of threads that need to be scheduled, which can improve the responsiveness of your program.

3.The threads in a thread pool can be configured with different priority levels, allowing you to prioritize certain tasks over others. This can be useful if you have a mix of long-running and short-running tasks and you want to ensure that the short-running tasks are completed as quickly as possible.

4.A thread pool can also help to prevent thread starvation, which can occur when a large number of short-running tasks are submitted to a single thread. By using a thread pool, you can ensure that each task has a chance to be executed, even if there are many tasks waiting to be processed.