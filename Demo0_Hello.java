/****************************************************************
 * Tai įvadinė klasė žengiant į įvykiais valdomą programavimą.
 * Event-driven programming: tai plati tema- pradedame nuo paprastų pavyzdžių
 * DAUG KLAUSIMŲ APIE JAVĄ GALIMA IŠSIAIŠKINTI, ATLIEKANT
 * SAVARANKIŠKUS BANDYMUS, TYRIMUS IR ANALIZĘ :)
 * 
 * Visos demonstracinės klasės turi main metodą ir vykdomos Run File (Shift+F6)
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis: Peržiūrėkite ir išsiaiškinkite pateiktas klases,
 * pakeiskite programinį kodą, stebėkite gautus rezultatus.
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.console;

import extendsFX.BaseConsole;
import javafx.stage.Stage;

public class Demo0_Hello extends BaseConsole{
    int clicks = 0;
    // demo pavyzdys: skaičiuojame x^2, x^3 ir fact(x)
    // nustatykite nuo kurios x reikšmės faktorialas blogai skaičiuojamas
    void calculate(){
        int x = readLastInt();
        int fact = 1;
        int check = 1;
        for(int k = 2; k <= x; k++)
            fact *= k;
        int p = 0;
        int temp = 0;
        while (p == 0) //Find the biggest positive factorial
        {
            temp++;
            check *= temp;
            if (check < 0)
                p++;
        }
        if (fact < 0) {
            print(" x=" + x + " x^2=" + x * x + " x^3=" + x * x * x + "fwjfk" + check);
            printLn(" fact=" + fact);
        }
        else
            printLn("The program stopped working!" + temp); //Factorial positive limit
    }
    public void backNumber(int report)
    {
        double angle = Math.toRadians(readLastInt()); // Converting to radians
        double temp = 0;
        if (report == 0) // Finding the variable meaning
            temp =  Math.sin(angle);
        else if (report == 1)
             temp = Math.cos(angle);
        else
            temp = Math.tan(angle);
        if (temp < 0)
            temp = 0;
        printLn("Number of angle: " + temp);
    }
// UŽDUOTIS: sudarykite naują metodą funkcijų sin, cos ir tan skaičiavimui,
//           kai duotas kampas yra nurodomas laipsniais
    @Override
    public void createControls() {
        super.createControls();    // sukuriame bazinius mygtukus
        addButton("Hello1", e -> ta1.appendText("Hello World!!! " +nL));
        addButton("Hello2", e -> ta2.appendText("Hello World!!! " 
                                 + "I can count clicks: " + ++clicks +nL));
        addButton("calc",   e -> calculate());
        addButton("sin", e -> backNumber(0));
        addButton("cos", e ->backNumber(1));
        addButton("tan", e -> backNumber(2));
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World (KTU IF)");
//         išbandykite skirtingus ekranų nustatytumus 
       // setTextAreas("green", "red", 200, 400);
//        setTextAreas("green", null, 300, 400);
//        setTextAreas(null, "red", 350, 400);
        super.start(stage);  
    }
    public static void main(String[] args) {
        launch();
    }
}