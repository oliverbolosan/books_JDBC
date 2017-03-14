/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olive
 */
public class Publishers 
{
    private String publisherName;
    private String publisherAddress;
    private String publisherPhone;
    private String publisherEmail;
    
    public Publishers()
    {
        
    }
    
    public Publishers(String address, String phone, String email)
    {
        this.publisherAddress = address;
        this.publisherPhone = phone;
        this.publisherEmail = email;
    }
    
    public Publishers(String name, String address, String phone, String email)
    {
        this.publisherName = name;
        this.publisherAddress = address;
        this.publisherPhone = phone;
        this.publisherEmail = email;
    }
    
    public String getName()
    {
        return publisherName;
    }
    
    public void setName(String name)
    {
        this.publisherName = name;
    }
    
    public String getAddress()
    {
        return publisherAddress;
    }
    
    public void setAddress(String address)
    {
        this.publisherAddress = address;
    }
    
    public String getPhone()
    {
        return publisherPhone;
    }
    
    public void setPhone(String phone)
    {
        this.publisherPhone = phone;
    }
    
    public String getEmail()
    {
        return  publisherEmail;
    }
    
    public void setEmail(String email)
    {
        this.publisherEmail = email;
    }
}
