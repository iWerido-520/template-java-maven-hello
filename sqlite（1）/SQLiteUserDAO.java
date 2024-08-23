package org.example.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.AbstractAuthenticatedAction;
import org.example.DatabaseConfig;
import org.example.User;
import org.example.UserDAO;

public class SQLiteUserDAO implements UserDAO {

    private DatabaseConfig config = new SQLiteDatabaseConfig();

    @Override
    public boolean addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.config.getConnection();

            String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().toString()); // 将枚举转换为字符串

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.config.getConnection();

            String updateQuery = "UPDATE users SET password = ?, role = ? WHERE username = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getRole().toString()); // 将枚举转换为字符串
            preparedStatement.setString(3, user.getUsername());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.config.getConnection();

            String deleteQuery = "DELETE FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, username);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = this.config.getConnection();
            String selectQuery = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                String roleStr = resultSet.getString("role");
                AbstractAuthenticatedAction.Role role = AbstractAuthenticatedAction.Role.valueOf(roleStr);
                user.setRole(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = this.config.getConnection();
            String selectQuery = "SELECT * FROM users";
            preparedStatement = connection.prepareStatement(selectQuery);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                String roleStr = resultSet.getString("role");
                AbstractAuthenticatedAction.Role role = AbstractAuthenticatedAction.Role.valueOf(roleStr);
                user.setRole(role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userList;
    }

    @Override
    public boolean createTable(boolean dropExistingTable) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.config.getConnection();
            statement = connection.createStatement();

            if (dropExistingTable) {
                String dropQuery = "DROP TABLE IF EXISTS users";
                statement.executeUpdate(dropQuery);
            }

            String createQuery = "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," + // 添加 UNIQUE 约束
                    "password TEXT NOT NULL," +
                    "role TEXT NOT NULL" +
                    ")";
            statement.executeUpdate(createQuery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("创建用户表时出错：" + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
