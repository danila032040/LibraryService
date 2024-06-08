package application.eventHandling.book.returnByUser;

import base.log.Logger;
import base.mediator.ddd.DomainEventNotification;
import base.mediator.notification.NotificationHandler;
import domain.book.BookId;
import domain.book.events.BookReturnedByUserDomainEvent;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.exceptions.BookWasNotBorrowedByTheUserDomainEvent;
import domain.user.exceptions.UserNotFoundException;
import domain.user.specifications.UserByIdSpecification;

public class ReturnedByUserDomainEventHandler
        implements
        NotificationHandler<DomainEventNotification<BookReturnedByUserDomainEvent>> {
    private final Logger logger;
    private final UserRepository userRepository;
    
    public ReturnedByUserDomainEventHandler(Logger logger, UserRepository userRepository) {
        this.logger = logger;
        this.userRepository = userRepository;
    }
    
    @Override
    public void handle(DomainEventNotification<BookReturnedByUserDomainEvent> notification) {
        try {
            BookId borrowedBookId = notification.getDomainEvent().getReturnedBook().getId();
            UserId userId = notification.getDomainEvent().getUserIdThatHadReturnedTheBook();
            User user = userRepository
                    .getFirst(new UserByIdSpecification(userId))
                    .orElseThrow(() -> new UserNotFoundException(userId));
            if (user.getBorrowedBookIds().contains(borrowedBookId)) {
                user.returnBorrowedBook(borrowedBookId);
            }
        } catch (UserNotFoundException exc) {
            logger
                    .logError(
                            "Could not handle {0}: User({1}) were not found",
                            "ReturnedByUserDomainEvent",
                            exc.getUserId().getId());
        } catch (BookWasNotBorrowedByTheUserDomainEvent exc) {
            logger
                    .logError(
                            "Could not handle {0}: The book({1}) was not borrowed by the User({2})",
                            "ReturnedByUserDomainEvent",
                            exc.getBookIdThatWasNotBorrowed().getId(),
                            exc.getUser().getId().getId());
        }
    }
    
}
