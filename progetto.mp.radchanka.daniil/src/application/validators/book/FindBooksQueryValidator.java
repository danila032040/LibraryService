package application.validators.book;

import application.queries.book.find.FindBooksQuery;
import base.result.ErrorResult;
import base.result.ValidationResult;
import base.utils.Validator;

public class FindBooksQueryValidator implements Validator<FindBooksQuery> {
    
    @Override
    public ValidationResult validate(FindBooksQuery request) {
        return ValidationResult
                .create()
                .withErrorIf(
                        () -> request.getThenSortByField().isPresent() ^ request.getThenSortType().isPresent(),
                        ErrorResult.from("ThenSortByField and ThenSortType must be both present or both not present"));
    }
    
}
