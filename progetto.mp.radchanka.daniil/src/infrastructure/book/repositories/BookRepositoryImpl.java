package infrastructure.book.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;

public class BookRepositoryImpl extends InMemoryRepository<Book, BookId> implements BookRepository {
    private Optional<Integer> lastGeneratedId;
    
    public BookRepositoryImpl(
            Collection<Book> storage,
            Supplier<Collection<Book>> resultCollectionFactory,
            CloneFactory<Book> entityCloneFactory) {
        super(storage, resultCollectionFactory, entityCloneFactory);
        lastGeneratedId = storage.stream().map(Book::getId).map(BookId::getId).max(Integer::compare);
    }
    
    @Override
    public BookId generateNewBookId() {
        return new BookId(lastGeneratedId.map(x -> x + 1).or(() -> Optional.of(0)).get());
    }
}
