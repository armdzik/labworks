/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duomstrprojektas;

import java.util.ArrayList;

/**
 *
 * @author arminas.dzikas
 */
public class DynamicArrayPerformance {
    
    private static final int UPPER_ITERATIONS = 10;
    private static final int LOWER_ITERATIONS = 1000;
    
    public DynamicArrayPerformance(){
        this.addWarmupComparison();
        this.addComparison();
        
        this.getWarmupComparison();
        this.getComparison();
        
        this.indexOfWarmupComparison();
        this.indexOfComparison();
        
        this.removeWarmupComparison();
        this.removeComparison();
        
        this.containsWarmupComparison();
        this.containsComparison();
        
        this.addAtWarmupComparison();
        this.addAtComparison();
        
    }
    
    private void addComparison(){
        System.out.println(" =============== Add metodas — DynamicArray vs ArrayList =============== ");
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        double dynamicArrayTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.add(i + j);
            }
            dynamicArrayTime += (System.nanoTime() - startTime); 
            System.out.println("DynamicArray| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (dynamicArrayTime/1e6));
        }
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        double arrayListTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.add(i + j);
            }
            arrayListTime += (System.nanoTime() - startTime); 
            System.out.println("ArrayList| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (arrayListTime/1e6));
        }
        this.printConclusions(dynamicArrayTime, arrayListTime);
    }
    
    private void addWarmupComparison(){
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.add(i + j);
            }  
        }
        System.out.println("||DynamicArray warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.add(i + j);
            }
        }
        System.out.println("||ArrayList warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
    }
    
    private void printConclusions(double dynamicArrayTime, double arrayListTime){
        if (dynamicArrayTime < arrayListTime){
            System.out.println("—|/|   DynamicArray greitesnis per: " + ((arrayListTime - dynamicArrayTime)/1e6)+ " ms");
        } else {
            System.out.println("—|/|   ArrayList greitesnis per: " + ((dynamicArrayTime - arrayListTime)/1e6)+ " ms");
        }
        System.out.println();
    }
    
    private DynamicArray<Integer> formIntegerDynamicArray(){
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.add(i + j);
            }
        }
        return dynamicArray;
    }
    
    private ArrayList<Integer> formIntegerArrayList(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.add(i + j);
            }
        }
        return arrayList;
    }
    
    private void getWarmupComparison(){
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.get(i + j);
            }
        }
        System.out.println("||DynamicArray warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.get(i + j);
            }
        }
        System.out.println("||ArrayList warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
    }
    
    
    private void getComparison(){
        System.out.println(" =============== Get metodas — DynamicArray vs ArrayList =============== ");
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        double dynamicArrayTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.get(i + j);
            }
            dynamicArrayTime += (System.nanoTime() - startTime); 
            System.out.println("DynamicArray| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (dynamicArrayTime/1e6));
        }
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        double arrayListTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.get(i + j);
            }
            arrayListTime += (System.nanoTime() - startTime); 
            System.out.println("ArrayList| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (arrayListTime/1e6));
        }
        this.printConclusions(dynamicArrayTime, arrayListTime);
    }
    
    
    private void indexOfWarmupComparison(){
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.indexOf(i + j);
            }
        }
        System.out.println("||DynamicArray warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.indexOf(i + j);
            }
        }
        System.out.println("||ArrayList warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
    }
       private void removeWarmupComparison(){
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                dynamicArray.remove((Object)value);
            }
        }
        System.out.println("||DynamicArray warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                arrayList.remove((Object)value);
            }
        }
        System.out.println("||ArrayList warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
    }
    
    private void indexOfComparison(){
        System.out.println(" =============== Indeksas — DynamicArray vs ArrayList =============== ");
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        double dynamicArrayTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                dynamicArray.indexOf(i + j);
            }
            dynamicArrayTime += (System.nanoTime() - startTime); 
            System.out.println("DynamicArray| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (dynamicArrayTime/1e6));
        }
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        double arrayListTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                arrayList.indexOf(i + j);
            }
            arrayListTime += (System.nanoTime() - startTime); 
            System.out.println("ArrayList| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (arrayListTime/1e6));
        }
        this.printConclusions(dynamicArrayTime, arrayListTime);
    }
    
 
    
    private void removeComparison(){
        System.out.println(" =============== Remove metodas — DynamicArray vs ArrayList =============== ");
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        double dynamicArrayTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                dynamicArray.remove((Object)value);
            }
            dynamicArrayTime += (System.nanoTime() - startTime); 
            System.out.println("DynamicArray| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (dynamicArrayTime/1e6));
        }
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        double arrayListTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                arrayList.remove((Object)value);
            }
            arrayListTime += (System.nanoTime() - startTime); 
            System.out.println("ArrayList| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (arrayListTime/1e6));
        }
        this.printConclusions(dynamicArrayTime, arrayListTime);
    }
    
    private void containsWarmupComparison()
    {
    System.out.println("```Contains metodas - warmup```");
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                dynamicArray.contains(value);
            }
        }
        System.out.println("||DynamicArray warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );       
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                arrayList.contains(value);
            }
        }
        System.out.println("||ArrayList warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
    }
    
       private void addAtWarmupComparison(){
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                dynamicArray.add((i+j),value);
            }
        }
        System.out.println("||DynamicArray warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                arrayList.add((i+j),value);
            }
        }
        System.out.println("||ArrayList warmup baigtas. Padarytos iteracijos kartai: " + (LOWER_ITERATIONS*UPPER_ITERATIONS) );
    }
    
    
    private void containsComparison(){
        System.out.println(" =============== Contains metodas — DynamicArray vs ArrayList =============== ");
          DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        double dynamicArrayTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                dynamicArray.contains(value);
            }
            dynamicArrayTime += (System.nanoTime() - startTime); 
            System.out.println("DynamicArray| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (dynamicArrayTime/1e6));
        }
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        double arrayListTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                arrayList.contains(value);
            }
            arrayListTime += (System.nanoTime() - startTime); 
            System.out.println("ArrayList| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (arrayListTime/1e6));
        }
        this.printConclusions(dynamicArrayTime, arrayListTime);
    }
    
 
    
    private void addAtComparison(){
        System.out.println(" =============== Add su indeksu metodas — DynamicArray vs ArrayList =============== ");
        DynamicArray<Integer> dynamicArray = this.formIntegerDynamicArray();
        double dynamicArrayTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                dynamicArray.add((i+j),value);
            }
            dynamicArrayTime += (System.nanoTime() - startTime); 
            System.out.println("DynamicArray| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (dynamicArrayTime/1e6));
        }
        ///
        ArrayList<Integer> arrayList = this.formIntegerArrayList();
        double arrayListTime = 0;
        for (int i = 0; i < UPPER_ITERATIONS; i++){
            double startTime = System.nanoTime();
            for (int j = 0; j < LOWER_ITERATIONS; j++){
                Integer value = i + j;
                arrayList.add((i+j),value);
            }
            arrayListTime += (System.nanoTime() - startTime); 
            System.out.println("ArrayList| Iteracijos:" + (i + 1)*LOWER_ITERATIONS + " Laikas:" + 
                    (arrayListTime/1e6) + "ms");
        }
        this.printConclusions(dynamicArrayTime, arrayListTime);
    }
    
}
