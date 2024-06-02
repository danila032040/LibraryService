package domain.book;

import base.repository.Repository;

public interface BookRepository extends Repository<Book, BookId> {
	public BookId generateNewBookId();
}
