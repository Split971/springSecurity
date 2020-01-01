package springSecurity.dao;

import springSecurity.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);
}
