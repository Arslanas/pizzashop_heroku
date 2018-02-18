package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {


    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        super(User.class);
    }
}
