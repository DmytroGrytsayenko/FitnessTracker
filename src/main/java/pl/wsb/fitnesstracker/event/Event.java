package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.ToString;

@Entity// <-- ДОБАВЬ ЭТО СЛОВО
@Table(name = "Event")
@ToString
class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;
    // ... остальной код не трогай
}