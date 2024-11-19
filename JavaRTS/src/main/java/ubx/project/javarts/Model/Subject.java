package ubx.project.javarts.Model;

import ubx.project.javarts.View.Observer;

public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
