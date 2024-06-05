package tests.base.repository.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import base.repository.AlreadyExistsException;
import base.repository.InMemoryRepository;
import base.repository.Pagination;
import base.repository.SortCriteria;
import tests.base.repository.mocks.EntityMock;

public class InMemoryRepositoryUnitTests {
    private InMemoryRepository<EntityMock, Integer> repository;
    private ArrayList<EntityMock> storage;
    
    @Before
    public void setup() {
        storage = Lists.newArrayList();
        repository = new InMemoryRepository<>(storage, ArrayList::new, EntityMock::createClone);
    }
    
    @Test
    public void add_ShouldAddClonedEntityToTheStorage() throws AlreadyExistsException {
        EntityMock expected = new EntityMock(1, 5);
        repository.add(expected);
        
        EntityMock actual = storage.get(0);
        
        assertThat(actual).isNotSameAs(expected).isEqualTo(expected);
        assertThat(actual.equalsUsingAllFields(expected)).isTrue();
    }
    
    @Test
    public void add_WhenAddingTwice_ShouldThrowAlreadyExistsException() throws AlreadyExistsException {
        EntityMock entity = new EntityMock(12312454, 5);
        
        ThrowingCallable actual = () -> {
            repository.add(entity);
            repository.add(entity);
        };
        
        assertThatExceptionOfType(AlreadyExistsException.class).isThrownBy(actual).withMessageContaining("12312454");
    }
    
    @Test
    public void addRange_ShouldAddClonedEntitiesToTheStorage() throws AlreadyExistsException {
        List<EntityMock> expectedEntities = Lists
                .newArrayList(new EntityMock(1, 5), new EntityMock(2, 5), new EntityMock(3, 5), new EntityMock(4, 5));
        repository.addRange(expectedEntities);
        
        List<EntityMock> actualEntities = storage;
        Map<Integer, EntityMock> actualEntityWithIndexMap = IntStream
                .range(0, actualEntities.size())
                .boxed()
                .collect(Collectors.toMap(i -> i, actualEntities::get));
        
        assertThatList(actualEntities).isNotSameAs(expectedEntities).isEqualTo(expectedEntities);
        assertThat(actualEntityWithIndexMap)
                .allSatisfy((index, actualEntity) -> actualEntity.equalsUsingAllFields(expectedEntities.get(index)));
    }
    
    @Test
    public void addRange_WithDuplicateIds_ShouldThrowAlreadyExistsExceptionAndNotAddAnythingToTheStorage() {
        List<EntityMock> entities = Lists
                .newArrayList(
                        new EntityMock(1123123, 5),
                        new EntityMock(2, 5),
                        new EntityMock(3, 5),
                        new EntityMock(1123123, 5));
        
        ThrowingCallable actual = () -> {
            repository.addRange(entities);
        };
        List<EntityMock> actualList = storage;
        
        assertThatExceptionOfType(AlreadyExistsException.class).isThrownBy(actual).withMessageContaining("1123123");
        assertThatList(actualList).isEmpty();
    }
    
    @Test
    public void get_WhenSpecificationIsAlwaysTrue_ShouldReturnCloneOfTheFirstElementFromStorage() {
        List<EntityMock> expected = Lists
                .newArrayList(new EntityMock(1, 2), new EntityMock(2, 2), new EntityMock(3, 2));
        storage.addAll(expected);
        
        Collection<EntityMock> actual = repository.get(x -> true);
        
        assertThat(actual)
                .satisfiesExactly(
                        item1 -> assertThat(item1).isNotSameAs(expected.get(0)).isEqualTo(expected.get(0)),
                        item2 -> assertThat(item2).isNotSameAs(expected.get(1)).isEqualTo(expected.get(1)),
                        item3 -> assertThat(item3).isNotSameAs(expected.get(2)).isEqualTo(expected.get(2)));
    }
    
    @Test
    public void get_WhenSpecificationIsAlwaysTrueWithPage2OfSize2_ShouldReturnPaginatedAndClonedElementsFromTheStorage() {
        List<EntityMock> expected = Lists
                .newArrayList(
                        new EntityMock(1, 2),
                        new EntityMock(2, 2),
                        new EntityMock(3, 2),
                        new EntityMock(4, 2),
                        new EntityMock(5, 2),
                        new EntityMock(6, 2),
                        new EntityMock(7, 2),
                        new EntityMock(8, 2));
        storage.addAll(expected);
        
        Collection<EntityMock> actual = repository.get(x -> true, Pagination.of(2, 2));
        
        assertThat(actual)
                .satisfiesExactly(
                        item1 -> assertThat(item1).isNotSameAs(expected.get(4)).isEqualTo(expected.get(4)),
                        item2 -> assertThat(item2).isNotSameAs(expected.get(5)).isEqualTo(expected.get(5)));
    }
    
