package base.specification.composable.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import base.specification.composable.NotSpecification;

@RunWith(Parameterized.class)
public class NotSpecificationUnitTests {
	@Parameters
	public static Collection<Object> data() {
		return Arrays.asList(new Object[][]{{true, false}, {false, true}});
	}
	private boolean expectedResult;

	private NotSpecification<Integer> specification;

	public NotSpecificationUnitTests(boolean isBaseSpecificationSatisfied,
			boolean expectedResult) {
		this.expectedResult = expectedResult;

		this.specification = new NotSpecification<Integer>(
				x1 -> isBaseSpecificationSatisfied);
	}

	@Test
	public void isSatisfiedBy_ShouldReturnCorrectResult() {
		boolean actual = specification.isSatisfiedBy(5);

		assertThat(actual).isEqualTo(expectedResult);
	}

}
