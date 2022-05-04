package thietnn.repository;

import org.springframework.data.repository.CrudRepository;
import thietnn.model.Book;

public interface IBookRepository extends CrudRepository<Book, Long> {
}
