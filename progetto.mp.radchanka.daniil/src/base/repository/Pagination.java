package base.repository;

public final class Pagination {
    public static Pagination of(int pageIndex, int pageSize) {
        return new Pagination(pageIndex, pageSize);
    }
    
    private final int pageIndex;
    
    private final int pageSize;
    
    private Pagination(int pageIndex, int pageSize) {
        if (pageIndex < 0) {
            throw new IllegalArgumentException("Page index must be positive");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        
    }
    
    public int getPageIndex() {
        return this.pageIndex;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
}
