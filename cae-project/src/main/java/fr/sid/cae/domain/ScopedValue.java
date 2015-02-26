package fr.sid.cae.domain;
 
import java.io.Serializable;
 
public interface ScopedValue<T> extends Serializable {
 
    public T getValue();
     
    public void setValue(T value);
     
    public boolean isDefined();
     
    public void clear();
     
}