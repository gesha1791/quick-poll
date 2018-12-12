package ua.foxminded.quickpoll.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.quickpoll.domain.Vote;
import ua.foxminded.quickpoll.dto.VoteResult;
import ua.foxminded.quickpoll.repository.VoteRepository;

import javax.inject.Inject;

@RestController
public class ComputeResultController {
    @Inject
    VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId){
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotesByPoll = voteRepository.findByPoll(pollId);

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
