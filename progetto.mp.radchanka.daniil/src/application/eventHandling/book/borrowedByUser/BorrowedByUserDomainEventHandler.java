package application.eventHandling.book.borrowedByUser;

import base.log.Logger;
import base.mediator.ddd.DomainEventNotification;
import base.mediator.notification.NotificationHandler;
import domain.book.BookId;
import domain.book.events.BookBorrowedByUserDomainEvent;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.exceptions.UserNotFoundException;
import domain.user.specifications.UserByIdSpecification;

public class BorrowedByUserDomainEventHandler
		implements
			NotificationHandler<DomainEventNotification<BookBorrowedByUserDomainEvent>> {
	private final Logger logger;
	private final UserRepository userRepository;

	public BorrowedByUserDomainEventHandler(Logger logger,
			UserRepository userRepository) {
		this.logger = logger;
		this.userRepository = userRepository;
	}

	@Override
	public void handle(
			DomainEventNotification<BookBorrowedByUserDomainEvent> notification) {
		try {
			BookId borrowedBookId = notification
					.getDomainEvent()
					.getBorrowedBook()
					.getId();
			UserId userId = notification
					.getDomainEvent()
					.getUserIdThatHadBorrowedTheBook();
			User user = userRepository
					.getFirst(new UserByIdSpecification(userId))
					.orElseThrow(
							() -> new UserNotFoundException(
									"User was not found with id: "
											+ userId.getId()));

			if (!user.getBorrowedBookIds().contains(borrowedBookId))
				user.addBorrowedBook(borrowedBookId);
		} catch (Exception exc) {
			logger
					.logError(
							"Could not handle {0}: {1}",
							"BorrowedByUserDomainEvent",
							exc.getMessage());
		}
	}

}
