package ua.foxminded.quickpoll.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.foxminded.quickpoll.domain.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {
}
