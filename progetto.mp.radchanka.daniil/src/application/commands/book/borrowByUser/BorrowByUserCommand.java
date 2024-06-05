package application.commands.book.borrowByUser;

import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.Success;

public class BorrowByUserCommand implements Request<ErrorOr<Success>> {
    private final int bookId;
    private final int userId;
    
    public BorrowByUserCommand(int bookId, int userId) {
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
