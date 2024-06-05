package domain.user.events;

import base.ddd.DomainEvent;
import domain.user.User;

public class UserSpecifiedPhoneNumberDomainEvent implements DomainEvent {
    private final User user;
    private final String specifiedPhoneNumber;
    
    public UserSpecifiedPhoneNumberDomainEvent(User user, String specifiedPhoneNumber) {
        this.user = user;
        this.specifiedPhoneNumber = specifiedPhoneNumber;
    }
    
    public String getSpecifiedPhoneNumber() {
        return specifiedPhoneNumber;
    }
    
    public User getUser() {
        return user;
    }
}
