package pizzaShop.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface GenericDAO<T,ID> {
    List<T> getAll();
    T findById(ID id);
    T makePersistent(T t);
    void makeTransient(T t);
    Long getCount();
}