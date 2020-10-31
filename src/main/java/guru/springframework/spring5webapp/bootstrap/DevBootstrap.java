package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

        @Override
        public void run (String...args) throws Exception {

            Publisher publisher = new Publisher();
            publisher.setName("foo");
            publisher.setAddress("12th Street, LA");
            publisherRepository.save(publisher);

            //Eric
            Author eric = new Author("Eric", "Evans");
            Book ddd = new Book("Domain Driven Design", "1234", publisher);
            eric.getBooks().add(ddd);
            ddd.getAuthors().add(eric);
            ddd.setPublisher(publisher);
            publisher.getBooks().add(ddd);

            authorRepository.save(eric);
            bookRepository.save(ddd);

            //Rod
            Author Rod = new Author("Rod", "Johnson");
            Book noEJB = new Book("J2EE Development without EJB", "23444", publisher);
            Rod.getBooks().add(noEJB);
            noEJB.getAuthors().add(Rod);
            noEJB.setPublisher(publisher);
            publisher.getBooks().add(ddd);

            authorRepository.save(Rod);
            bookRepository.save(noEJB);
           publisherRepository.save(publisher);

            System.out.println("Started in Bootstrap");
            System.out.println("Number of Books: " + bookRepository.count());
            System.out.println("Number of publishers: " + publisherRepository.count());
            System.out.println("Publisher number of books: " + publisher.getBooks().size());
        }
    }

