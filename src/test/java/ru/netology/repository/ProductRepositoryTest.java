package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
  private ProductRepository repository = new ProductRepository();
  private Book book1 = new Book(1, "book1", 1000, "author1", 100, 2000);
  private Book book2 = new Book(2, "book2", 1100, "author2", 200, 2001);
  private TShirt tShirt1 = new TShirt(3, "tShirt1", 800, "black", "M");
  private TShirt tShirt2 = new TShirt(4, "tShirt2", 800, "white", "L");

  @Test
  public void shouldSaveOneItem() {
    repository.save(book1);
    Product[] expected = new Product[]{book1};
    Product[] actual = repository.findAll();
    assertArrayEquals(expected, actual);
  }

  @Test
  void shouldRemoveByExistId() {
    repository.save(book1);
    repository.save(book2);
    repository.save(tShirt1);
    repository.save(tShirt2);
    repository.removeById(3);
    Product[] actual = repository.findAll();
    Product[] expected = new Product[]{book1, book2, tShirt2};
    assertArrayEquals(actual, expected);
  }

  @Test
  void shouldRemoveByNotExistId() {
    assertThrows(NotFoundException.class, () -> repository.removeById(5));
  }
}
