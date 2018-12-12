package ua.foxminded.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ua.foxminded.quickpoll.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
