import java.util.concurrent.Callable;

public class Task<V> implements Comparable<Task<V>> {

    public final Callable<V> operation;
    private final TaskType type;

    private Task(Callable<V> operation, TaskType type) {
        this.operation = operation;
        this.type = type;
    }

    public static <V> Task<V> createTask(Callable<V> operation) {
        return new Task<>(operation, TaskType.OTHER);
    }

    public static <V> Task<V> createTask(Callable<V> operation, TaskType type) {
        return new Task<>(operation, type);
    }

    public int getPriority(){
        return this.type.getPriorityValue();
    }
  
    @Override
    public int compareTo(Task<V> other) {
        return Integer.compare(this.type.getPriorityValue(), other.type.getPriorityValue());
    }
}
