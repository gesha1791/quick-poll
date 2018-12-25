package ua.foxminded.quickpoll.v2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import ua.foxminded.quickpoll.domain.Poll;
import ua.foxminded.quickpoll.dto.error.ErrorDetail;
import ua.foxminded.quickpoll.exception.ResourceNotFoundException;
import ua.foxminded.quickpoll.repository.PollRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController("pollControllerV2")
@RequestMapping(value = "/v2/polls")
@Api(value = "Polls", description = "Poll API", tags = {"Polls"})
public class PollController {
    @Inject
    PollRepository pollRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the polls", response = Poll.class, responseContainer = "List")
    public ResponseEntity<Page<Poll>> getAllPolls(@PageableDefault(size = 12) Pageable pageable) {
        Page<Poll> allPolls = pollRepository.findAll(pageable);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Creates a new Poll", notes = "The newly created poll Id will be sent in the " +
                                                                "localtion response header", response = Void.class)
    @ApiResponses(value =
                          {@ApiResponse(code = 201, message = "Poll created successfully", response = Void.class),
                                  @ApiResponse(code = 500, message = "Error creating Poll", response = ErrorDetail.class)})
    public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll) {
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves a poll associated with the pollId", response = Poll.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Poll.class),
                                  @ApiResponse(code = 404, message = "Unable to find poll", response = ErrorDetail.class)})
    public ResponseEntity<?> getPollById(@PathVariable Long id) {
        verifyPoll(id);
        Optional<Poll> poll = pollRepository.findById(id);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Updates given Poll", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
                                  @ApiResponse(code = 404, message = "Unable to find Poll", response = ErrorDetail.class)})
    public ResponseEntity<Void> updatePoll(@RequestBody Poll poll, @PathVariable Long id) {
        verifyPoll(id);
        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes given Poll", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
                                  @ApiResponse(code = 404, message = "Unable to find Poll", response = ErrorDetail.class)})
    public ResponseEntity<Void> deletePollId(@PathVariable Long id) {
        verifyPoll(id);
        pollRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyPoll(Long id) throws ResourceNotFoundException {
        if (!pollRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Poll with id " + id + " not found");
        }
    }
}
