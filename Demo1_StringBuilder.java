/****************************************************************
 * Šioje klasėje tiriame svarbios StringBuilder klasės metodus
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

import com.sun.xml.internal.ws.util.StringUtils;
import extendsFX.BaseConsole;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.annotation.Repeatable;

public class Demo1_StringBuilder extends BaseConsole{
    final private StringBuilder sb1 = new StringBuilder();
    final private StringBuilder sb2 = new StringBuilder();
    private int clickNum1, clickNum2;
    
    @Override
    public void createControls() {
        super.createControls();    // sukuriame bazinius mygtukus
        addButton("+ta1", action -> { // papildome sb1
            sb1.append(" *** ").append(clickNum1++);
            ta1.appendText(sb1.toString()+ nL ); 
        });
        addButton("+ta2", action -> { // papildome sb2
            sb2.append(" *** ").append(clickNum2++);
            ta2.appendText(sb2.toString()+ nL );
        });
        addButton("-ta2", action -> { // sutrumpiname sb2
            int last = sb2.lastIndexOf(" *** ");
            if(last >= 0)
                sb2.delete(last, sb2.length());
            ta2.appendText(sb2.toString()+ nL );
        });
        addButton("-ta3", action -> { // sutrumpiname sb2
            int last = sb1.lastIndexOf(" *** ");
            if(last >= 0)
                sb1.delete(last, sb1.length());
            ta1.appendText(sb1.toString()+ nL );
        });
        //https://en.wikipedia.org/wiki/List_of_Unicode_characters žiūr. punktą 30
        addButton("pir#",      action -> printPyramid(pyrHeight++, "#"));
        addButton("pir\u25B2", action -> printPyramid(pyrHeight++, "\u25B2"));
        addButton("pir\u25CF", action -> printPyramid(pyrHeight++, "\u25CF"));
        addButton("fliOvPir\u2207", action -> anotherPyramid(pyrHeight++, '\u2207'));
    }
    private int pyrHeight = 1;
    private void printPyramid(int h, String fillSym){
    // konstruosime panaudodami 2 StringBuilderius: pirmas trumpėja, 2as ilgėja
        final char emptySym = ' '; // variantai '.' ':'
        printLn("=== Piramidė h=" + h);
        StringBuilder pyr1 = new StringBuilder();       
        pyr1.append(new String(new char[h]).replace('\0', emptySym)); //Null simbolius pakeičia tarpais(po char
        // simboliu skaičiaus sukūrimo)
//        for(int i = 0 ; i<h; i++)  // tas pats rezultatas kitu būdu
//            pyr1.append(' ');      // t.y. h tų pačių simbolių
        StringBuilder pyr2 = new StringBuilder(fillSym);
        for (int i = 0; i < h; i++) {
            printLn(pyr1.toString() + pyr2 + pyr1);
            pyr1.deleteCharAt(pyr1.length()-1);
            pyr2.append(fillSym).append(fillSym);
        }
    }
// sudarykite pasirinktinai metodus apverstai ar pasuktai piramidei spausdinti
    private void anotherPyramid(int h, char fillSymbol)
    {
        printLn("=== Piramidė h=" + h);
        final char emptySym = ' ';
        String temp = new String(new char[h * 2 + 1]).replace('\0', fillSymbol);
        StringBuilder flOvPir = new StringBuilder();
        StringBuilder flOvPir2 = new StringBuilder(temp);
        for (int i = h; i > -1; i--)
        {
            printLn(flOvPir + flOvPir2.toString());
            flOvPir.append(emptySym).append(emptySym);
            flOvPir2.delete(flOvPir2.length() - 2, flOvPir2.length());
        }
    }     
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Eksperimentai su StringBuilder (VirP)");
        super.start(stage);  
        initialText();
    }  
    private void initialText(){
        ta1.appendText("AAAAAAAAAA"+nL); // 10 x A
        ta1.appendText("BBBBBBBBBB"+nL); // 10 x B
        ta1.appendText("CCCCCCCCCC"+nL); // 10 x D
        ta1.appendText("DDDDDDDDDD"+nL); // 10 x D
        ta1.appendText("JJJJJJJJJJ"+nL); // 10 x D
        printLn("RRRRRRRRRR"); // 10 x R
        printLn("SSSSSSSSSS"); // 10 x S
        printLn("TTTTTTTTTT"); // 10 x T
        printLn("WWWWWWWWWW"); // 10 x W   
        // išbandykite teksto vaizdavimą su įvairiais fontais
        // https://en.wikipedia.org/wiki/Monospaced_font
//      ta1.setFont(Font.font ("Courier New", 11)); // bazinis pagal nutylėjimą
        ta1.setFont(Font.font("Lucida Console", 16));
        ta2.setFont(Font.font("Times New Roman", 14));
        // toliau proporciniai fontai
//        ta1.setFont(Font.font("Times New Roman", 11));
//        ta2.setFont(Font.font("Verdana", 11));
    }

    public static void main(String[] args) {
        launch();
    }
}
