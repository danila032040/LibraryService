package application.queries.book.find;

import java.util.function.Function;

import domain.author.AuthorId;
import domain.book.Book;
import domain.library.LibraryId;

public enum BookSortByField {
	id {
		@Override
		public Function<Book, ? extends Comparable<?>> getSortByFieldExpression() {
			return book -> book.getId().getId();
		}

	},
	name {
		@Override
		public Function<Book, ? extends Comparable<?>> getSortByFieldExpression() {
			return book -> book.getName();
		}
	},
	genre {

		@Override
		public Function<Book, ? extends Comparable<?>> getSortByFieldExpression() {
			return book -> book.getGenre();
		}
	},
	publicationYear {
		@Override
		public Function<Book, ? extends Comparable<?>> getSortByFieldExpression() {
			return book -> book.getPublicationYear();
		}
	},
	authorId {
		@Override
		public Function<Book, ? extends Comparable<?>> getSortByFieldExpression() {
			return book -> book.getAuthorId().map(AuthorId::getId).orElse(0);
		}
	},
	libraryId {
		@Override
		public Function<Book, ? extends Comparable<?>> getSortByFieldExpression() {
			return book -> book.getLibraryId().map(LibraryId::getId).orElse(0);
		}
	};

	public abstract Function<Book, ? extends Comparable<?>> getSortByFieldExpression();
}
