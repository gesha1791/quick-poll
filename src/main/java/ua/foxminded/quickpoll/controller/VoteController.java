package ua.foxminded.quickpoll.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.foxminded.quickpoll.domain.Vote;
import ua.foxminded.quickpoll.repository.VoteRepository;

import javax.inject.Inject;
import javax.persistence.Id;
import java.net.URI;

@RestController
public class VoteController {
    @Inject
    VoteRepository voteRepository;

    @RequestMapping(value = "/polls/{id}/votes/", method = RequestMethod.POST)
    public ResponseEntity<?> createVote (@PathVariable Long id, @RequestBody Vote vote){
        voteRepository.save(vote);

        HttpHeaders httpHeaders = new HttpHeaders();

        // Create URI
        URI newPollURI = ServletUriComponentsBuilder
                                 .fromCurrentRequest()
                                 .path("/{id}")
                                 .buildAndExpand(vote.getId())
                                 .toUri();
        httpHeaders.setLocation(newPollURI);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{id}/votes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes (@PathVariable Long id){
        return voteRepository.findByPoll(id);
    }
}
