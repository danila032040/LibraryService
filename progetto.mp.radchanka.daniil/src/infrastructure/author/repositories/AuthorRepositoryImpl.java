package infrastructure.author.repositories;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import base.specification.Specification;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.AuthorRepository;

public class AuthorRepositoryImpl extends InMemoryRepository<Author, AuthorId> implements AuthorRepository {
    private int idSeed;
    
    public AuthorRepositoryImpl(
            Collection<Author> storage,
            Supplier<List<Author>> resultCollectionFactory,
            CloneFactory<Author> entityCloneFactory) {
        super(storage, resultCollectionFactory, entityCloneFactory);
        idSeed = storage
                .stream()
                .map(Author::getId)
                .map(AuthorId::getId)
                .max(Integer::compare)
                .map(x -> x + 1)
                .orElse(0);
    }
    
    @Override
    public boolean exists(Specification<Author> specification) {
        return this.getFirst(specification).isPresent();
    }
    
    @Override
    public AuthorId generateNewAuthorId() {
        return new AuthorId(idSeed++);
    }
}
