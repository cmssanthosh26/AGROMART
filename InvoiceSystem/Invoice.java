package invoicesystem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class Invoice {
    private int invoiceNumber;
    private int itemCount;
    private ArrayList<Item> a;
    private ArrayList<Integer> quantity;
    private float subTotal;
    private float total;
    private float discount;
    private Invoice next;
    private final String dateTime;
    private final LocalDateTime localDateTime;
    private final DateTimeFormatter formatDateTime;
    private static final Scanner scanner=new Scanner(System.in);
    
    public Invoice(int no)
    {
        this.invoiceNumber=no;
        this.discount=5;
        this.a = new ArrayList<>();
        this.quantity=new ArrayList<>();
        this.localDateTime = LocalDateTime.now();
        this.formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyy  HH:mm:ss");
        this.dateTime = this.localDateTime.format(formatDateTime);
    }
    
    void setNextInvoice(Invoice i)
    {
        this.next=i;
    }
    
    Invoice getNextInvoice()
    {
        return this.next;
    }
    
    int getInvoiceNumber()
    {
        return this.invoiceNumber;
    }
    
    float getSubTotal()
    {
        return this.subTotal;
    }
    
    float getDiscount(){
        return this.discount;
    }
    
    int getItemCount()
    {
        return this.itemCount;
    }
    
    void addItemToInvoice(Item item)
    {
        while(true)
        {
            Item temp=item;
            System.out.println("Menu List");
            while(temp!=null)
            {
                System.out.println(temp.getItemName()+"\t\t"+temp.getItemRate());
                temp=temp.getNextItem();
            }
            System.out.println("Select Item Name");
            scanner.nextLine();
            String name=scanner.nextLine();
            temp=item;
            while(temp!=null)
            {
                if(temp.getItemName().equalsIgnoreCase(name))
                    break;
                temp=temp.getNextItem();
            }
            System.out.println("Enter Quantity");
            int quan=scanner.nextInt();
            if(temp!=null)
            {
                this.subTotal += quan * temp.getItemRate();
                this.itemCount++;
                a.add(temp);
                this.quantity.add(quan);
            }
            System.out.println("Do you want to enter one more item enter 1 otherwise enter 0");
            int k=scanner.nextInt();
            if(k==0)
            {
                break;
            }
        }
    }
    void displayInvoice()
    {
        System.out.println("Date & Time : "+this.dateTime);
        System.out.println();
        //System.out.println("S.No\tItem Name\t Rate \t Quantity \t Amount");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s", "S.No","Item Name","Rate","Quantity","Amount");
        System.out.println();
        
        Iterator itr=a.iterator();
        Iterator itrQuantity=this.quantity.iterator();
        int count=1;
        while(itr.hasNext() && itrQuantity.hasNext())
        {
            Item i=(Item)itr.next();
            int quantityValue=(Integer)itrQuantity.next();
            //System.out.println(count++ + "\t" + i.getItemName() + "\t" + i.getItemRate() + "\t 1 \t"+ i.getItemRate());
            System.out.printf("%-15d%-15s%-15f%-15d%-15f",count++ , i.getItemName() , i.getItemRate() ,quantityValue, quantityValue * i.getItemRate());
            System.out.println();
        }
        System.out.println();
        System.out.println("Total number of Items :"+this.itemCount);
        System.out.println("Subtotal :"+this.subTotal);
        System.out.println("Discount :"+this.discount);
        System.out.println("Total Amount :"+this.subTotal*((100-this.discount)/100));
    }    
}
