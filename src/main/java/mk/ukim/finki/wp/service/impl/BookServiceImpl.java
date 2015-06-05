package mk.ukim.finki.wp.service.impl;

import java.util.List;

import mk.ukim.finki.wp.model.Book;
import mk.ukim.finki.wp.repository.BookRepository;
import mk.ukim.finki.wp.service.BookService;

import org.hibernate.SessionFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class BookServiceImpl extends
        BaseEntityCrudServiceImpl<Book, BookRepository> implements
        BookService {

  @Autowired
  private BookRepository repository;

  @Override
  protected BookRepository getRepository() {
    return repository;
  }

  @Override
  public List<Book> findByCategoryId(Long id) {
    return repository.findByCategoryId(id);
  }

  @PersistenceContext
  private EntityManager em;

  @Transactional
  public List search(String text) {
    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

    QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(Book.class).get();

    org.apache.lucene.search.Query luceneQuery = qb.keyword()
            .onFields("name", "description", "category.name")
            .matching("*"+text+"*")
            .createQuery();

    // wrap Lucene query in a javax.persistence.Query
    javax.persistence.Query jpaQuery =
            fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

    // execute search
    return jpaQuery.getResultList();
  }

  @Override
  public List<Book> findPromotedBooks() {
    return repository.findByPromoted(true);
  }
}
