package invoicesystem;
import java.util.LinkedHashMap;
public class InvoiceSystem 
{
    public static void main(String[] args) 
    {
        String[] item={"Dosa","Poori","Rice","Briyani"};
        float[] itemRate={10,20,40,70};
        InvoiceControl ic=new InvoiceControl();
        ic.initializeItem(item,itemRate);
        ic.startExecution();
    }    
}
