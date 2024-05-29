package domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import base.cloneable.Cloneable;
import base.ddd.Entity;
import domain.book.BookId;
import domain.common.Address;
import domain.user.events.UserChangedAddressDomainEvent;
import domain.user.events.UserChangedPhoneNumberDomainEvent;
import domain.user.events.UserCreatedDomainEvent;
import domain.user.events.UserSpecifiedPhoneNumberDomainEvent;
import domain.user.exceptions.BookWasAlreadyBorrowedByTheUserDomainEvent;
import domain.user.exceptions.BookWasNotBorrowedByTheUserDomainEvent;

public class User extends Entity<UserId> implements Cloneable<User> {
	public static User createNewUser(
			UserId id,
			String name,
			String surname,
			Address address,
			Optional<String> phoneNumber) {
		User createdUser = new User(
				id,
				name,
				surname,
				address,
				phoneNumber,
				new ArrayList<BookId>());
		createdUser
				.registerDomainEvent(new UserCreatedDomainEvent(createdUser));
		return createdUser;
	}
	private Address address;
	private final Collection<BookId> borrowedBookIds;
	private String name;
	private Optional<String> phoneNumber;

	private String surname;

	private User(UserId id, String name, String surname, Address address,
			Optional<String> phoneNumber, Collection<BookId> borrowedBookIds) {
		super(id);
		this.name = Objects.requireNonNull(name);
		this.surname = Objects.requireNonNull(surname);
		this.address = Objects.requireNonNull(address);
		this.phoneNumber = Objects.requireNonNull(phoneNumber);
		this.borrowedBookIds = Objects.requireNonNull(borrowedBookIds);
	}

	public void addBorrowedBook(BookId bookId)
			throws BookWasAlreadyBorrowedByTheUserDomainEvent {
		Objects.requireNonNull(bookId);
		if (borrowedBookIds.contains(bookId))
			throw new BookWasAlreadyBorrowedByTheUserDomainEvent(this, bookId);
		borrowedBookIds.add(bookId);
	}

	public void changeAddress(Address address) {
		Objects.requireNonNull(address);
		Address previousAddress = this.address;
		Address currentAddress = address;
		this.address = address;
		this
				.registerDomainEvent(
						new UserChangedAddressDomainEvent(
								this,
								previousAddress,
								currentAddress));
	}

	public void changePhoneNumber(String phoneNumber) {
		Objects.requireNonNull(phoneNumber);
		Optional<String> previousPhoneNumber = this.phoneNumber;
		String currentPhoneNumber = phoneNumber;
		this.phoneNumber = Optional.of(phoneNumber);

		previousPhoneNumber.ifPresentOrElse(previousPhoneNumberValue -> {
			this
					.registerDomainEvent(
							new UserChangedPhoneNumberDomainEvent(
									this,
									previousPhoneNumberValue,
									currentPhoneNumber));
		}, () -> {
			this
					.registerDomainEvent(
							new UserSpecifiedPhoneNumberDomainEvent(
									this,
									currentPhoneNumber));
		});
	}

	@Override
	public User createClone() {
		return new User(
				this.getId().createClone(),
				name,
				surname,
				address,
				phoneNumber,
				borrowedBookIds
						.stream()
						.map(BookId::createClone)
						.collect(Collectors.toList()));
	}

	public Address getAddress() {
		return address;
	}

	public Collection<BookId> getBorrowedBookIds() {
		return borrowedBookIds
				.stream()
				.map(BookId::createClone)
				.collect(Collectors.toList());
	}

	public String getName() {
		return name;
	}

	public Optional<String> getPhoneNumber() {
		return phoneNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void returnBorrowedBook(BookId bookId)
			throws BookWasNotBorrowedByTheUserDomainEvent {
		Objects.requireNonNull(bookId);
		if (!borrowedBookIds.contains(bookId))
			throw new BookWasNotBorrowedByTheUserDomainEvent(this, bookId);
		borrowedBookIds.remove(bookId);
	}
}
