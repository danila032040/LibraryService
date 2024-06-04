package application.queries.book.find;

import java.util.Collection;
import java.util.Objects;

import base.mediator.request.RequestHandler;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.result.ErrorOr;
import base.specification.Specification;
import base.specification.composable.CompositeSpecification;
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookRepository;
import domain.library.LibraryId;

public class FindQueryHandler
		implements
			RequestHandler<FindQuery, ErrorOr<Collection<Book>>> {
	private final BookRepository bookRepository;

	public FindQueryHandler(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public ErrorOr<Collection<Book>> handle(FindQuery request) {
		try {
			Specification<Book> specification = BuildSpecificationFromRequest(
					request);
			SortCriteria<Book> sortCriteria = BuildSortCriteriaFromRequest(
					request);
			Pagination pagination = Pagination
					.of(request.getPageIndex(), request.getPageSize());

			return ErrorOr
					.fromResult(
							bookRepository
									.get(
											specification,
											sortCriteria,
											pagination));
		} catch (Exception exc) {
			return ErrorOr.fromErrorMessage(exc.getMessage());
		}
	}

	private Specification<Book> BuildSpecificationFromRequest(
			FindQuery request) {
		CompositeSpecification<Book> baseSpecification = (book) -> true;
		if (request.getAuthorId().isPresent()) {
			AuthorId searchAuthorId = request.getAuthorId().get();
			Specification<Book> bookByAuthorIdSpecification = (book) -> book
					.getAuthorId()
					.map(authorId -> Objects.equals(authorId, searchAuthorId))
					.orElse(false);
			baseSpecification = baseSpecification
					.and(bookByAuthorIdSpecification);
		}
		return baseSpecification;
	}

	private SortCriteria<Book> BuildSortCriteriaFromRequest(FindQuery request) {
		return SortCriteria
				.<Book, Integer>sortByAsc(
						book -> book
								.getAuthorId()
								.map(AuthorId::getId)
								.orElse(Integer.MIN_VALUE))
				.thenSortByDesc(
						book -> book
								.getLibraryId()
								.map(LibraryId::getId)
								.orElse(Integer.MIN_VALUE));
	}

}
