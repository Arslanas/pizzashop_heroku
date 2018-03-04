package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaShop.entity.User;
import pizzaShop.repository.UserRepo;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo repo) {
        super(User.class, repo);
        this.repo = repo;
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
