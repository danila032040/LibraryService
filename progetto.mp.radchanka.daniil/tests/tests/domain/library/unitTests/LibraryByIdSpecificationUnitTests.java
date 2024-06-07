package tests.domain.library.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.common.Address;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.specifications.LibraryByIdSpecification;

public class LibraryByIdSpecificationUnitTests {
    @Test
    public void constructor_WhenLibraryIdIsNull_ShouldThrowException() {
        ThrowingCallable actual = () -> new LibraryByIdSpecification(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenLibraryIsNull_ShouldThrowException() {
        LibraryId libraryId = new LibraryId(1);
        LibraryByIdSpecification specification = new LibraryByIdSpecification(libraryId);
        
        ThrowingCallable actual = () -> specification.isSatisfiedBy(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenLibraryIdMatches_ShouldReturnTrue() {
        LibraryId libraryId = new LibraryId(1);
        LibraryByIdSpecification specification = new LibraryByIdSpecification(libraryId);
        Library library = Library.createNewLibrary(libraryId, new Address());
        
        boolean result = specification.isSatisfiedBy(library);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void isSatisfiedBy_WhenLibraryIdDoesNotMatch_ShouldReturnFalse() {
        LibraryId libraryId1 = new LibraryId(1);
        LibraryId libraryId2 = new LibraryId(2);
        LibraryByIdSpecification specification = new LibraryByIdSpecification(libraryId1);
        Library library = Library.createNewLibrary(libraryId2, new Address());
        
        boolean result = specification.isSatisfiedBy(library);
        
        assertThat(result).isFalse();
    }
}
