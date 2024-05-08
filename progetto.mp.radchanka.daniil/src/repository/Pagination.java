package repository;

public final class Pagination {
	public static Pagination of(int pageIndex, int pageSize) {
		return new Pagination(pageIndex, pageSize);
	}
	private final int pageIndex;

	private final int pageSize;

	private Pagination(int pageIndex, int pageSize) {
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
