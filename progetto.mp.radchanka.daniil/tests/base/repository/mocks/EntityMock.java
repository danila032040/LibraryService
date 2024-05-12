package base.repository.mocks;

import base.ddd.Entity;
import base.cloneable.Cloneable;

public class EntityMock extends Entity<Integer>
		implements
			Cloneable<EntityMock> {
	private int someIntUsedForSortBy;

	public EntityMock(int id, int someFieldUsedForSortBy) {
		super(id);
	}

	public int getSomeFieldUsedForSortBy() {
		return someIntUsedForSortBy;
	}

	public boolean equalsUsingAllFields(EntityMock otherEntity) {
		return this.getId() == otherEntity.getId()
				&& this.getSomeFieldUsedForSortBy() == otherEntity
						.getSomeFieldUsedForSortBy();
	}

	@Override
	public EntityMock createClone() {
		return new EntityMock(getId(), getSomeFieldUsedForSortBy());
	}

}
