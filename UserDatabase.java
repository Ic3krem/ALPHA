package main;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, Boolean> isAdmin = new HashMap<>();
    
    // Initialize with default admin and sample users
    static {
        User admin = new User("000", "admin", "admin123", "Admin User", 30, "Employee");
        users.put("admin", admin);
        isAdmin.put("admin", true);

        // Sample users
        User u1 = new User("001", "john.doe", "password1", "John Doe", 22, "Student");
        User u2 = new User("002", "jane.smith", "password2", "Jane Smith", 28, "Employee");
        User u3 = new User("003", "mike.johnson", "password3", "Mike Johnson", 25, "Alumni");
        users.put(u1.getUsername(), u1);
        users.put(u2.getUsername(), u2);
        users.put(u3.getUsername(), u3);
        isAdmin.put(u1.getUsername(), false);
        isAdmin.put(u2.getUsername(), false);
        isAdmin.put(u3.getUsername(), false);
    }

    public static boolean register(String username, String password, String fullName, int age, String category) {
        if (users.containsKey(username)) {
            return false;
        }
        String userId = String.format("%03d", users.size());
        User user = new User(userId, username, password, fullName, age, category);
        users.put(username, user);
        isAdmin.put(username, false);
        return true;
    }

    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).getPassword().equals(password);
    }
    
    public static boolean isAdmin(String username) {
        return isAdmin.getOrDefault(username, false);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static Map<String, User> getAllUsers() {
        return users;
    }

    public static boolean removeUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            isAdmin.remove(username);
            return true;
        }
        return false;
    }
} 