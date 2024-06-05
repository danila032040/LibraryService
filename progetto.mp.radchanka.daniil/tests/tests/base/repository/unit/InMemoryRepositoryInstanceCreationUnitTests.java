package tests.base.repository.unit;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.repository.InMemoryRepository;
import tests.base.repository.mocks.EntityMock;

public class InMemoryRepositoryInstanceCreationUnitTests {
    @Test
    public void createInstance_WhenEntityCloneFactoryIsNull_ThrowsNullPointerException() {
        ThrowingCallable actual = () -> new InMemoryRepository<>(new ArrayList<>(), ArrayList::new, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenResultCollectionFactoryIsNull_ThrowsNullPointerException() {
        ThrowingCallable actual = () -> new InMemoryRepository<>(new ArrayList<>(), null, EntityMock::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenStorageIsNull_ThrowsNullPointerException() {
        ThrowingCallable actual = () -> new InMemoryRepository<>(null, ArrayList::new, EntityMock::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