    @Test
    public void get_WhenSpecificationIsAlwaysTrueWithPage2OfSize2AndSortedBySomeField_ShouldReturnPaginatedAndClonedElementsFromTheSortedStorageWithoutPermutatingStorage() {
        List<EntityMock> expected = Lists
                .newArrayList(
                        new EntityMock(1, 8),
                        new EntityMock(2, 7),
                        new EntityMock(3, 6),
                        new EntityMock(4, 5),
                        new EntityMock(5, 4),
                        new EntityMock(6, 3),
                        new EntityMock(7, 2),
                        new EntityMock(8, 1));
        storage.addAll(expected);
        
        Collection<EntityMock> actual = repository
                .get(
                        x -> true,
                        SortCriteria.<EntityMock, Integer>sortByAsc(x -> x.getSomeFieldUsedForSortBy()),
                        Pagination.of(2, 2));
        
        assertThat(storage).containsExactly(expected.toArray(size -> new EntityMock[size]));
        assertThat(actual)
                .satisfiesExactly(
                        item1 -> assertThat(item1).isNotSameAs(expected.get(3)).isEqualTo(expected.get(3)),
                        item2 -> assertThat(item2).isNotSameAs(expected.get(2)).isEqualTo(expected.get(2)));
    }
    
    @Test
    public void getAll_ShouldReturnClonedElementsFromTheStorage() {
        List<EntityMock> expected = Lists
                .newArrayList(new EntityMock(1, 2), new EntityMock(2, 2), new EntityMock(3, 2));
        storage.addAll(expected);
        
        Collection<EntityMock> actual = repository.getAll();
        
        assertThat(actual)
                .satisfiesExactly(
                        item1 -> assertThat(item1).isNotSameAs(expected.get(0)).isEqualTo(expected.get(0)),
                        item2 -> assertThat(item2).isNotSameAs(expected.get(1)).isEqualTo(expected.get(1)),
                        item3 -> assertThat(item3).isNotSameAs(expected.get(2)).isEqualTo(expected.get(2)));
    }
    
    @Test
    public void getFirst_WhenSpecificationIsAlwaysTrue_ShouldReturnCloneOfTheFirstElementFromStorage() {
        storage.add(new EntityMock(1, 2));
        storage.add(new EntityMock(2, 2));
        storage.add(new EntityMock(3, 2));
        EntityMock expectedMock = storage.get(0);
        
        EntityMock actual = repository.getFirst(x -> true).get();
        
        assertThat(actual).isNotSameAs(expectedMock).isEqualTo(expectedMock);
    }
    
    @Test
    public void remove_ShouldRemoveFromStorage() {
        EntityMock expectedRemovedEntity = new EntityMock(5, 2);
        storage.add(new EntityMock(1, 2));
        storage.add(new EntityMock(3, 2));
        storage.add(expectedRemovedEntity);
        int expectedRemovedEntityId = expectedRemovedEntity.getId();
        
        repository.remove(expectedRemovedEntityId);
        
        assertThatList(storage).doesNotContain(expectedRemovedEntity);
    }
    
    @Test
    public void removeRange_ShouldRemoveAllFromStorage() {
        List<EntityMock> expectedRemovedEntities = Lists
                .newArrayList(
                        new EntityMock(1123123, 5),
                        new EntityMock(2, 5),
                        new EntityMock(3, 5),
                        new EntityMock(112312, 5));
        List<Integer> expectedRemovedEntityIds = expectedRemovedEntities
                .stream()
                .map(EntityMock::getId)
                .collect(Collectors.toUnmodifiableList());
        storage.add(new EntityMock(1, 2));
        storage.addAll(expectedRemovedEntities);
        storage.add(new EntityMock(4, 2));
        storage.add(new EntityMock(5, 2));
        
        repository.removeRange(expectedRemovedEntityIds);
        
        assertThatList(storage).doesNotContain(expectedRemovedEntities.toArray(size -> new EntityMock[size]));
    }
    
    @Test
    public void update_ShouldUpdateByIdInStorage() {
        storage.add(new EntityMock(5, 3));
        storage.add(new EntityMock(3, 5));
        EntityMock expected = new EntityMock(5, 6);
        repository.update(expected);
        
        List<EntityMock> actual = storage.stream().filter(x -> x.getId() == 5).collect(Collectors.toUnmodifiableList());
        
        assertThat(actual).satisfiesExactly(item1 -> assertThat(item1).isNotSameAs(expected).isEqualTo(expected));
    }
    
    @Test
    public void updateRange_ShouldUpdateAllByIdInStorage() {
        storage.add(new EntityMock(5, 3));
        storage.add(new EntityMock(3, 5));
        List<EntityMock> expected = Lists.newArrayList(new EntityMock(5, 6), new EntityMock(3, 6));
        repository.updateRange(expected);
        
        List<EntityMock> actual = storage
                .stream()
                .filter(x -> x.getId() == 5 || x.getId() == 3)
                .collect(Collectors.toUnmodifiableList());
        
        assertThatList(actual)
                .satisfiesExactly(
                        item1 -> assertThat(item1).isNotSameAs(expected.get(0)).isEqualTo(expected.get(0)),
                        item2 -> assertThat(item2).isNotSameAs(expected.get(1)).isEqualTo(expected.get(1)));
    }
}
