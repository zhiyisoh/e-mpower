package empower.empower.log;

public class Log {
    private String itemName;
    private long id;

    public Log(String itemName){
        this.itemName=itemName;
    }

    public Log(long id, String itemName){
        this.id=id;
        this.itemName=itemName;
    }

    public long getId(){
        return id;
    }

    public String getItemName(){
        return itemName;
    }

    public void setId(long newId){
        this.id=newId;
    }

    public void setItemName(String newItemName){
        this.itemName=newItemName;
    }
}
