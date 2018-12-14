package ua.foxminded.quickpoll.dto.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ValidationError {
    private String code;
    private String message;
}
