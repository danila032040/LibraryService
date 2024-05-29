package application;

import java.util.ArrayList;
import java.util.Collection;

import base.ddd.DomainEventPublisher;
import base.ddd.Entity;
import base.log.LogConfiguration;
import base.log.Logger;
import base.log.LoggerImpl;
import base.log.PrintStreamLogEntryPublisher;
import base.mediator.Mediator;
import base.mediator.MediatorImpl;
import base.mediator.ddd.DomainEventNotificationPublisher;
import base.mediator.notification.NotificationDispatcher;
import base.mediator.notification.NotificationDispatcherImpl;
import base.mediator.request.RequestDispatcher;
import base.mediator.request.RequestDispatcherImpl;
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
	public static void main(String[] args) {
		AppStartup startup = new AppStartup();
		startup.registerDependencies();
	}

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private LibraryRepository libraryRepository;
	private UserRepository userRepository;

	private Collection<Author> authorStorage;
	private Collection<Book> bookStorage;
	private Collection<Library> libraryStorage;
	private Collection<User> userStorage;

	private Mediator mediator;
	private DomainEventPublisher domainEventPublisher;
	private Logger logger;

	public AuthorRepository getAuthorRepository() {
		return authorRepository;
	}

	public Collection<Author> getAuthorStorage() {
		return authorStorage;
	}

	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public Collection<Book> getBookStorage() {
		return bookStorage;
	}

	public DomainEventPublisher getDomainEventPublisher() {
		return domainEventPublisher;
	}

	public LibraryRepository getLibraryRepository() {
		return libraryRepository;
	}

	public Collection<Library> getLibraryStorage() {
		return libraryStorage;
	}

	public Logger getLogger() {
		return logger;
	}

	public Mediator getMediator() {
		return mediator;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public Collection<User> getUserStorage() {
		return userStorage;
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

	private <TEntity extends Entity<TEntityId>, TEntityId> Collection<TEntity> createStorage() {
		return new ArrayList<TEntity>();
	}

	private DomainEventPublisher createDomainEventPublisher(
			NotificationDispatcher notificationDispatcher) {
		return new DomainEventNotificationPublisher(notificationDispatcher);
	}

	private Logger createLogger() {
		var globalLogConfiguration = new LogConfiguration();
		return new LoggerImpl(
				globalLogConfiguration,
				new PrintStreamLogEntryPublisher(
						globalLogConfiguration::getLocale,
						globalLogConfiguration::getTimeStampFormat,
						System.out));
	}

	private Mediator createMediator() {
		return new MediatorImpl(
				new RequestDispatcherImpl(),
				new NotificationDispatcherImpl());
	}

	private void registerNotificationHandlers(NotificationDispatcher mediator) {
	}

	private void registerRequestHandlers(RequestDispatcher mediator) {

	}
}
