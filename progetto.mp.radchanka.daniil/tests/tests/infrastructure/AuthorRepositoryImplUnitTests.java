package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import base.specification.Specification;
import domain.author.Author;
import domain.author.AuthorId;
import infrastructure.author.repositories.AuthorRepositoryImpl;

public class AuthorRepositoryImplUnitTests {
    @Test
    public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new AuthorRepositoryImpl(new ArrayList<>(), ArrayList::new, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new AuthorRepositoryImpl(new ArrayList<>(), null, Author::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new AuthorRepositoryImpl(null, ArrayList::new, Author::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateNewAuthorId_WhenStorageIsEmpty_ShouldReturnAuthorIdZero() {
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                Author::createClone);
        
        AuthorId newAuthorId = authorRepository.generateNewAuthorId();
        
        assertThat(newAuthorId.getId()).isEqualTo(0);
    }
    
    @Test
    public void generateNewAuthorId_WhenStorageIsNotEmpty_ShouldReturnNextAuthorId() {
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(
                Lists
                        .newArrayList(
                                Author.createNewAuthor(new AuthorId(1), "", "", ""),
                                Author.createNewAuthor(new AuthorId(2), "", "", ""),
                                Author.createNewAuthor(new AuthorId(5), "", "", "")),
                ArrayList::new,
                Author::createClone);
        
        AuthorId newAuthorId = authorRepository.generateNewAuthorId();
        
        assertThat(newAuthorId.getId()).isEqualTo(6);
    }
    
    @Test
    public void exists_WhenStorageIsNotEmptyAndAuthorSatisfiesSpecification_ShouldReturnTrue() {
        Author author = Author.createNewAuthor(new AuthorId(1), "", "", "");
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(
                Lists.newArrayList(author),
                ArrayList::new,
                Author::createClone);
        
        Specification<Author> specification = u -> u.getId().equals(new AuthorId(1));
        
        boolean result = authorRepository.exists(specification);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void exists_WhenStorageIsNotEmptyAndNoAuthorSatisfiesSpecification_ShouldReturnFalse() {
        Author author = Author.createNewAuthor(new AuthorId(1), "", "", "");
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(
                Lists.newArrayList(author),
                ArrayList::new,
                Author::createClone);
        
        Specification<Author> specification = u -> u.getId().equals(new AuthorId(2));
        
        boolean result = authorRepository.exists(specification);
        
        assertThat(result).isFalse();
    }
    
    @Test
    public void exists_WhenStorageIsEmpty_ShouldReturnFalse() {
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                Author::createClone);
        
        Specification<Author> specification = u -> u.getId().equals(new AuthorId(1));
        
        boolean result = authorRepository.exists(specification);
        
        assertThat(result).isFalse();
    }
}
