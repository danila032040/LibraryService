package domain.user.exceptions;

import domain.user.UserId;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private final UserId userId;
    
    public UserNotFoundException(UserId userId) {
        this.userId = userId;
    }
    
    public UserId getUserId() {
        return userId;
    }
}
