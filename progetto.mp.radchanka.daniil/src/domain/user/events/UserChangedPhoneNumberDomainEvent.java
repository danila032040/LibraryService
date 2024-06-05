package domain.user.events;

import base.ddd.DomainEvent;
import domain.user.User;

public class UserChangedPhoneNumberDomainEvent implements DomainEvent {
    private final User user;
    private final String previousPhoneNumber;
    private final String currentPhoneNumber;
    
    public UserChangedPhoneNumberDomainEvent(User user, String previousPhoneNumber, String currentPhoneNumber) {
        this.user = user;
        this.previousPhoneNumber = previousPhoneNumber;
        this.currentPhoneNumber = currentPhoneNumber;
    }
    
    public String getCurrentPhoneNumber() {
        return currentPhoneNumber;
    }
    
    public String getPreviousPhoneNumber() {
        return previousPhoneNumber;
    }
    
    public User getUser() {
        return user;
    }
    
}
