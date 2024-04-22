package repository;

import java.util.Optional;
import java.util.stream.Stream;

import specification.Specification;

public class SpecificationRetrieveSingleResultStrategy<T> implements RetrieveSingleItemStrategy<T> {
	private final Specification<T> _specification;

	private SpecificationRetrieveSingleResultStrategy(Specification<T> specification) {
		_specification = specification;
	}

	public static <T> SpecificationRetrieveSingleResultStrategy<T> from(Specification<T> specification) {
		return new SpecificationRetrieveSingleResultStrategy<T>(specification);
	}

	@Override
	public Optional<T> retrieve(Stream<T> dataStream) {
		return dataStream
			.filter( _specification::isSatisfiedBy)
			.findFirst();
	}

}
