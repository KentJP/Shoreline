/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL;


import shoreline.BLL.Exception.BLLException;
import shoreline.DAL.Exeption.DALException;
import shoreline.DAL.Facade.DalFacade;
import shoreline.DAL.Facade.DalFacadeDistributor;


/**
 *
 * @author Daniel
 */
public class UserManager 
{
    
    private DalFacade dalfacade; 

    /**
     *
     * @throws BLLException
     */
    public UserManager() throws BLLException 
    {
        try
        {
            dalfacade = new DalFacadeDistributor();
            
        }catch(DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex);
        }
        
    }

    
    /**
     * Validate wheater or not the Email is in the Database.
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public boolean validateLogin(String loginInfo) throws BLLException 
    {
        try
        {
            return dalfacade.validateLogin(loginInfo);
        }catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    
      
        
        
    
    
}
