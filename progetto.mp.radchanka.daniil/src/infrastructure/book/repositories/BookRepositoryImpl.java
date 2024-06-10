package infrastructure.book.repositories;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;

public class BookRepositoryImpl extends InMemoryRepository<Book, BookId> implements BookRepository {
    private int idSeed;
    
    public BookRepositoryImpl(
            Collection<Book> storage,
            Supplier<List<Book>> resultCollectionFactory,
            CloneFactory<Book> entityCloneFactory) {
        super(storage, resultCollectionFactory, entityCloneFactory);
        idSeed = storage.stream().map(Book::getId).map(BookId::getId).max(Integer::compare).map(x -> x + 1).orElse(0);
    }
    
    @Override
    public BookId generateNewBookId() {
        return new BookId(idSeed++);
    }
}
