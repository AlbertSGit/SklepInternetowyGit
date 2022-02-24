package skleponline.kod.Pakiety.BazaDane;

import java.util.List;

import skleponline.kod.Pakiety.Elementy.User;

public interface UsersDao {

    public List<User> getUserData();
    public User getUser(int id);
    public void updateUser(int userId, String columnName, String newUpdate);
    public void deleteUser(int userId);
    public void addUser(String name, String password, String userType);
}