package ua.foxminded.quickpoll.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.quickpoll.domain.Vote;
import ua.foxminded.quickpoll.dto.OptionCount;
import ua.foxminded.quickpoll.dto.VoteResult;
import ua.foxminded.quickpoll.repository.VoteRepository;

import javax.inject.Inject;
import java.util.HashMap;

@RestController
public class ComputeResultController {
    @Inject
    VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        Iterable<Vote> allVotesByPoll = voteRepository.findByPoll(pollId);
        VoteResult voteResult = countVotes(allVotesByPoll);

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

    private VoteResult countVotes(Iterable<Vote> allVotesByPoll) {
        VoteResult voteResult = new VoteResult();
        int totalVotes = 0;
        HashMap<Long, OptionCount> longOptionCountHashMap = new HashMap<>();
        for (Vote vote : allVotesByPoll) {
            totalVotes++;

            OptionCount optionCount = longOptionCountHashMap.get(vote.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(vote.getOption().getId());
                longOptionCountHashMap.put(vote.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(longOptionCountHashMap.values());

        return voteResult;
    }
}
