/**
 *
 * @author Rafael R Rodriguez
 *
 */

package fixaccountingcode;


public class UserID {
    
    private String _profile;
    private String _description;
    private Boolean _vendorNumber;

    /**
     * @return the _profile
     */
    public String getProfile() {
        return _profile;
    }

    /**
     * @param _profile the _profile to set
     */
    public void setProfile(String _profile) {
        this._profile = _profile;
    }

    /**
     * @return the _description
     */
    public String getDescription() {
        return _description;
    }

    /**
     * @param _description the _description to set
     */
    public void setDescription(String _description) {
        this._description = _description;
    }

    /**
     * @return the _vendorNumber
     */
    public Boolean getVendorNumber() {
        
        _vendorNumber = matches(this._description);
        return _vendorNumber;
    }

     
    private static Boolean matches(String description)
    {
        Boolean found = description.contains("999999");
             
        return found;
    }

}
