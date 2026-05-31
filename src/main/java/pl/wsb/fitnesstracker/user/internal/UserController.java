package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    // 1. Создание пользователя (POST /v1/users)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User userEntity = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(userEntity);
        return userMapper.toUserDto(savedUser);
    }

    // 2. Получение ВСЕХ пользователей (GET /v1/users)
    @GetMapping
    public List<UserDto> getUsers() throws InterruptedException {
        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    // 3. Получение упрощенного списка пользователей (GET /v1/users/simple)
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers() {
        return this.userProvider.findAllUsers().stream()
                .map(user -> new UserSimpleDto(user.getFirstName(), user.getLastName()))
                .toList();
    }

    // 4. Получение пользователя по конкретному ID (GET /v1/users/{id})
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // 5. Поиск по строгому совпадению Email (GET /v1/users/email?email=...)
    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(user -> List.of(new UserEmailDto(user.getId(), user.getEmail())))
                .orElse(List.of()); // Если не нашли, возвращаем пустой список
    }

    // 6. Поиск пользователей старше определенной даты (GET /v1/users/older/{time})
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersByAge(@PathVariable LocalDate time) {
        return userService.findUsersBornBefore(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    // 7. Обновление пользователя (PUT /v1/users/{id})
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        return userMapper.toUserDto(updatedUser);
    }

    // 8. Удаление пользователя (DELETE /v1/users/{id})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}