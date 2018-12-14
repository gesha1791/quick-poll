package ua.foxminded.quickpoll.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.foxminded.quickpoll.Exception.ResourceNotFoundException;
import ua.foxminded.quickpoll.domain.Poll;
import ua.foxminded.quickpoll.repository.PollRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {
    @Inject
    PollRepository pollRepository;

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);

        HttpHeaders httpHeaders = new HttpHeaders();

        // Create URI
        URI newPollURI = ServletUriComponentsBuilder
                                 .fromCurrentRequest()
                                 .path("/{id}")
                                 .buildAndExpand(poll.getId())
                                 .toUri();
        httpHeaders.setLocation(newPollURI);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPollById(@PathVariable Long id) {
        verifyPoll(id);
        Optional<Poll> poll = pollRepository.findById(id);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long id) {
        verifyPoll(id);
        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePollId(@PathVariable Long id) {
        verifyPoll(id);
        pollRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyPoll(Long id) throws ResourceNotFoundException {
        if(!pollRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Poll with id " + id + " not found");
        }
    }
}
