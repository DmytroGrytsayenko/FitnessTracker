package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    Optional<User> getUser(Long userId);

    Optional<User> getUserByEmail(String email);

    List<User> findAllUsers();

    void deleteUser(Long userId);

    List<User> searchByEmailFragment(String emailFragment);

    List<User> findUsersBornBefore(LocalDate date);

    User updateUser(Long id, UserDto userDto);

}
