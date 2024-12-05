package ubx.project.javarts.Exception;

public class NotEnoughWorkers extends RuntimeException {
    public NotEnoughWorkers(String message) {
        super(message);
    }
}
