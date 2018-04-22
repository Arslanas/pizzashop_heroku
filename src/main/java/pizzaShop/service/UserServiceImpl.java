package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.User;
import pizzaShop.repository.UserRepo;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    ShoppingCartService shoppingCartService;
    UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo repo, ShoppingCartService shoppingCartService) {
        super(User.class, repo);
        this.repo = repo;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public User update(User user) {
        User userDB = findByUsername(user.getUsername());
        if (userDB == null) {
            return super.save(user);
        }
        user.setAuthorities(userDB.getAuthorities());
        return super.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        shoppingCartService.findByUsername(username).forEach(e->shoppingCartService.delete(e.getId()));
        repo.deleteByUsername(username);
    }
}
