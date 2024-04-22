package domainDrivenDesign.cloneable;

import domainDrivenDesign.ValueObject;

public abstract class CloneableValueObject<TValueObject extends ValueObject>
		extends
			ValueObject
		implements
			Cloneable<TValueObject> {
}
