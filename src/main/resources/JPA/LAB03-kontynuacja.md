Laboratorium III — część 2 (LAB03-2): encja Achievement — stan na 2026-05-08

**Uwaga — to mini-laboratorium dopinające LAB03.** Jeśli na poprzednich
zajęciach nie skończyliście całego LAB03, **najpierw doczytajcie LAB03**
(encje, repozytorium, zapytania) i dopiero wracajcie tutaj. Jeśli LAB03 macie
zrobione — to LAB03-2 jest **krótką dokładką** (~30–40 min), która rozszerza
model o jeszcze jedną encję: `Achievement`.

Plan zajęć:

1. Dodanie encji `Achievement` do modelu (~15 min).
2. Dopisanie dwóch testów dla tabeli `achievement` do istniejącego
   `Lab03EntitiesTest` (~10 min).
3. Commit, push, weryfikacja na GitHub Actions (~10 min).

---

## 0. Przygotowanie

1. Zsynchronizuj swój projekt z projektem prowadzącego (Sync Fork / Pull).
2. Upewnij się, że poprzednie laboratoria są zielone lokalnie (`clean install`
   z **wbudowanego w IntelliJ widoku Maven** — panel *Maven* po prawej stronie
   IDE → `Lifecycle` → dwuklik na `clean`, potem `install`) oraz na GitHubie
   (zakładka Actions).

---

## 1. Encja `Achievement`

W LAB03 mieliśmy trzy nowe encje (`Event`, `UserEvent`, `WorkoutSession`).
Dziś dokładamy **czwartą** — odznakę zdobywaną przez użytkownika:

| Encja         | Po co?                                                                       |
|---------------|------------------------------------------------------------------------------|
| `Achievement` | Osiągnięcie zdobyte przez użytkownika (np. „Pierwsze 10 km") — `name`, `earnedAt`. |

**Relacja:** `Achievement` → `User`: `@ManyToOne` (jeden użytkownik może
zdobyć wiele osiągnięć; jedno osiągnięcie należy do jednego użytkownika).

> **Wskazówka:** pakiet `pl.wsb.fitnesstracker.achievement` już istnieje, ale
> jest pusty (samo `package-info.java`). Utwórz w nim klasę `Achievement` od
> zera: `@Entity`, `@Id` typu `Long` + `@GeneratedValue`, pola `name` (String),
> `earnedAt` (`LocalDateTime`) oraz relacja `@ManyToOne` do `User`.

Szkielet (do uzupełnienia samodzielnie):

```java
package pl.wsb.fitnesstracker.achievement;

import jakarta.persistence.*;
import pl.wsb.fitnesstracker.user.api.User;
import java.time.LocalDateTime;

@Entity
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "earned_at")
    private LocalDateTime earnedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Achievement() {
    }

    public Achievement(String name, LocalDateTime earnedAt, User user) {
        this.name = name;
        this.earnedAt = earnedAt;
        this.user = user;
    }

    // gettery — np. przez Lombok @Getter, jeśli używaliście w poprzednich encjach
}
```

> Powyższy szkielet to **przykład, nie gotowiec**. 
---

## 2. Testy — dopisz dwa @Test do `Lab03EntitiesTest`

**Nie tworzymy nowej klasy testowej** ani nowego stage'a CI. Zamiast tego
**dopisujesz dwie metody** `@Test` do **istniejącego** pliku
`src/test/java/pl/wsb/fitnesstracker/Lab03EntitiesTest.java` (tego samego,
który dociągnąłeś w LAB03). Stage CI `lab03-entities` automatycznie odpali
nowe testy razem z resztą.

Co dopisać:

- `shouldHaveAchievementTable()` — sprawdza, że istnieje tabela `achievement`
  (wzoruj się na metodzie `shouldHaveEventTable()` z tego samego pliku),
- `achievementTableHasUserForeignKey()` — sprawdza, że ma kolumny `id`
  oraz `user_id` (wzoruj się na `userEventTableHasForeignKeyColumns()`).

Helpery `tableExists(...)` i `tableColumns(...)` masz już w klasie — tylko
ich użyj.

Uruchom lokalnie: `mvn -B test -Dtest=Lab03EntitiesTest`. Po stworzeniu
encji `Achievement` z sekcji 1 wszystkie testy w klasie powinny być zielone.

---

## 3. Commit & push

1. Upewnij się, że `clean install` z **wbudowanego w IntelliJ widoku Maven**
   (panel *Maven* → `Lifecycle`) przechodzi lokalnie.
2. Scommituj zmiany i wyślij na swoje repo — stage `lab03-entities` na
   GitHub Actions powinien obejmować wszystkie testy (włącznie z dwoma
   nowymi dla `achievement`).
3. Zgłoś prowadzącemu wykonanie zadania.

---

## Kryteria akceptacji

> **Uwaga:** żeby otrzymać ocenę za LAB03-kontynuacja, **musisz mieć wykonane
> LAB03** (encje, repozytorium, zapytanie JPQL/native — zgodnie z kryteriami
> z `LAB03.md`) **oraz** zakres LAB03-kontynuacja opisany poniżej.

**Ocena 5:**
- Spełnione kryteria oceny 5 z LAB03 (`WorkoutSession`, `Event`, `UserEvent` +
  `JpaRepository` z minimum 1 zapytaniem JPQL i 1 natywnym; zielony
  `Lab03EntitiesTest`).
- Encja `Achievement` zdefiniowana z relacją `@ManyToOne` do `User`.
- Dwa nowe testy w `Lab03EntitiesTest` (`shouldHaveAchievementTable`,
  `achievementTableHasUserForeignKey`) przechodzą lokalnie i na GitHub Actions.
- Zielony build na GitHub Actions po wypchnięciu zmian.

**Ocena 4:**
- Spełnione kryteria oceny 4 z LAB03 (2 z 3 nowych encji + `JpaRepository`
  utworzone, zielony `Lab03EntitiesTest` dla tych encji).
- Dopisane testy przechodzą przynajmniej lokalnie.
