package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(
            value = """
                SELECT e.name AS event_name,
                       COUNT(ue.user_id) AS participant_count
                FROM event e
                LEFT JOIN user_event ue ON e.id = ue.event_id
                GROUP BY e.name
                ORDER BY e.name
                """,
            nativeQuery = true
    )
    List<Object[]> findEventNamesWithParticipantCount();
}