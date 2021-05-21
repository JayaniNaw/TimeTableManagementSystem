/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

/**
 *
 * @author Adeesha
 */
public class Tags {
    
    private int ID;
    private String TagName;
    private String RelatedTag;

    public Tags(int ID, String TagName, String RelatedTag) {
        this.ID = ID;
        this.TagName = TagName;
        this.RelatedTag = RelatedTag;
    }

    public int getID() {
        return ID;
    }

    public String getTagName() {
        return TagName;
    }

    public String getRelatedTag() {
        return RelatedTag;
    }
    
}
