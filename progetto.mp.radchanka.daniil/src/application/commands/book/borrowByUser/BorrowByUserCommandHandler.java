package application.commands.book.borrowByUser;

import java.util.Optional;

import application.commands.common.address.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.Success;
import base.utils.Mapper;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;
import domain.book.specifications.BookByIdSpecification;
import domain.common.Address;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.specifications.UserByIdSpecification;

public class BorrowByUserCommandHandler
		implements
			RequestHandler<BorrowByUserCommand, ErrorOr<Success>> {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final DomainEventPublisher domainEventPublisher;

	public BorrowByUserCommandHandler(BookRepository bookRepository,
			UserRepository userRepository,
			DomainEventPublisher domainEventPublisher,
			Mapper<AddressCommandData, Address> addressMapper) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public ErrorOr<Success> handle(BorrowByUserCommand request) {
		try {
			BookId bookId = new BookId(request.getBookId());
			UserId userId = new UserId(request.getUserId());

			Optional<Book> optionalExistingBook = bookRepository
					.getFirst(new BookByIdSpecification(bookId));

			if (optionalExistingBook.isEmpty())
				return ErrorOr
						.fromErrorMessage(
								"Book with specified id was not found");

			if (userRepository.exists(new UserByIdSpecification(userId)))
				return ErrorOr
						.fromErrorMessage(
								"User with specified id was not found");

			Book existingBook = optionalExistingBook.orElseThrow();

			existingBook.borrowByUser(userId);

			bookRepository.update(existingBook);
			domainEventPublisher
					.publishDomainEvents(existingBook.extractAllDomainEvents());

			return ErrorOr
					.fromResult(Success.from("Successfully borrowed book"));
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}
}
