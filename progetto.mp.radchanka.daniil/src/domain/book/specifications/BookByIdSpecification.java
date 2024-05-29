package domain.book.specifications;

import base.specification.Specification;
import domain.book.Book;
import domain.book.BookId;

public class BookByIdSpecification implements Specification<Book> {
	private final BookId bookId;

	public BookByIdSpecification(BookId authorId) {
		this.bookId = authorId;
	}

	@Override
	public boolean isSatisfiedBy(Book book) {
		return book.getId().equals(bookId);
	}
}
