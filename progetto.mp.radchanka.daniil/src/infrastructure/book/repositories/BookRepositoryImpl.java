package infrastructure.book.repositories;

import java.util.Collection;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;

public class BookRepositoryImpl extends InMemoryRepository<Book, BookId>
		implements
			BookRepository {

	public BookRepositoryImpl(Collection<Book> storage,
			Supplier<Collection<Book>> resultCollectionFactory,
			CloneFactory<Book> entityCloneFactory) {
		super(storage, resultCollectionFactory, entityCloneFactory);
	}

}
