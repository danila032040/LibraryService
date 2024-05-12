package base.repository.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatList;

import java.util.ArrayList;
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
import base.repository.mocks.EntityMock;

public class InMemoryRepositoryUnitTests {
	private ArrayList<EntityMock> storage;
	private InMemoryRepository<EntityMock, Integer> repository;

	@Before
	public void Setup() {
		storage = Lists.newArrayList();
		repository = new InMemoryRepository<EntityMock, Integer>(
				storage,
				ArrayList::new,
				EntityMock::createClone);
	}

	@Test
	public void add_ShouldAddClonedEntityToTheStorage()
			throws AlreadyExistsException {
		EntityMock expected = new EntityMock(1, 5);
		repository.add(expected);

		EntityMock actual = storage.get(0);

		assertThat(actual).isNotSameAs(expected).isEqualTo(expected);
		assertThat(actual.equalsUsingAllFields(expected)).isTrue();
	}

	@Test
	public void add_WhenAddingTwice_ShouldThrowAlreadyExistsException()
			throws AlreadyExistsException {
		EntityMock entity = new EntityMock(12312454, 5);

		ThrowingCallable actual = () -> {
			repository.add(entity);
			repository.add(entity);
		};

		assertThatExceptionOfType(AlreadyExistsException.class)
				.isThrownBy(actual)
				.withMessageContaining("12312454");
	}

	@Test
	public void addRange_ShouldAddClonedEntitiesToTheStorage()
			throws AlreadyExistsException {
		List<EntityMock> expectedEntities = Lists
				.newArrayList(
						new EntityMock(1, 5),
						new EntityMock(2, 5),
						new EntityMock(3, 5),
						new EntityMock(4, 5));
		repository.addRange(expectedEntities);

		List<EntityMock> actualEntities = storage;
		Map<Integer, EntityMock> actualEntityWithIndexMap = IntStream
				.range(0, actualEntities.size())
				.boxed()
				.collect(Collectors.toMap(i -> i, actualEntities::get));

		assertThatList(actualEntities)
				.isNotSameAs(expectedEntities)
				.isEqualTo(expectedEntities);
		assertThat(actualEntityWithIndexMap)
				.allSatisfy(
						(index, actualEntity) -> actualEntity
								.equalsUsingAllFields(
										expectedEntities.get(index)));
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

		assertThatExceptionOfType(AlreadyExistsException.class)
				.isThrownBy(actual)
				.withMessageContaining("1123123");
		assertThat(actualList).isEmpty();
	}
}
