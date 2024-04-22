package repository;

public final class Pagination {
	private final int _pageIndex;
	private final int _pageSize;

	private Pagination(int pageIndex, int pageSize) {
		_pageIndex = pageIndex;
		_pageSize = pageSize;

	}

	public static Pagination of(int pageIndex, int pageSize) {
		return new Pagination(pageIndex, pageSize);
	}

	public int getPageIndex() {
		return _pageIndex;
	}

	public int getPageSize() {
		return _pageSize;
	}
}
