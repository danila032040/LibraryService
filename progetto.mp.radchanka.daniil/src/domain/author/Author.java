package domain.author;

import java.util.Objects;

import base.cloneable.Cloneable;
import base.ddd.Entity;
import domain.author.events.AuthorCreatedDomainEvent;

public class Author extends Entity<AuthorId> implements Cloneable<Author> {
	public static Author createNewAuthor(
			AuthorId id,
			String name,
			String surname,
			String country) {
		Author createdAuthor = new Author(id, name, surname, country);
		createdAuthor
				.registerDomainEvent(
						new AuthorCreatedDomainEvent(createdAuthor));
		return createdAuthor;
	}
	private String country;
	private String name;

	private String surname;

	private Author(AuthorId id, String name, String surname, String country) {
		super(Objects.requireNonNull(id));
		this.name = Objects.requireNonNull(name);
		this.surname = Objects.requireNonNull(surname);
		this.country = Objects.requireNonNull(country);
	}

	@Override
	public Author createClone() {
		return new Author(this.getId().createClone(), name, surname, country);
	}
	public String getCountry() {
		return country;
	}
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
