package ubx.project.javarts.Model;

/**
 * Interface Subject for the Design pattern Observer
 */
public interface Subject {
    public void addListener(Runnable o);
    public void addErrorListener(Runnable o);
    public void notifyListener();
    public void notifyErrorListener(Exception e);
}
