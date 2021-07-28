package com.epam.webch.model.dao.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.connection.ConnectionPool;
import com.epam.webch.model.connection.ProxyConnection;
import com.epam.webch.model.dao.UserDao;
import com.epam.webch.model.dao.query.UserQueryFactory;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.entity.user.UserCredentials;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {
    private static final UserDao instance = new UserDaoImpl();
    private static final Logger logger = LogManager.getLogger();

    //table columns start
    private static final String ID_COLUMN = "id";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    private static final String SALT_COLUMN = "salt";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private static final String BALANCE_COLUMN = "balance";
    private static final String ROLE_COLUMN = "role";
    private static final String STATUS_COLUMN = "status";
    //table columns end

    private UserDaoImpl() {

    }

    public static UserDao getInstance(){
        return instance;
    }

    @Override
    public void createUser(String email, String hashPassHex, String saltHex,
                           String name, int balance, User.UserRole role, User.UserStatus status) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.createUserQuery(email, hashPassHex, saltHex, name,  balance, role, status);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createUser SqlException {}", e);
                throw new DaoException("createUser SqlException "+ e);
            }
        }
    }

    @Override
    public void createUser(String email, String hashPassHex, String saltHex,
                           String name,String surname) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.createUserQuery(email, hashPassHex, saltHex, name, surname);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createUser SqlException {}", e);
                throw new DaoException("createUser SqlException "+ e);
            }
        }
    }
    @Override
    public void createUser(String email, String hashPassHex, String saltHex,
                           String name) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.createUserQuery(email, hashPassHex, saltHex, name);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createUser SqlException {}", e);
                throw new DaoException("createUser SqlException "+ e);
            }
        }
    }

    @Override
    public Optional<User> findUserByEmail(String emailToFind) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.findUserByEmailQuery(emailToFind);
        Optional<User> userFromBase = Optional.empty();
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(ID_COLUMN);
                    String email = resultSet.getString(EMAIL_COLUMN);
                    String name = resultSet.getString(NAME_COLUMN);
                    Optional<String> surname = Optional.of(resultSet.getString(SURNAME_COLUMN));
                    Optional<Integer> balance = Optional.of(resultSet.getInt(BALANCE_COLUMN));
                    String roleString = resultSet.getString(ROLE_COLUMN);
                    String statusString = resultSet.getString(STATUS_COLUMN);
                    User.UserRole role = User.UserRole.valueOf(roleString);
                    User.UserStatus status = User.UserStatus.valueOf(statusString);
                    if (surname.isPresent() && balance.isPresent()) {
                        userFromBase = Optional.of(new User(id, email, name, surname.get(), balance.get(), role, status));
                    } else if (surname.isPresent()) {
                        userFromBase = Optional.of(new User(id, email, name, surname.get(), role, status));
                    } else if (balance.isPresent()) {
                        userFromBase = Optional.of(new User(id, email, name, balance.get(), role, status));
                    } else {
                        userFromBase = Optional.of(new User(id, email,  name, role, status));
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "findUserByEmail SqlException {}", e);
                throw new DaoException("findUserByEmail SqlException "+ e);
            }
        }
        return userFromBase;
    }


    @Override
    public List<Optional<User>> findAllUsers() throws DaoException {
        List<Optional<User>> users = new ArrayList<>();
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.findAllUsersQuery();
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        Optional<User> userFromBase;
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(ID_COLUMN);
                    String email = resultSet.getString(EMAIL_COLUMN);
                    String name = resultSet.getString(NAME_COLUMN);
                    Optional<String> surname = Optional.of(resultSet.getString(SURNAME_COLUMN));
                    Optional<Integer> balance = Optional.of(resultSet.getInt(BALANCE_COLUMN));
                    String roleString = resultSet.getString(ROLE_COLUMN);
                    String statusString = resultSet.getString(STATUS_COLUMN);
                    User.UserRole role = User.UserRole.valueOf(roleString);
                    User.UserStatus status = User.UserStatus.valueOf(statusString);
                    if (surname.isPresent() && balance.isPresent()) {
                        userFromBase = Optional.of(new User(id, email, name, surname.get(), balance.get(), role, status));
                    } else if (surname.isPresent()) {
                        userFromBase = Optional.of(new User(id, email, name, surname.get(), role, status));
                    } else if (balance.isPresent()) {
                        userFromBase = Optional.of(new User(id, email, name, balance.get(), role, status));
                    } else {
                        userFromBase = Optional.of(new User(id, email, name, role, status));
                    }
                    users.add(userFromBase);
                }

            } catch (SQLException e) {
                logger.log(Level.ERROR, "getAllUsers SqlException {}", e);
                throw new DaoException("getAllUsers SqlException " + e);
            }
        }
        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.findUserByIdQuery(id);
        Optional<User> userFromBase = Optional.empty();
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    long userid = resultSet.getLong(ID_COLUMN);
                    String email = resultSet.getString(EMAIL_COLUMN);
                    String name = resultSet.getString(NAME_COLUMN);
                    Optional<String> surname = Optional.of(resultSet.getString(SURNAME_COLUMN));
                    Optional<Integer> balance = Optional.of(resultSet.getInt(BALANCE_COLUMN));
                    String roleString = resultSet.getString(ROLE_COLUMN);
                    String statusString = resultSet.getString(STATUS_COLUMN);
                    User.UserRole role = User.UserRole.valueOf(roleString);
                    User.UserStatus status = User.UserStatus.valueOf(statusString);
                    if (surname.isPresent() && balance.isPresent()) {
                        userFromBase = Optional.of(new User(userid, email, name, surname.get(), balance.get(), role, status));
                    } else if (surname.isPresent()) {
                        userFromBase = Optional.of(new User(userid, email, name, surname.get(), role, status));
                    } else if (balance.isPresent()) {
                        userFromBase = Optional.of(new User(userid, email, name, balance.get(), role, status));
                    } else {
                        userFromBase = Optional.of(new User(userid, email, name, role, status));
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "findUserById SqlException {}", e);
                throw new DaoException("findUserById SqlException " + e);
            }
        }
        return userFromBase;
    }

    @Override
    public boolean deleteUser(Long id) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.deleteUserQuery(id);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deleteUser SqlException {}", e);
                throw new DaoException("deleteUser SqlException "+ e);
            }
        }
        return false;
    }

    @Override
    public void changeUserStatus(String email, User.UserStatus status) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.changeUserStatusQuery(email, status);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeUserStatus SqlException {}", e);
                throw new DaoException("changeUserStatus SqlException " + e);
            }
        }
    }

    @Override
    public void changeUserRole(String email, User.UserRole role) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.changeUserRoleQuery(email, role);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeUserRole SqlException {}", e);
                throw new DaoException("changeUserRole SqlException " + e);
            }
        }
    }

    @Override
    public void changeUserName(String email, String name) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.changeUserNameQuery(email, name);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeUserName SqlException {}", e);
                throw new DaoException("changeUserName SqlException " + e);
            }
        }
    }

    @Override
    public Optional<UserCredentials> findUserCredentialsByEmail(String emailToFind) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.findUserCredentialsQuery(emailToFind);
        Optional<UserCredentials> userCredentials = Optional.empty();
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String hashPassword = resultSet.getString(PASSWORD_COLUMN);
                    String salt = resultSet.getString(SALT_COLUMN);
                    userCredentials = Optional.of(new UserCredentials(hashPassword, salt));
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "findUserCredentialsByEmail SqlException {}", e);
                throw new DaoException("findUserCredentialsByEmail SqlException " + e);
            }
        }
        return userCredentials;
    }

    @Override
    public void changeUserSurname(String email, String surname) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.changeUserSurnameQuery(email, surname);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeUserSurname SqlException {}", e);
                throw new DaoException("changeUserSurname SqlException " + e);
            }
        }
    }

    @Override
    public void changeUserEmail(String email, String newEmail) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.changeUserEmailQuery(email, newEmail);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeUserEmail SqlException {}", e);
                throw new DaoException("changeUserEmail SqlException " + e);
            }
        }
    }

    @Override
    public void changeUserPassword(String email, String password,String salt) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.changeUserPasswordQuery(email, password,salt);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeUserPassword SqlException {}", e);
                throw new DaoException("changeUserPassword SqlException " + e);
            }
        }
    }

    @Override
    public void createUser(String email, String hashPassword, String salt, String name,
                           String surname, int balance, User.UserRole role, User.UserStatus status) throws DaoException {
        UserQueryFactory factory = new UserQueryFactory();
        String query = factory.createUserQuery(email, hashPassword, salt, name, surname, balance, role, status);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createUser SqlException {}", e);
                throw new DaoException("createUser SqlException "+ e);
            }
        }
    }

}
