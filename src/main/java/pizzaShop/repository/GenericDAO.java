package pizzaShop.repository;

import java.util.List;

interface GenericDAO<T,ID> {
    List<T> getAll();
    T getByID(ID id);
    T makePersistent(T t);
    void makeTransient(T t);
    Long getCount();
}