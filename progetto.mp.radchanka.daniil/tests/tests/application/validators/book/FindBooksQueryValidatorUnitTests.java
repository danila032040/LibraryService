package tests.application.validators.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.book.find.FindBooksQuery;
import application.queries.common.sortData.SortTypeQueryData;
import application.validators.book.FindBooksQueryValidator;
import base.result.ValidationResult;

public class FindBooksQueryValidatorUnitTests {
    
    @Test
    public void validate_WhenThenSortByFieldAndThenSortTypeAreBothPresent_ShouldBeValid() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                null,
                null,
                Optional.of(BookSortByFieldQueryData.Id),
                Optional.of(SortTypeQueryData.Ascending));
        FindBooksQueryValidator validator = new FindBooksQueryValidator();
        
        ValidationResult validationResult = validator.validate(query);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenThenSortByFieldAndThenSortTypeAreBothNotPresent_ShouldBeValid() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                null,
                null,
                Optional.empty(),
                Optional.empty());
        FindBooksQueryValidator validator = new FindBooksQueryValidator();
        
        ValidationResult validationResult = validator.validate(query);
        
        assertThat(validationResult.isValid()).isTrue();
        assertThat(validationResult.getErrors()).isEmpty();
    }
    
    @Test
    public void validate_WhenOnlyThenSortByFieldIsPresent_ShouldBeNotValidWithErrors() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                null,
                null,
                Optional.of(BookSortByFieldQueryData.Id),
                Optional.empty());
        FindBooksQueryValidator validator = new FindBooksQueryValidator();
        
        ValidationResult validationResult = validator.validate(query);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getErrorMessage())
                .isEqualTo("ThenSortByField and ThenSortType must be both present or both not present");
    }
    
    @Test
    public void validate_WhenOnlyThenSortTypeIsPresent_ShouldBeNotValidWithErrors() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                null,
                null,
                Optional.empty(),
                Optional.of(SortTypeQueryData.Ascending));
        FindBooksQueryValidator validator = new FindBooksQueryValidator();
        
        ValidationResult validationResult = validator.validate(query);
        
        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().get(0).getErrorMessage())
                .isEqualTo("ThenSortByField and ThenSortType must be both present or both not present");
    }
}
