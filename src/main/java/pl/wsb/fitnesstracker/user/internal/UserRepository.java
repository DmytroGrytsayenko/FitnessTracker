package pl.wsb.fitnesstracker.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.internal.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repozytorium do zarządzania encją User w bazie danych.
 * Wykorzystuje metody domyślne i strumienie (Stream) do zaawansowanego filtrowania.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Wyszukuje użytkowników, których email zawiera podany fragment (bez względu na wielkość liter).
     * @param emailFragment fragment adresu email
     * @return lista pasujących użytkowników
     */
    default List<User> searchByEmailFragment(String emailFragment) {
        String lowerCaseFragment = emailFragment.toLowerCase();
        return findAll().stream()
                .filter(user -> user.getEmail() != null && user.getEmail().toLowerCase().contains(lowerCaseFragment))
                .collect(Collectors.toList());
    }

    /**
     * Wyszukuje użytkowników, którzy są starsi niż podany wiek.
     * @param minAge minimalny wiek użytkownika
     * @return lista użytkowników spełniających kryterium wieku
     */
    default List<User> searchUsersOlderThan(int minAge) {
        LocalDate currentDate = LocalDate.now();
        return findAll().stream()
                .filter(user -> user.getBirthday() != null && Period.between(user.getBirthday(), currentDate).getYears() > minAge)
                .collect(Collectors.toList());
    }
}