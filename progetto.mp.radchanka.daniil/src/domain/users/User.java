package domain.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import base.ddd.Entity;
import base.cloneable.Cloneable;
import domain.Address;
import domain.book.BookId;
import domain.users.events.UserCreatedDomainEvent;

public class User extends Entity<UserId> implements Cloneable<User> {
	private String name;
	private String surname;
	private Address address;
	private Optional<String> phoneNumber;
	private Collection<BookId> borrowedBookIds;

	private User(UserId id, String name, String surname, Address address,
			Optional<String> phoneNumber, Collection<BookId> reservedBookIds) {
		super(id);
	}

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
		createdUser.registerDomainEvent(new UserCreatedDomainEvent(id));
		return createdUser;
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

	public void addBorrowedBook(BookId bookId) {
		borrowedBookIds.add(bookId);
	}

	public void returnBook(BookId bookId) {
		borrowedBookIds.remove(bookId);
	}
}
