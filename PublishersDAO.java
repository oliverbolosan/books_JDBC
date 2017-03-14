
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
public interface PublishersDAO 
{
    Set<Publishers> getAllPublishers();
    Publishers getPublisher(String name);
    boolean insertPublisher(Publishers publisher);
    boolean updatePublisher(Publishers publisher);
    boolean deletePublisher(Publishers publisher);
}
