package invoicesystem;
public class Item {
    private String itemName;
    private float itemRate;
    private Item next;
    
    void setItemName(String name)
    {
        this.itemName=name;
    }
    
    void setItemRate(float rate)
    {
        this.itemRate=rate;
    }
    
    void setNextItem(Item it){
        this.next=it;
    }
            
    String getItemName()
    {
        return this.itemName;
    }
    
    float getItemRate(){
        return this.itemRate;
    }
    
    Item getNextItem()
    {
        return this.next;
    }
}
