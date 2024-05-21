package domain.user;

import java.util.ArrayList;
import java.util.Collection;
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
	private Collection<BookId> borrowedBookIds;
	private String name;
	private Optional<String> phoneNumber;

	private String surname;

	private User(UserId id, String name, String surname, Address address,
			Optional<String> phoneNumber, Collection<BookId> reservedBookIds) {
		super(id);
	}

	public void addBorrowedBook(BookId bookId) {
		borrowedBookIds.add(bookId);
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

	public void removeReturnedBook(BookId bookId) {
		borrowedBookIds.remove(bookId);
	}

	public void setAddress(Address address) {
		Address previousAddress = this.address;
		this.address = address;
		this
				.registerDomainEvent(
						new UserChangedAddressDomainEvent(
								this,
								previousAddress,
								address));
	}

	public void setPhoneNumber(String phoneNumber) {
		Optional<String> previousPhoneNumber = this.phoneNumber;
		this.phoneNumber = Optional.of(phoneNumber);

		previousPhoneNumber.ifPresentOrElse(previousPhoneNumberValue -> {
			this
					.registerDomainEvent(
							new UserChangedPhoneNumberDomainEvent(
									this,
									previousPhoneNumberValue,
									phoneNumber));
		}, () -> {
			this
					.registerDomainEvent(
							new UserSpecifiedPhoneNumberDomainEvent(
									this,
									phoneNumber));
		});
	}
}
