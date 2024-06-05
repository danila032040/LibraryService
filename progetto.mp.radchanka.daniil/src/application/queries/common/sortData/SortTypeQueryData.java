package application.queries.common.sortData;

import base.repository.SortType;

public enum SortTypeQueryData {
    Ascending {
        @Override
        public SortType mapToSortType() {
            return SortType.Ascending;
        }
    },
    Descending {
        @Override
        public SortType mapToSortType() {
            return SortType.Descending;
        }
    };
    
    public abstract SortType mapToSortType();
}
