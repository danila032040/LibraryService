package application.commands.book.borrowByUser;

import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.SuccessResult;

public class BorrowBookByUserCommand implements Request<ErrorOr<SuccessResult>> {
    private final int bookId;
    private final int userId;
    
    public BorrowBookByUserCommand(int bookId, int userId) {
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
