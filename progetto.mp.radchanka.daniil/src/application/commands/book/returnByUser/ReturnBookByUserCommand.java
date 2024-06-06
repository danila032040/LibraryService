package application.commands.book.returnByUser;

import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.SuccessResult;

public class ReturnBookByUserCommand implements Request<ErrorOr<SuccessResult>> {
    private final int bookId;
    private final int userId;
    
    public ReturnBookByUserCommand(int bookId, int userId) {
        this.bookId = bookId;
        this.userId = userId;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public int getUserId() {
        return userId;
    }
}
