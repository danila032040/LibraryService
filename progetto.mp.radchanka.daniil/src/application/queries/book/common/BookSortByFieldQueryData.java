package application.queries.book.common;

import java.util.Comparator;

import base.repository.SortCriteria;
import base.repository.SortType;
import base.utils.ComparatorUtils;
import domain.book.Book;
import domain.book.BookId;

public enum BookSortByFieldQueryData {
    Id {
        @Override
        public SortCriteria<Book> createSortCriteria(SortType sortType) {
            return SortCriteria.<Book, BookId>sortBy(book -> book.getId(), sortType);
        }
        
        @Override
        public SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType) {
            return sortCriteria.thenSortBy(book -> book.getId(), sortType);
        }
    },
    Name {
        @Override
        public SortCriteria<Book> createSortCriteria(SortType sortType) {
            return SortCriteria.<Book, String>sortBy(book -> book.getName(), sortType);
        }
        
        @Override
        public SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType) {
            return sortCriteria.thenSortBy(book -> book.getName(), sortType);
        }
    },
    Genre {
        @Override
        public SortCriteria<Book> createSortCriteria(SortType sortType) {
            return SortCriteria.<Book, String>sortBy(book -> book.getGenre(), sortType);
        }
        
        @Override
        public SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType) {
            return sortCriteria.thenSortBy(book -> book.getGenre(), sortType);
        }
    },
    PublicationYear {
        @Override
        public SortCriteria<Book> createSortCriteria(SortType sortType) {
            return SortCriteria.<Book, Integer>sortBy(book -> book.getPublicationYear(), sortType);
        }
        
        @Override
        public SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType) {
            return sortCriteria.thenSortBy(book -> book.getPublicationYear(), sortType);
        }
    },
    AuthorId {
        @Override
        public SortCriteria<Book> createSortCriteria(SortType sortType) {
            Comparator<Book> comparator = ComparatorUtils.comparingOptionalField(book -> book.getAuthorId());
            return SortCriteria
                    .<Book>sortUsingComparator(
                            sortType == SortType.Descending ? comparator.reversed() : comparator.reversed());
        }
        
        @Override
        public SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType) {
            Comparator<Book> comparator = ComparatorUtils.comparingOptionalField(book -> book.getAuthorId());
            return sortCriteria
                    .thenSortUsingComparator(
                            sortType == SortType.Descending ? comparator.reversed() : comparator.reversed());
        }
    },
    LibraryId {
        @Override
        public SortCriteria<Book> createSortCriteria(SortType sortType) {
            Comparator<Book> comparator = ComparatorUtils.comparingOptionalField(book -> book.getLibraryId());
            return SortCriteria
                    .<Book>sortUsingComparator(
                            sortType == SortType.Descending ? comparator.reversed() : comparator.reversed());
        }
        
        @Override
        public SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType) {
            Comparator<Book> comparator = ComparatorUtils.comparingOptionalField(book -> book.getLibraryId());
            return sortCriteria
                    .thenSortUsingComparator(
                            sortType == SortType.Descending ? comparator.reversed() : comparator.reversed());
        }
    };
    
    public abstract SortCriteria<Book> createSortCriteria(SortType sortType);
    
    public abstract SortCriteria<Book> applyThenSort(SortCriteria<Book> sortCriteria, SortType sortType);
}
