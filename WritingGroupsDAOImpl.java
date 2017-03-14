
import java.sql.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olive
 */
public class WritingGroupsDAOImpl implements WritingGroupsDAO
{
    @Override
    public Set getAllWritingGroups()
    {
        Connection connection = null;
        Statement stmt = null;
        try
        {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM WRITINGGROUPS");
            Set groups = new HashSet();
            while(rs.next())
            {
                WritingGroups group = extractGroupFromResultSet(rs);
                groups.add(group);
            }
            stmt.close();
            connection.close();
            return groups;
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
    public WritingGroups getWritingGroup(String groupName)
    {
        PreparedStatement ps = null;
        Connection connection = null;
        String selectGroup = "SELECT * FROM WRITINGGROUPS WHERE GROUPNAME = ?";
        try
        {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(selectGroup);
            ps.setString(1, groupName);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractGroupFromResultSet(rs);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean insertWritingGroup(WritingGroups group)
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO WRITINGGROUPS VALUES (?,?,?,?)");
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getHeadWriter());
            ps.setString(3, group.getYearFormed());
            ps.setString(4, group.getSubject());
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
    public boolean updateWritingGroup(WritingGroups group)
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("UPDATE WRITINGGROUPS SET HEADWRITER=?, YEARFORMED=?, SUBJECT=? WHERE GROUPNAME=?");
            ps.setString(1, group.getHeadWriter());
            ps.setString(2, group.getYearFormed());
            ps.setString(3, group.getSubject());
            ps.setString(4, group.getGroupName());
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
    public boolean deleteWritingGroup(WritingGroups group)
    {
        Connection connection = ConnectionFactory.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM WRITINGGROUPS WHERE GROUPNAME=?");
            ps.setString(1, group.getGroupName());
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
        
    private WritingGroups extractGroupFromResultSet(ResultSet rs) throws SQLException
    {
        WritingGroups group = new WritingGroups();
        
        group.setGroupName(rs.getString("GROUPNAME"));
        group.setHeadWriter(rs.getString("HEADWRITER"));
        group.setYearFormed(rs.getString("YEARFORMED"));
        group.setSubject(rs.getString("SUBJECT"));
        
        return group;
    }
}
