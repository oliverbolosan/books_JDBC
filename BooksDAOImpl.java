
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
public class BooksDAOImpl implements BooksDAO
{

    @Override
    public Set<Books> getAllBooks() 
    {
        Connection connection = null;
        Statement stmt = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKS");
            Set books = new HashSet();
            while(rs.next())
            {
                Books book = extractBookFromResultSet(rs);
                books.add(book);
            }
            stmt.close();
            connection.close();
            return books;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if(stmt != null)
                {
                    stmt.close();
                }
            }
            catch(SQLException ex2)
            {
                
            }
            try
            {
                if(connection != null)
                {
                    connection.close();
                }
            }
            catch(SQLException ex3)
            {
                ex3.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Books getAllBookDetails(String bookTitle) 
    {
        PreparedStatement ps = null;
        Connection connection = null;
        String selectBook = "SELECT * FROM BOOKS NATURAL JOIN WRITINGGROUPS NATURAL JOIN PUBLISHERS WHERE BOOKTITLE=?";
        try
        {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(selectBook);
            ps.setString(1, bookTitle);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Books book = new Books();
                WritingGroups group = new WritingGroups();
                Publishers pub = new Publishers();
                
                book.setBookTitle(rs.getString("BOOKTITLE"));
                book.setNumberPages(rs.getInt("NUMBEROFPAGES"));
                book.setYearPublished(rs.getString("YEARPUBLISHED"));
                book.setGroupName(rs.getString("GROUPNAME"));
                group.setHeadWriter(rs.getString("HEADWRITER"));
                group.setYearFormed(rs.getString("YEARFORMED"));
                group.setSubject(rs.getString("SUBJECT"));
                book.setPublisherName(rs.getString("PUBLISHERNAME"));
                pub.setAddress(rs.getString("PUBLISHERADDRESS"));
                pub.setPhone(rs.getString("PUBLISHERPHONE"));
                pub.setEmail(rs.getString("PUBLISHEREMAIL"));
                
                book.setGroup(group);
                book.setPublisher(pub);
                return book;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Books getBook(String bookTitle) 
    {
        PreparedStatement ps = null;
        Connection connection = null;
        String selectBook = "SELECT * FROM BOOKS WHERE BOOKTITLE=?";
        try
        {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(selectBook);
            ps.setString(1, bookTitle);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractBookFromResultSet(rs);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertBook(Books book) 
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO BOOKS VALUES (?,?,?,?,?)");
            ps.setString(1, book.getGroupName());
            ps.setString(2, book.getBookTitle());
            ps.setString(3, book.getPublisherName());
            ps.setString(4, book.getYearPublished());
            ps.setInt(5, book.getNumberPages());
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBook(Books book) 
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("UPDATE BOOKS SET PUBLISHERNAME=?, YEARPUBLISHED=?, NUMBEROFPAGES=? WHERE BOOKTITLE=? AND GROUPNAME=?");
            ps.setString(1, book.getPublisherName());
            ps.setString(2, book.getYearPublished());
            ps.setInt(3, book.getNumberPages());
            ps.setString(4, book.getBookTitle());
            ps.setString(5, book.getGroupName());
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateBookPublisher(String newPublisher, String oldPublisher) 
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("UPDATE BOOKS SET PUBLISHERNAME=? WHERE PUBLISHERNAME=?");
            ps.setString(1, newPublisher);
            ps.setString(2, oldPublisher);
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Books book) 
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM BOOKS WHERE BOOKTITLE=?");
            ps.setString(1, book.getBookTitle());
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    
     private Books extractBookFromResultSet(ResultSet rs) throws SQLException
    {
        Books book = new Books();
        
        book.setGroupName(rs.getString("GROUPNAME"));
        book.setBookTitle(rs.getString("BOOKTITLE"));
        book.setPublisherName(rs.getString("PUBLISHERNAME"));
        book.setYearPublished(rs.getString("YEARPUBLISHED"));
        book.setNumberPages(rs.getInt("NUMBEROFPAGES"));
        
        return book;
    }
    
}
