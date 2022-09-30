package empower.empower.log;

public class Log {
    private String itemName;
    private long id;
    private String itemNotes;
    private String imagePath;

    public Log(String itemName){
        this.itemName=itemName;
    }

    public Log(long id, String itemName, String itemNotes, String imagePath){
        this.id=id;
        this.itemName=itemName;
        this.itemNotes = itemNotes;
        this.imagePath = imagePath;
    }
    
    public long getId(){
        return id;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemNotes(){
        return itemNotes;
    }

    public String getImagePath(){
        return imagePath;
    }

    public void setId(long newId){
        this.id=newId;
    }

    public void setItemName(String newItemName){
        this.itemName=newItemName;
    }

    public void setItemNotes(String newItemNotes){
        this.itemNotes = newItemNotes;
    }

    public void setImagePath(String newImagePath){
        this.imagePath = newImagePath;
    }
}
