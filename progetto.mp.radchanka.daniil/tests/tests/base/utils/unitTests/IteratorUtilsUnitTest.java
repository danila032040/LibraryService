package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import base.utils.IteratorUtils;
import tests.base.utils.mocks.AccumulatorMock;

public class IteratorUtilsUnitTest {
    @Test
    public void elementsEqual_WhenAllElementsAreEqual_ShouldBeTrue() {
        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5);
        
        boolean actual = IteratorUtils.elementsEqual(list1.iterator(), list2.iterator());
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void elementsEqual_WhenAtLeastOneElementIsNotEqual_ShouldBeFalse() {
        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 2, 4, 5);
        ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5);
        
        boolean actual = IteratorUtils.elementsEqual(list1.iterator(), list2.iterator());
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void elementsEqual_WhenFirstCollectionIsShorterWithEqualInitialElements_ShouldBeFalse() {
        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3);
        ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5);
        
        boolean actual = IteratorUtils.elementsEqual(list1.iterator(), list2.iterator());
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void elementsEqual_WhenNullInstanceIsUsed_ShouldNotThrowAnyException() {
        ArrayList<Integer> list1 = Lists.newArrayList(null, null);
        ArrayList<Integer> list2 = Lists.newArrayList(null, 2, 3, null, 5);
        
        ThrowingCallable actual = () -> IteratorUtils.elementsEqual(list1.iterator(), list2.iterator());
        
        assertThatNoException().isThrownBy(actual);
    }
    
    @Test
    public void elementsEqual_WhenSecondCollectionIsShorterWithEqualInitialElements_ShouldBeFalse() {
        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3);
        
        boolean actual = IteratorUtils.elementsEqual(list1.iterator(), list2.iterator());
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void reduceRemaining_ShouldInvokeAccumulatorWithCorrectArguments() {
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
        int expectedAccumulatedValueBeforeLastElement = 10;
        int expectedLastElement = 5;
        Iterator<Integer> iterator = elements.iterator();
        AccumulatorMock<Integer, Integer> accumulatorMock = new AccumulatorMock<>((acc, element) -> acc + element);
        
        IteratorUtils.reduceRemaining(iterator, 0, accumulatorMock);
        
        assertThat(accumulatorMock.getExecutionsCount()).isEqualTo(elements.size());
        assertThat(accumulatorMock.getLastSpecifiedT()).contains(expectedAccumulatedValueBeforeLastElement);
        assertThat(accumulatorMock.getLastSpecifiedU()).contains(expectedLastElement);
    }
    
    @Test
    public void reduceRemaining_ShouldReturnAccumulatedResult() {
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = elements.iterator();
        int expectedResult = 15;
        AccumulatorMock<Integer, Integer> accumulatorMock = new AccumulatorMock<>((acc, element) -> acc + element);
        
        Integer result = IteratorUtils.reduceRemaining(iterator, 0, accumulatorMock);
        
        assertThat(result).isEqualTo(expectedResult);
        assertThat(accumulatorMock.getExecutionsCount()).isEqualTo(elements.size());
    }
    
    @Test
    public void reduceRemaining_WhenAccumulatorIsNull_ShouldThrowNullPointerException() {
        Iterator<Integer> iterator = Arrays.<Integer>asList(1, 2, 3, 4, 5).iterator();
        ThrowingCallable actual = () -> IteratorUtils.reduceRemaining(iterator, 0, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void reduceRemaining_WhenIteratorIsEmpty_ShouldReturnIdentity() {
        Iterator<Integer> emptyIterator = Arrays.<Integer>asList().iterator();
        AccumulatorMock<Integer, Integer> accumulatorMock = new AccumulatorMock<>((acc, element) -> acc + element);
        int expectedIdentity = 0;
        
        Integer result = IteratorUtils.reduceRemaining(emptyIterator, expectedIdentity, accumulatorMock);
        
        assertThat(result).isEqualTo(expectedIdentity);
        assertThat(accumulatorMock.getExecutionsCount()).isEqualTo(0);
    }
    
    @Test
    public void reduceRemaining_WhenIteratorIsEmptyAndAccumulatorIsNull_ShouldThrowNullPointerException() {
        Iterator<Integer> emptyIterator = Arrays.<Integer>asList().iterator();
        ThrowingCallable actual = () -> IteratorUtils.reduceRemaining(emptyIterator, 0, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void toArrayList_ShouldReturnArrayListWithElementsReturnedInOrderFromIterator() {
        ArrayDeque<Integer> list = IntStream
                .of(1, 2, 3, 4, 5, 6)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
        Integer[] expected = new Integer[] { 6, 5, 4, 3, 2, 1 };
        
        ArrayList<Integer> actual = IteratorUtils.toArrayList(list.descendingIterator());
        
        assertThat(actual).containsExactly(expected);
    }
    
    @Test
    public void toArrayList_WhenIteratorsDoesNotHaveNextElements_ShouldReturnEmptyArrayList() {
        ArrayList<Integer> expected = Lists.newArrayList();
        
        ArrayList<Integer> actual = IteratorUtils.toArrayList(expected.iterator());
        
        assertThat(actual).isEmpty();
    }
    
}
