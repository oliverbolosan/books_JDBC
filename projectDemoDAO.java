/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olive
 */
import java.util.*;
import java.io.*;

public class projectDemoDAO 
{
    static final String displayFormat="%-25s%-25s%-25s%-25s\n";
    static final String bookDisplayFormat="%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n";
    public static void main(String[] args)
    {
        WritingGroupsDAO groupDAO = new WritingGroupsDAOImpl();
        PublishersDAO publisherDAO = new PublishersDAOImpl();
        BooksDAO bookDAO = new BooksDAOImpl();
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        int select = 0;
        while(running)
        {
            printMenu();
            select = scan.nextInt();
            scan.nextLine();
            switch(select)
            {
                case 1: 
                    System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
                    for(WritingGroups allGroups : groupDAO.getAllWritingGroups())
                    {
                        System.out.printf(displayFormat, allGroups.getGroupName(), allGroups.getHeadWriter(), allGroups.getYearFormed(), allGroups.getSubject());
                    }
                    break;
                case 2:
                    System.out.print("Enter a Group Name: ");
                    String searchGroup = scan.nextLine();
                    if(groupDAO.getWritingGroup(searchGroup) == null)
                    {
                        System.out.println("Invalid Group Name.");
                        break;
                    }
                    else
                    {
                        WritingGroups group = groupDAO.getWritingGroup(searchGroup);
                        System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
                        System.out.printf(displayFormat, group.getGroupName(), group.getHeadWriter(), group.getYearFormed(), group.getSubject());
                    }
                    break;
                case 3:
                    System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
                    for(Publishers allPublishers : publisherDAO.getAllPublishers())
                    {
                        System.out.printf(displayFormat, allPublishers.getName(), allPublishers.getAddress(), allPublishers.getPhone(), allPublishers.getEmail());
                    }
                    break;
                case 4:
                    System.out.print("Enter a Publisher: ");
                    String searchPublisher = scan.nextLine();
                    if(publisherDAO.getPublisher(searchPublisher) == null)
                    {
                        System.out.println("Invalid Publisher Name.");
                        break;
                    }
                    else
                    {
                        Publishers publisher = publisherDAO.getPublisher(searchPublisher);
                        System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
                        System.out.printf(displayFormat, publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getEmail());
                    }
                    break;
                case 5:
                    System.out.printf(displayFormat, "Group Name", "Book Title", "Publisher Name", "Year Published", "Number of Pages");
                    for(Books allBooks : bookDAO.getAllBooks())
                    {
                        System.out.printf(displayFormat, allBooks.getGroupName(), allBooks.getBookTitle(), allBooks.getPublisherName(), allBooks.getYearPublished(), allBooks.getNumberPages());
                    }
                    break;
                case 6:
                    System.out.print("Enter a title of a book: ");
                    String searchBook = scan.nextLine();
                    if(bookDAO.getBook(searchBook) == null)
                    {
                        System.out.println("Invalid book title.");
                        break;
                    }
                    Books book = bookDAO.getAllBookDetails(searchBook);
                    String writer = null;
                    String formed = null;
                    String sub = null;
                    String addr = null;
                    String phone = null;
                    String email = null;
                    for(WritingGroups groupSet : book.getGroup())
                    {
                        writer = groupSet.getHeadWriter();
                        formed = groupSet.getYearFormed();
                        sub = groupSet.getSubject();
                    }
                    for(Publishers pubSet : book.getPublisher())
                    {
                        addr = pubSet.getAddress();
                        phone = pubSet.getPhone();
                        email = pubSet.getEmail();
                    }
                    System.out.printf(bookDisplayFormat, "Book Title", "Number of Pages", "Group Name", "Head Writer", "Year Formed", "Subject", "Year Published", "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
                    System.out.printf(bookDisplayFormat, book.getBookTitle(), book.getNumberPages(), book.getGroupName(), writer, formed, sub, book.getYearPublished(), book.getPublisherName(), addr, phone, email);
                    break;
                case 7:
                    boolean existingGroup = false;
                    boolean existingPublisher = false;
                    String newBookGroup;
                    String newBookPublisher;
                    String newBookTitle;
                    String newBookPublishYear;
                    int newBookPageNum;
                    System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
                    for(WritingGroups allGroups : groupDAO.getAllWritingGroups())
                    {
                        System.out.printf(displayFormat, allGroups.getGroupName(), allGroups.getHeadWriter(), allGroups.getYearFormed(), allGroups.getSubject());
                    }
                    System.out.print("Insert a book under group: ");
                    newBookGroup = scan.nextLine();
                    for(WritingGroups allGroups : groupDAO.getAllWritingGroups())
                    {
                        if(allGroups.getGroupName().equals(newBookGroup))
                        {
                            existingGroup = true;
                        }
                    }
                    System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
                    for(Publishers allPublishers : publisherDAO.getAllPublishers())
                    {
                        System.out.printf(displayFormat, allPublishers.getName(), allPublishers.getAddress(), allPublishers.getPhone(), allPublishers.getEmail());
                    }
                    System.out.print("Insert a book under publisher name: ");
                    newBookPublisher = scan.nextLine();
                    for(Publishers allPublishers : publisherDAO.getAllPublishers())
                    {
                        if(allPublishers.getName().equals(newBookPublisher))
                        {
                            existingPublisher = true;
                        }
                    }
                    if(existingPublisher && existingGroup)
                    {
                        System.out.print("Insert a book title: ");
                        newBookTitle = scan.nextLine();
                        System.out.print("Insert year of publication: ");
                        newBookPublishYear = scan.nextLine();
                        System.out.print("Insert number of pages: ");
                        newBookPageNum = scan.nextInt();
                        Books newBook = new Books();
                        newBook.setBookTitle(newBookTitle);
                        newBook.setGroupName(newBookGroup);
                        newBook.setPublisherName(newBookPublisher);
                        newBook.setYearPublished(newBookPublishYear);
                        newBook.setNumberPages(newBookPageNum);
                        bookDAO.insertBook(newBook);
                    }
                    else
                    {
                        System.out.println("Invalid group or publisher");
                    }
                    break;
                case 8:
                    System.out.print("Enter a new publisher name: ");
                    String newPublisherName = scan.nextLine();
                    System.out.print("Enter a new publisher address: ");
                    String newPublisherAddress = scan.nextLine();
                    System.out.print("Enter a new publisher phone: ");
                    String newPublisherPhone = scan.nextLine();
                    System.out.print("Enter a new publisher email: ");
                    String newPublisherEmail = scan.nextLine();
                    publisherDAO.insertPublisher(new Publishers(newPublisherName, newPublisherAddress, newPublisherPhone, newPublisherEmail));
                    System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
                    for(Publishers allPublishers : publisherDAO.getAllPublishers())
                    {
                        System.out.printf(displayFormat, allPublishers.getName(), allPublishers.getAddress(), allPublishers.getPhone(), allPublishers.getEmail());
                    }
                    System.out.print("Insert " + newPublisherName + " and update: ");
                    String oldPublisherName = scan.nextLine();
                    for(Publishers allPublishers : publisherDAO.getAllPublishers())
                    {
                        if(allPublishers.getName().equals(oldPublisherName))
                        {
                            bookDAO.updateBookPublisher(newPublisherName, oldPublisherName);
                        }
                        else
                        {
                            System.out.println("Invalid Publisher Name");
                        }
                    }
                    break;
                case 9:
                    System.out.print("Delete book by title: ");
                    String deleteBookByTitle = scan.nextLine();
                    if(bookDAO.getBook(deleteBookByTitle) == null)
                    {
                        System.out.println("Invalid book title");
                    }
                    else
                    {
                        Books removeBook = bookDAO.getBook(deleteBookByTitle);
                        bookDAO.deleteBook(removeBook);
                    }
                    
                    break;
                case 10: System.out.println("Exiting...");
                         running = false;
                         break;
                default: System.out.println("Invalid selection.");
                         break;
            }
        }
    }
    public static void printMenu()
    {
        System.out.println("----------------------------------------------------------------------\n"
                         + "1.  List all writing groups.\n"
                         + "2.  List all of the data of a specific writing group.\n"
                         + "3.  List all publishers.\n"
                         + "4.  List all of the data of a specific publisher.\n"
                         + "5.  List all book titles.\n"
                         + "6.  List all of the data of a specific book.\n"
                         + "7.  Insert a new book.\n"
                         + "8.  Insert a new publisher to replace an existing publisher.\n"
                         + "9.  Delete a book.\n"
                         + "10. Exit Program.\n"
                         + "----------------------------------------------------------------------");
    }
}
