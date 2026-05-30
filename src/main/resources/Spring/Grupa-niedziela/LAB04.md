# LABORATORIUM 04 - Spring Boot - Tworzenie sieciowego API CRUD

**Uwaga - Przed przystapieniem do zadania proszę o wykonanie zadania LAB01/02/03 (poprzednie zajęcia).**
Zsynchronizuj swoje repozytorium z repozytorium prowadzącego (Sync Fork bądź Pull z repozytorium prowadzącego). Można
wykonać to bezpośrednio w GitHubie lub lokalnie.

## ZADANIE 1. Sieciowe API do operacji typu CRUD na klientach

### Potrzeba biznesowa

Jako użytkownik systemu, chcę mieć możliwość zarządzania użytkownikami
serwisu FitnessTracker:

- móc ich wyszukiwać, pobierać
- móc wprowadzać nowych użytkowników do systemu
- móc usuwać użytkowników z systemu
- móc aktualizować użytkowników

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wylistowanie podstawowych informacji o wszystkich użytkownikach zapisanych w systemie (tylko ID oraz nazwa
  użytkownika - imię i nazwisko)
- [ ] pobranie szczegółów dotyczących wybranego użytkownika (dowolny parametr: ID/ imię & nazwisko/ e-mail)
- [ ] utworzenie nowego użytkownika
- [ ] usunięcie użytkownika (konkretny, np. konkretny ID danego uzytkownika)
- [ ] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [ ] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany
- [ ] aktualizowanie użytkowników w systemie (dowolnie wybrany atrybut)

### Przygotowanie środowiska

Przed rozpoczęciem implementacji skopiuj plik `UserApiIntegrationTest.java`
z katalogu `src/main/resources/Spring/Grupa-niedziela/` do katalogu testów
projektu, czyli do `src/test/java/pl/wsb/fitnesstracker/` (taka jest standardowa
lokalizacja testów w projekcie Maven). Plik zawiera testy integracyjne,
które będą weryfikować poprawność Twojej implementacji API.

### Wymagania techniczne

- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] (OPCJONALNIE) rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [ ] rozwiązanie powinno wykorzystywać rekordy (Java 16+) do definicji obiektów transferu danych (DTO)


**Kryterium Akceptacji na ocenę 5:**

- Poprawnie wykonane 5 wymagań funkcjonalnych zadania 1.
- Poprawnie działające testy jednostkowe do napisanych wymagań funkcjonalnych (UserApiIntegrationTest) - również na
  Github Actions
- Przesłanie zmian na swoje repozytorium.

**Kryterium Akceptacji na ocenę 4.5:**

- Poprawnie wykonane 3 wymagania funkcjonalne z zadania 1.
- Poprawnie działające testy jednostkowe do napisanych wymagań funkcjonalnych (UserApiIntegrationTest) - również na
  Github Actions
- Przesłanie zmian na swoje repozytorium.
- 
**Kryterium Akceptacji na ocenę 4:**

- Poprawnie wykonane 2 wymagania funkcjonalne z zadania 1.
- Poprawnie działające testy jednostkowe do napisanych wymagań funkcjonalnych (UserApiIntegrationTest) - również na
  Github Actions
- Przesłanie zmian na swoje repozytorium.

**Kryterium Akceptacji na ocenę 3:**

- Obecność na zajęciach i zgłoszenie wykonania zadania.
- Poprawnie wykonane 1 wymaganie funkcjonalne z zadania 1.
- Poprawnie działający test jednostkowe do napisanego wymagań funkcjonalnych (UserApiIntegrationTest) - również na
  Github Actions
- Przesłanie zmian na swoje repozytorium..
