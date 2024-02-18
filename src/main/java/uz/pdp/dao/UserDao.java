package uz.pdp.dao;

import uz.pdp.model.User;

public interface UserDao extends CrudDao<User, Long> {
    User saveByNamedParameter(User user);
    User saveBySimpleInsert(User user);
    User findByEmail(String email);
}