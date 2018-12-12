package ua.foxminded.quickpoll.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OptionCount {
    private Long optionId;
    private int count;
}
