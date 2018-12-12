package ua.foxminded.quickpoll.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString
public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> results;
}
