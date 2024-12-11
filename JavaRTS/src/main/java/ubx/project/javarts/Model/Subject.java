package ubx.project.javarts.Model;

/**
 * Interface Subject for the Design pattern Observer
 */
public interface Subject {
    public void addObserver(Runnable o);
    public void addErrorListener(Runnable o);
    public void removeObserver(Runnable o);
    public void notifyObservers();
    public void notifyErrorListener(Exception e);
}
