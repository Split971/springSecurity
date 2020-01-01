package springSecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springSecurity.entity.User;
import springSecurity.user.CrmUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
