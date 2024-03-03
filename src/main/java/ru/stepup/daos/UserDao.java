package ru.stepup.daos;

import org.springframework.stereotype.Component;
import ru.stepup.configurations.DataSource;
import ru.stepup.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao extends BaseDao<User, Long> {
    public static final String SELECT_ALL_USERS = "select * from public.users";
    public static final String SELECT_USER_BY_ID = "select * from public.users u where u.id = ";
    public static final String UPDATE_USER_BY_ID = "update public.users u set username = '%s' where u.id = $s";
    public static final String DELETE_USER_BY_ID = "delete from public.users u where u.id = ";
    public static final String INSERT_USER = "insert into public.users values ('%s')";

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<User> getAll() {
        List<User> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SELECT_ALL_USERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("username"));
                lst.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return lst;
    }

    @Override
    public Optional<User> getEntityById(Long id) {
        PreparedStatement ps = getPrepareStatement(SELECT_USER_BY_ID + id);
        User user = null;
        try {
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = new User(rs.getLong("id"), rs.getString("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public boolean update(User user) {
        PreparedStatement ps = getPrepareStatement(String.format(UPDATE_USER_BY_ID, user.getUsername(), user.getId()));
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(ps);
        }

        return true;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement ps = getPrepareStatement(DELETE_USER_BY_ID + id);

        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(ps);
        }

        return true;
    }

    @Override
    public boolean create(User user) {
        PreparedStatement ps = getPrepareStatement(String.format(INSERT_USER, user.getUsername()));
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePrepareStatement(ps);
        }

        return true;
    }
}
