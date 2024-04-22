package domainDrivenDesign.cloneable;

import domainDrivenDesign.Entity;

public abstract class CloneableEntity<TEntity extends Entity<TId>, TId>
		extends
			Entity<TId>
		implements
			Cloneable<TEntity> {
}
