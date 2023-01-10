import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomExecutor{

    private final ThreadPoolExecutor executor;
    private int maxPriority;

    public CustomExecutor() {
        int numProcessors = Runtime.getRuntime().availableProcessors();
        int numThreads = numProcessors / 2;
        int maxThreads = numProcessors - 1;
        BlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        ThreadFactory threadFactory = new CustomThreadFactory();
        executor = new ThreadPoolExecutor(numThreads, maxThreads, 300, TimeUnit.MILLISECONDS, queue, threadFactory);
    }


    public  <V> Future<V>  submit(Task<V> task) {
        updateMaxPriority(task.getPriority());
        return executor.submit(task.operation);
    }

    public <V> Future<V> submit(Callable<V> task) {
        Task<V> t = Task.createTask(task);
        updateMaxPriority(t.getPriority());
        Future<V> result =  executor.submit(t.operation);
       return result;
    }

    public <V> Future<V> submit(Callable<V> task, TaskType type) {
        Task<V> t = Task.createTask(task,type);
        updateMaxPriority(t.getPriority());
        Future<V> result =  executor.submit(t.operation);
       return result;
    }
  

    public int getMaxPriority() {
        return maxPriority;
    }

    private void updateMaxPriority(int priority) {
        maxPriority = Math.max(maxPriority, priority);
    }

    public void gracefullyTerminate() {
        executor.shutdown();
    }

    private static class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
}
