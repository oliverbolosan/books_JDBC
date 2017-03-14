
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olive
 */
public interface BooksDAO 
{
    Set<Books> getAllBooks();
    Books getBook(String bookTitle);
    Books getAllBookDetails(String bookTitle);
    boolean insertBook(Books book);
    boolean updateBook(Books book);
    boolean updateBookPublisher(String newPublisher, String oldPublisher);
    boolean deleteBook(Books book);
}
