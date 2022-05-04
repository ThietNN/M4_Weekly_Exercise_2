package thietnn.service;

import java.util.Optional;

public interface IService<T> {
        Iterable<T> findAll();
        Optional<T> findById(long id);
        T save (T t);
        void remove(long id);
}
