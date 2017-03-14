
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
public class PublishersDAOImpl implements PublishersDAO
{
    @Override
    public Set getAllPublishers()
    {
        Connection connection = null; 
        Statement stmt = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PUBLISHERS");
            Set publishers = new HashSet();
            while(rs.next())
            {
                Publishers publisher = extractPublisherFromResultSet(rs);
                publishers.add(publisher);
            }
            stmt.close();
            connection.close();
            return publishers;
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
    public Publishers getPublisher(String name)
    {
        PreparedStatement ps = null;
        Connection connection = null;
        String selectPublisher = "SELECT * FROM PUBLISHERS WHERE PUBLISHERNAME = ?";
        try
        {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(selectPublisher);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractPublisherFromResultSet(rs);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean insertPublisher(Publishers publisher)
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PUBLISHERS VALUES (?,?,?,?)");
            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getAddress());
            ps.setString(3, publisher.getPhone());
            ps.setString(4, publisher.getEmail());
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
    public boolean updatePublisher(Publishers publisher)
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("UPDATE PUBLISHERS SET PUBLISHERADDRESS=?, PUBLISHERPHONE=?, PUBLISHEREMAIL=? WHERE PUBLISHERNAME=?");
            ps.setString(1, publisher.getAddress());
            ps.setString(2, publisher.getPhone());
            ps.setString(3, publisher.getEmail());
            ps.setString(4, publisher.getName());
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
    public boolean deletePublisher(Publishers publisher)
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PUBLISHERS WHERE PUBLISHERNAME=?");
            ps.setString(1, publisher.getName());
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
        
    private Publishers extractPublisherFromResultSet(ResultSet rs) throws SQLException
    {
        Publishers publisher = new Publishers();
        
        publisher.setName(rs.getString("PUBLISHERNAME"));
        publisher.setAddress(rs.getString("PUBLISHERADDRESS"));
        publisher.setPhone(rs.getString("PUBLISHERPHONE"));
        publisher.setEmail(rs.getString("PUBLISHEREMAIL"));
        
        return publisher;
    }
}
