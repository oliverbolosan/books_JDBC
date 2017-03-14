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
public interface WritingGroupsDAO 
{
    Set<WritingGroups> getAllWritingGroups();
    WritingGroups getWritingGroup(String groupName);
    boolean insertWritingGroup(WritingGroups group);
    boolean updateWritingGroup(WritingGroups group);
    boolean deleteWritingGroup(WritingGroups group);
}
