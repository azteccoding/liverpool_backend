package domain.model;

import java.util.Collection;
import java.util.List;

public interface CrudService<T> {
    void create(T t);
    void update(String id, T t);
    void delete(String id);
    Collection<T> getItem();
    List<T> getAll();
}