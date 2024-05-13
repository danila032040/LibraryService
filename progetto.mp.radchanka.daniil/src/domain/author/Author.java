package domain.author;

import base.ddd.Entity;
import base.cloneable.Cloneable;

public class Author extends Entity<AuthorId> implements Cloneable<Author> {
	private String name;
	private String surname;
	private String country;

	private Author(AuthorId id, String name, String surname, String country) {
		super(id);
	}

	public static Author createNewAuthor(
			AuthorId id,
			String name,
			String surname,
			String country) {
		Author createdAuthor = new Author(id, name, surname, country);
		return createdAuthor;
	}

	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getCountry() {
		return country;
	}

	@Override
	public Author createClone() {
		return new Author(this.getId().createClone(), name, surname, country);
	}

}
