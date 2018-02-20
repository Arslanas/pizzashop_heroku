package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.User;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {


    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        super(User.class);
    }

    public User findByUsername(String username){
        CriteriaQuery<User> query = em.getCriteriaBuilder().createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate predicate = em.getCriteriaBuilder().equal(root.get("username"), username);
        query.select(root).where(predicate);
        return  em.createQuery(query).getSingleResult();
    };
}
