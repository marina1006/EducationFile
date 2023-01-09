package web.service;

import java.util.List;
import web.model.User;

public interface UserService {

    void saveUser(User user);

    List<User> removeUser(Long id);
    List<User> getListUsers();
    public User getUser(Long id);

    public List<User> update(Long id, User user);
}
