/****************************************************************
 * Šioje klasėje tiriame bazinių klasių Stack ir Queue metodus
 * 
 * Visos demonstracinės klasės turi main metodą ir vykdomos Run File (Shift+F6)
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.console;
import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;
import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode;
import extendsFX.BaseConsole;
import java.util.*;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.BinaryNode;

public class Demo2_StackQueue extends BaseConsole{
    final Stack<String> stack = new Stack<>();
    final Queue<String> queue = new ArrayDeque<>();
    final List<String>  list  = new LinkedList<>();
    final HashSet<String> hash = new HashSet<String>();
    final Vector<String> atsaka = new Vector<String>();
    private int click;
    
    private void pushStack() {
        stack.push("e" + click++);
        printLn(stack.toString());
    }
    private void popStack() {
        try {  // gaudome tuščio steko exception
            String t = stack.pop();
            queue.add(t);
            printLn(stack.toString()); //Steko išspausdinimas
            ta1.appendText(queue.toString()+nL);
        } catch (EmptyStackException e){
            printLn("ERROR!!! Operacija pop() su tuščiu steku negalima");
        }
    }
    private void poolQueue() {
        queue.poll();
        ta1.appendText(queue.toString()+nL);
    }
    private void addToListMid() {
        list.add(list.size()/2, "e" + click++);
        ta1.appendText(list.toString()+nL);
    }
    private void vector()
    {
        atsaka.add(click++ + "");
        ListIterator iterate = atsaka.listIterator();
        while (iterate.hasNext())
        {
            String tmp = iterate.next().toString();
            ta2.appendText(tmp);

        }
        atsaka.listIterator();
        ta2.appendText(atsaka.toString());
    }
    private void hash()
    {
        hash.add(click++ + "");
        Iterator<String> temp = hash.iterator();
       while (temp.hasNext())
       {
           String tmpp = temp.next();
           ta1.appendText("[" + tmpp + "]");
       }
    }
    @Override
    public void createControls() {
        super.createControls();
        addButton("push", action -> pushStack());       
        addButton("pop",  action -> popStack() );       
        addButton("pool", action -> poolQueue());       
        addButton("addToMid", action -> addToListMid());
        addButton("vectrIter", action -> vector());
        addButton("hashIter", action -> hash());
    }
// UŽDUOTIS: ištirkite kitų struktūrų metodus
// https://www.geeksforgeeks.org/introduction-to-data-structures-10-most-commonly-used-data-structures/    
    @Override
    public void start(Stage stage) throws Exception {
        setTextAreas("green", "red", 350, 400);
        stage.setTitle("Eksperimentai su Stack'u ir Queue (VirP)");
        super.start(stage);  
    }
    public static void main(String[] args) {
        launch();
    }
}
