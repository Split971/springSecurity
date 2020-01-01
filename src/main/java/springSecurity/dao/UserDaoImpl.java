package springSecurity.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springSecurity.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUserName(String name) {

        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("from User where userName=:uName", User.class);
        query.setParameter("uName", name);

        User user = null;

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        return user;
    }

    @Override
    public void save(User user) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
    }
}
