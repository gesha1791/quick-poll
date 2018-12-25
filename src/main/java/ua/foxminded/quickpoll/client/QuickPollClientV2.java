package ua.foxminded.quickpoll.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.foxminded.quickpoll.domain.Option;
import ua.foxminded.quickpoll.domain.Poll;

import java.awt.*;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuickPollClientV2 {
    private static final String QUICK_POLL_URI_2 = "http://localhost:8090/v2/polls";
    private RestTemplate restTemplate = new RestTemplate();

    public CustomPageImpl<Poll> getAllPolls(int page, int size) {
        ParameterizedTypeReference<CustomPageImpl<Poll>> responseType = new ParameterizedTypeReference<CustomPageImpl<Poll>>() {};
        UriComponentsBuilder builder = UriComponentsBuilder
                                               .fromHttpUrl(QUICK_POLL_URI_2)
                                               .queryParam("page", page)
                                               .queryParam("size", size);

        ResponseEntity<CustomPageImpl<Poll>> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
        CustomPageImpl<Poll> allPolls = responseEntity.getBody();
        return allPolls;
    }
    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_2 + "/{pollId}", Poll.class, pollId);
    }

    public URI createPoll(Poll poll) {
        return restTemplate.postForLocation( QUICK_POLL_URI_2, poll);
    }

    public void updatePoll(Poll poll) {
        restTemplate.put(QUICK_POLL_URI_2 + "/{pollId}",  poll, poll.getId());
    }

    public void deletePoll(Long pollId) {
        restTemplate.delete(QUICK_POLL_URI_2 + "/{pollId}",  pollId);
    }

    public static void main(String[] args) {
        QuickPollClientV2 client = new QuickPollClientV2();

        // Test GetPoll
        Poll poll = client.getPollById(1001L);
        System.out.println(poll);

        // Test getAllPolls
        CustomPageImpl<Poll> allPolls = client.getAllPolls(2, 3);
        System.out.println(allPolls);

        // Test Create Poll
        Poll newPoll = new Poll();
        newPoll.setQuestion("What is your favourate color 2?");
        Set<Option> options = new HashSet<>();
        newPoll.setOptions(options);

        Option option1 = new Option();
        option1.setValue("Red");
        options.add(option1);

        Option option2 = new Option();
        option2.setValue("Blue");
        options.add(option2);
        URI pollLocation = client.createPoll(newPoll);
        System.out.println("Newly Created Poll Location " + pollLocation);

        // Test Update Poll with Id 6
        Poll pollForId1006 = client.getPollById(1006L);
        // Add a new Option
        Option newOption = new Option();
        newOption.setValue("The Incredibles 2");
        pollForId1006.getOptions().add(newOption);

        client.updatePoll(pollForId1006);

        pollForId1006 = client.getPollById(1006L);
        System.out.println("Updated poll has " + pollForId1006.getOptions().size() + " options");

        // Test Delete
        client.deletePoll(1009L);
    }


}

