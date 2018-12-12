package ua.foxminded.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ua.foxminded.quickpoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
