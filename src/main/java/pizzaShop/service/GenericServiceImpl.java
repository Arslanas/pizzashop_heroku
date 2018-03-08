package pizzaShop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public class GenericServiceImpl<T,ID extends Serializable> implements GenericService<T,ID> {

    protected JpaRepository<T,ID> genericRepo;
    protected final Class classInfo;

    protected GenericServiceImpl(Class<T> classInfo, JpaRepository<T,ID> repo){
        this.classInfo = classInfo;
        this.genericRepo = repo;
    }

    @Override
    public long count() {
        return genericRepo.count();
    }

    @Override
    public List<T> findAll() {
        return genericRepo.findAll();
    }

    @Override
    public Page<T> findAll(Pageable var) {
        return genericRepo.findAll(var);
    }

    @Override
    public T findOne(ID id) {
        return genericRepo.findOne(id);
    }

    @Override
    public T save(T t) {
        return genericRepo.save(t);
    }

    @Override
    public void delete(T t) {
        genericRepo.delete(t);
    }
    public void delete(ID id) {
        genericRepo.delete(id);
    }

    @Override
    public boolean exists(ID var1) {
        return genericRepo.exists(var1);
    }
}
