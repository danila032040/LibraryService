package application.queries.book.find;

import base.result.Error;
import base.result.ValidationResult;
import base.utils.Validator;

public class FindQueryValidator implements Validator<FindQuery> {
    
    @Override
    public ValidationResult validate(FindQuery request) {
        return ValidationResult
                .create()
                .withErrorIf(
                        () -> request.getThenSortByField().isPresent() ^ request.getThenSortType().isPresent(),
                        Error.from("ThenSortByField and ThenSortType must be both present or both not present"));
    }
    
}
