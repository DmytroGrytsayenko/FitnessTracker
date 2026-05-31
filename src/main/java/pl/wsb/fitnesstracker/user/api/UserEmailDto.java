package pl.wsb.fitnesstracker.user.api;

// Заметь: мы используем слово record вместо class,
// и параметры пишем прямо в круглых скобках возле названия!
public record UserEmailDto(Long id, String email) {
}