package tests.base.specification.composable.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import base.specification.composable.AndSpecification;

@RunWith(Parameterized.class)
public class AndSpecificationUnitTests {
    @Parameters
    public static Collection<Object> data() {
        return Arrays
                .asList(
                        new Object[][] { { true, true, true }, { true, false, false }, { false, true, false },
                                        { false, false, false } });
    }
    
    private boolean expectedResult;
    
    private AndSpecification<Integer> specification;
    
    public AndSpecificationUnitTests(
            boolean isFirstSpecificationSatisfied,
            boolean isSecondSpecificationSafisfied,
            boolean expectedResult) {
        this.expectedResult = expectedResult;
        
        this.specification = new AndSpecification<>(
                x1 -> isFirstSpecificationSatisfied,
                x2 -> isSecondSpecificationSafisfied);
    }
    
    @Test
    public void isSatisfiedBy_ShouldReturnCorrectResult() {
        boolean actual = specification.isSatisfiedBy(5);
        
        assertThat(actual).isEqualTo(expectedResult);
    }
    
}
