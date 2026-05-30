package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 1. Służy do znalezienia jednego, konkretnego użytkownika.
     * Spring Data JPA samo domyśli się jak wygenerować zapytanie SQL.
     */
    Optional<User> findByEmail(String email);

    /**
     * 2. Służy do wyszukiwania wielu użytkowników po fragmencie emaila.
     */
    default List<User> searchByEmailFragment(String emailFragment) {
        String lowerCaseFragment = emailFragment.toLowerCase();
        return findAll().stream()
                .filter(user -> user.getEmail() != null && user.getEmail().toLowerCase().contains(lowerCaseFragment))
                .collect(Collectors.toList());
    }

    /**
     * 3. Wyszukuje użytkowników, którzy są starsi niż podany wiek.
     */
    default List<User> searchUsersOlderThan(int minAge) {
        LocalDate currentDate = LocalDate.now();
        return findAll().stream()
                .filter(user -> user.getBirthdate() != null && Period.between(user.getBirthdate(), currentDate).getYears() > minAge)
                .collect(Collectors.toList());
    }
}