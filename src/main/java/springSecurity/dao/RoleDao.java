package springSecurity.dao;

import springSecurity.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);
}
