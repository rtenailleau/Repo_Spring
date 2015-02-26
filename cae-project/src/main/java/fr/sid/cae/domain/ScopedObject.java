package fr.sid.cae.domain;
 
public class ScopedObject<T> implements ScopedValue<T> {
 
    private T value;
     
    public T getValue() {
        return value;
    }
 
    public void setValue(T value) {
        this.value = value;
    }
 
    public boolean isDefined() {
        return value != null;
    }
 
    public void clear() {
        value = null;
    }
}