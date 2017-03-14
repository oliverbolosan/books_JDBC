
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
public class Books 
{
    private String groupName;
    private String bookTitle;
    private String publisherName;
    private String yearPublished;
    private Integer numberPages;
    
    private Set<Publishers> publisher;
    private Set<WritingGroups> group;
    
    public Books()
    {
        
    }
    
    public Books(String publisherName, String yearPublished, Integer numberPages, Set<Publishers> pubSet, Set<WritingGroups> groupSet)
    {
        this.publisherName = publisherName;
        this.yearPublished = yearPublished;
        this.numberPages = numberPages;
        this.publisher = pubSet;
        this.group = groupSet;
    }
    
    public Books (String groupName, String title, String publisherName, String year, Integer numberPages, Set<Publishers> pubSet, Set<WritingGroups> groupSet)
    {
        this.groupName = groupName;
        this.bookTitle = title;
        this.publisherName = publisherName;
        this.yearPublished = year;
        this.numberPages = numberPages;
        this.publisher = pubSet;
        this.group = groupSet;
    }
    
    public String getGroupName()
    {
        return groupName;
    }
    
    public void setGroupName(String name)
    {
        this.groupName = name;
    }
    
    public String getBookTitle()
    {
        return bookTitle;
    }
    
    public void setBookTitle(String title)
    {
        this.bookTitle = title;
    }
    
    public String getPublisherName()
    {
        return publisherName;
    }
    
    public void setPublisherName(String name)
    {
        this.publisherName = name;
    }
    
    public String getYearPublished()
    {
        return yearPublished;
    }
    
    public void setYearPublished(String year)
    {
        this.yearPublished = year;
    }
    
    public Integer getNumberPages()
    {
        return numberPages;
    }
    
    public void setNumberPages(Integer pages)
    {
        this.numberPages = pages;
    }
    
    public Set<Publishers> getPublisher()
    {
        return publisher;
    }
    
    public void setPublisher(Publishers pub)
    {
        this.publisher = new HashSet();
        this.publisher.add(pub);
    }
    
    public Set<WritingGroups> getGroup()
    {
        return group;
    }
    
    public void setGroup(WritingGroups groupSet)
    {
        this.group = new HashSet();
        this.group.add(groupSet);
    }
    
}
