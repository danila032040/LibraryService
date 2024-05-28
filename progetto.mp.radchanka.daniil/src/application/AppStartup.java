package application;

import java.util.ArrayList;
import java.util.Collection;

import base.ddd.DomainEventPublisher;
import base.ddd.Entity;
import base.log.GlobalLogConfiguration;
import base.log.Logger;
import base.log.LoggerImpl;
import base.log.PrintStreamLogEntryPublisher;
import base.mediator.Mediator;
import base.mediator.MediatorImpl;
import base.mediator.NotificationDispatcher;
import base.mediator.NotificationDispatcherImpl;
import base.mediator.RequestDispatcher;
import base.mediator.RequestDispatcherImpl;
import base.mediator.ddd.DomainEventNotificationPublisher;
import domain.author.Author;
import domain.author.AuthorRepository;
import domain.book.Book;
import domain.book.BookRepository;
import domain.library.Library;
import domain.library.LibraryRepository;
import domain.user.User;
import domain.user.UserRepository;
import infrastructure.author.repositories.AuthorRepositoryImpl;
import infrastructure.book.repositories.BookRepositoryImpl;
import infrastructure.library.repositories.LibraryRepositoryImpl;
import infrastructure.user.repositories.UserRepositoryImpl;

public class AppStartup {
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private LibraryRepository libraryRepository;
	private UserRepository userRepository;
	private DomainEventPublisher domainEventPublisher;

	private Collection<Author> authorStorage;
	private Collection<Book> bookStorage;
	private Collection<Library> libraryStorage;
	private Collection<User> userStorage;

	private Logger logger;
	private Mediator mediator;

	public static void main(String[] args) {
		AppStartup startup = new AppStartup();
		startup.registerDependencies();
	}

	public void registerDependencies() {
		mediator = createMediator();
		logger = createLogger();
		authorStorage = createStorage();
		bookStorage = createStorage();
		libraryStorage = createStorage();
		userStorage = createStorage();
		authorRepository = createAuthorRepository(authorStorage);
		bookRepository = createBookRepository(bookStorage);
		libraryRepository = createLibraryRepository(libraryStorage);
		userRepository = createUserRepository(userStorage);
		domainEventPublisher = createDomainEventPublisher(mediator);

		registerNotificationHandlers(mediator);
		registerRequestHandlers(mediator);
	}

	private void registerNotificationHandlers(NotificationDispatcher mediator) {
	}

	private void registerRequestHandlers(RequestDispatcher mediator) {

	}

	private <TEntity extends Entity<TEntityId>, TEntityId> Collection<TEntity> createStorage() {
		return new ArrayList<TEntity>();
	}

	private Mediator createMediator() {
		return new MediatorImpl(
				new RequestDispatcherImpl(),
				new NotificationDispatcherImpl());
	}

	private Logger createLogger() {
		var globalLogConfiguration = new GlobalLogConfiguration();
		return new LoggerImpl(
				globalLogConfiguration,
				new PrintStreamLogEntryPublisher(
						globalLogConfiguration,
						System.out));
	}

	private AuthorRepository createAuthorRepository(
			Collection<Author> authorStorage) {
		return new AuthorRepositoryImpl(
				authorStorage,
				ArrayList::new,
				Author::createClone);
	}
	private BookRepository createBookRepository(Collection<Book> bookStorage) {
		return new BookRepositoryImpl(
				bookStorage,
				ArrayList::new,
				Book::createClone);
	}
	private LibraryRepository createLibraryRepository(
			Collection<Library> libraryStorage) {
		return new LibraryRepositoryImpl(
				libraryStorage,
				ArrayList::new,
				Library::createClone);
	}
	private UserRepository createUserRepository(Collection<User> userStorage) {
		return new UserRepositoryImpl(
				userStorage,
				ArrayList::new,
				User::createClone);
	}

	private DomainEventPublisher createDomainEventPublisher(
			NotificationDispatcher notificationDispatcher) {
		return new DomainEventNotificationPublisher(notificationDispatcher);
	}
}
