package tests.domain.author.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.author.Author;
import domain.author.AuthorId;
import domain.author.specifications.AuthorByIdSpecification;

public class AuthorByIdSpecificationUnitTests {
    @Test
    public void constructor_WhenAuthorIdIsNull_ShouldThrowException() {
        ThrowingCallable actual = () -> new AuthorByIdSpecification(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenAuthorIsNull_ShouldThrowException() {
        AuthorId authorId = new AuthorId(1);
        AuthorByIdSpecification specification = new AuthorByIdSpecification(authorId);
        
        ThrowingCallable actual = () -> specification.isSatisfiedBy(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenAuthorIdMatches_ShouldReturnTrue() {
        AuthorId authorId = new AuthorId(1);
        AuthorByIdSpecification specification = new AuthorByIdSpecification(authorId);
        Author author = Author.createNewAuthor(authorId, "", "", "");
        
        boolean result = specification.isSatisfiedBy(author);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void isSatisfiedBy_WhenAuthorIdDoesNotMatch_ShouldReturnFalse() {
        AuthorId authorId1 = new AuthorId(1);
        AuthorId authorId2 = new AuthorId(2);
        AuthorByIdSpecification specification = new AuthorByIdSpecification(authorId1);
        Author author = Author.createNewAuthor(authorId2, "", "", "");
        
        boolean result = specification.isSatisfiedBy(author);
        
        assertThat(result).isFalse();
    }
}
