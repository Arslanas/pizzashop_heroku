package pizzaShop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public class GenericServiceImpl<T,ID extends Serializable> implements GenericService<T,ID> {

    protected JpaRepository<T,ID> repo;
    protected final Class classInfo;

    protected GenericServiceImpl(Class<T> classInfo, JpaRepository<T,ID> repo){
        this.classInfo = classInfo;
        this.repo = repo;
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<T> findAll(Pageable var) {
        return repo.findAll(var);
    }

    @Override
    public T findOne(ID id) {
        return repo.findOne(id);
    }

    @Override
    public T save(T t) {
        return repo.save(t);
    }

    @Override
    public void delete(T t) {
        repo.delete(t);
    }

    @Override
    public boolean exists(ID var1) {
        return repo.exists(var1);
    }
}
