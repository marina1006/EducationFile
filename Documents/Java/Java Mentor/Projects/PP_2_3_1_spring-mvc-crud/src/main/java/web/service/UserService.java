package web.service;

import java.util.List;
import web.model.User;

public interface UserService {

    void saveUser(User user);

    void removeUser(long id);
    List<User> getListUsers();
    public User getUser(long id);
}
