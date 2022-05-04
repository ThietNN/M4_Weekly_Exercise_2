package thietnn.repository;

import org.springframework.data.repository.CrudRepository;
import thietnn.model.Category;

public interface ICategoryRepository extends CrudRepository<Category, Long> {
}
