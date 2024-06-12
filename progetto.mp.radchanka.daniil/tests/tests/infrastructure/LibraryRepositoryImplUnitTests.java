package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import base.specification.Specification;
import domain.common.Address;
import domain.library.Library;
import domain.library.LibraryId;
import infrastructure.library.repositories.LibraryRepositoryImpl;

public class LibraryRepositoryImplUnitTests {
    @Test
    public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new LibraryRepositoryImpl(new ArrayList<>(), ArrayList::new, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new LibraryRepositoryImpl(new ArrayList<>(), null, Library::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new LibraryRepositoryImpl(null, ArrayList::new, Library::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void exists_WhenStorageIsEmpty_ShouldReturnFalse() {
        LibraryRepositoryImpl libraryRepository = new LibraryRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                Library::createClone);
        
        Specification<Library> specification = u -> u.getId().equals(new LibraryId(1));
        
        boolean result = libraryRepository.exists(specification);
        
        assertThat(result).isFalse();
    }
    
    @Test
    public void exists_WhenStorageIsNotEmptyAndLibrarySatisfiesSpecification_ShouldReturnTrue() {
        Library library = Library.createNewLibrary(new LibraryId(1), new Address());
        LibraryRepositoryImpl libraryRepository = new LibraryRepositoryImpl(
                Lists.newArrayList(library),
                ArrayList::new,
                Library::createClone);
        
        Specification<Library> specification = u -> u.getId().equals(new LibraryId(1));
        
        boolean result = libraryRepository.exists(specification);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void exists_WhenStorageIsNotEmptyAndNoLibrarySatisfiesSpecification_ShouldReturnFalse() {
        Library library = Library.createNewLibrary(new LibraryId(1), new Address());
        LibraryRepositoryImpl libraryRepository = new LibraryRepositoryImpl(
                Lists.newArrayList(library),
                ArrayList::new,
                Library::createClone);
        
        Specification<Library> specification = u -> u.getId().equals(new LibraryId(2));
        
        boolean result = libraryRepository.exists(specification);
        
        assertThat(result).isFalse();
    }
    
    @Test
    public void generateNewLibraryId_WhenStorageIsEmpty_ShouldReturnLibraryIdZero() {
        LibraryRepositoryImpl libraryRepository = new LibraryRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                Library::createClone);
        
        LibraryId newLibraryId = libraryRepository.generateNewLibraryId();
        
        assertThat(newLibraryId.getId()).isEqualTo(0);
    }
    
    @Test
    public void generateNewLibraryId_WhenStorageIsNotEmpty_ShouldReturnNextLibraryId() {
        LibraryRepositoryImpl libraryRepository = new LibraryRepositoryImpl(
                Lists
                        .newArrayList(
                                Library.createNewLibrary(new LibraryId(1), new Address()),
                                Library.createNewLibrary(new LibraryId(2), new Address()),
                                Library.createNewLibrary(new LibraryId(5), new Address())),
                ArrayList::new,
                Library::createClone);
        
        LibraryId newLibraryId = libraryRepository.generateNewLibraryId();
        
        assertThat(newLibraryId.getId()).isEqualTo(6);
    }
}
