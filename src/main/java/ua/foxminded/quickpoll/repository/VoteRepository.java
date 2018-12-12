package ua.foxminded.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ua.foxminded.quickpoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
