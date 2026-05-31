package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

// ДОБАВЛЕННЫЕ ИМПОРТЫ:
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        // ИСПРАВЛЕНО: используем findByEmail вместо searchByEmailFragment
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> searchByEmailFragment(String emailFragment) {
        return userRepository.searchByEmailFragment(emailFragment);
    }

    @Override
    public List<User> findUsersBornBefore(final LocalDate date) {
        return userRepository.findUsersBornBefore(date);
    }

    @Override
    public User updateUser(final Long id, final UserDto userDto) {
        // 1. Ищем пользователя в базе (если не нашли - выбрасываем ошибку)
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));

        // 2. Обновляем его данные (вызываем метод, который мы добавили на Шаге 1)
        // ВАЖНО: используем методы userDto.firstName() и т.д., точно как мы делали в маппере
        existingUser.updateUser(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );

        // 3. Сохраняем обновленного пользователя (Spring Data сама поймет, что это UPDATE, а не INSERT)
        return userRepository.save(existingUser);
    }
}