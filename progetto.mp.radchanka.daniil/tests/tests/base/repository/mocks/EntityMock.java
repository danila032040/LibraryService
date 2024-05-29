package tests.base.repository.mocks;

import base.cloneable.Cloneable;
import base.ddd.Entity;

public class EntityMock extends Entity<Integer>
		implements
			Cloneable<EntityMock> {
	private final int someIntUsedForSortBy;

	public EntityMock(int id, int someFieldUsedForSortBy) {
		super(id);
		this.someIntUsedForSortBy = someFieldUsedForSortBy;
	}

	@Override
	public EntityMock createClone() {
		return new EntityMock(getId(), getSomeFieldUsedForSortBy());
	}

	public boolean equalsUsingAllFields(EntityMock otherEntity) {
		return this.getId() == otherEntity.getId()
				&& this.getSomeFieldUsedForSortBy() == otherEntity
						.getSomeFieldUsedForSortBy();
	}

	public int getSomeFieldUsedForSortBy() {
		return someIntUsedForSortBy;
	}

}
