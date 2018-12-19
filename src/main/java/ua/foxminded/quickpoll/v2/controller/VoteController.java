package ua.foxminded.quickpoll.v2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.foxminded.quickpoll.domain.Vote;
import ua.foxminded.quickpoll.repository.VoteRepository;

import javax.inject.Inject;
import java.net.URI;

@RestController("voteControllerV2")
@RequestMapping(value = "/v2/polls/{id}")
@Api(value = "Votes", description = "Votes API", tags = {"Votes"})
public class VoteController {
    @Inject
    VoteRepository voteRepository;

    @RequestMapping(value = "/votes", method = RequestMethod.POST)
    @ApiOperation(value = "Casts a new vote for a given poll", notes = "The newly created vote Id will be sent in the location response header",
            response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Vote Created Successfully", response = Void.class)})
    public ResponseEntity<?> createVote(@PathVariable Long id, @RequestBody Vote vote) {
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

    @RequestMapping(value = "/votes", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the votes", response = Vote.class, responseContainer = "List")
    public Iterable<Vote> getAllVotes(@PathVariable Long id) {
        return voteRepository.findByPoll(id);
    }
}
