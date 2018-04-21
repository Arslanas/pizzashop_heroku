package pizzaShop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T,ID extends Serializable>  {
    long count();
    List<T> findAll();
    Page<T> findAll(Pageable var);
    T findOne(ID id);
    T save(T t);
    void delete(ID id);
    boolean exists(ID var1);
}
