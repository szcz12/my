//ONP bez nawiasow
import java.util.*;
public class ONPv2 {
public static String[][] priorytet = new String[][]{{"("},{"+","-",")"},{"*","/"},{"^","SQRT"},{"sin","cos","tg","ctg","NEG"}};

   
public static int sprawdz(String elem){
for(int n=0;n<priorytet.length;n++){
    for(int m=0;m<priorytet[n].length;m++){
        if(priorytet[n][m].compareTo(elem)==0){
        return n;
        }
            
    }
}
return -1;
}
///////////////////////////////////////////////////////////////////////////
public static String[] onp(String[] wejs){
String[] stos,wyjsc = new String[wejs.length];
stos = new String[wejs.length+10];
int a,b;
    a = -1;//ostatni(stos)
    b = -1;//ostatni(wyjsc)
for(int n=0;n<wejs.length;n++){
 
    if(sprawdz(wejs[n])==-1){ // jesli zmienna
    wyjsc[b+1] = wejs[n];
    b++;
    }
    else if(a==-1 && sprawdz(wejs[n])>=0){//jesli spojnik i pusty stos 
    stos[0]=wejs[n];
    a++;
    }
    else if(a>=0 && sprawdz(wejs[n])>=0 && sprawdz(wejs[n])<=sprawdz(stos[a]) && wejs[n].compareTo("(")!=0 && wejs[n].compareTo(")")!=0){//jesli spojnik ma mniejszy prio 
    wyjsc[b+1] = stos[a];
    b++;
    stos[a]=wejs[n];
    }
    else if(a>=0 && sprawdz(wejs[n])>=0 && sprawdz(wejs[n])>sprawdz(stos[a])){//jesli spojnik ma wiekszy prio 
    stos[a+1]=wejs[n];
    a++;
    }
    while(a>0 && sprawdz(stos[a])<=sprawdz(stos[a-1])){//sprawdzanie priorytetow dwóch ostatnich elmentow 
    if(stos[a].compareTo(")")!=0 && stos[a-1].compareTo("(")!=0)
    {
    wyjsc[b+1] = stos[a-1];
    stos[a-1]=stos[a];
    stos[a]=null;
    b++; 
    a--;
    }
    else
    {
    stos[a-1]=null;
    stos[a]=null; 
    a=a-2;    
    }
    }
    }
    while(a>=0){ //wypisywanie wszystkich elementów stosu od gory
    wyjsc[b+1] = stos[a];
    stos[a]=null;
    b++;
    a--;
    }
return wyjsc;
}
///////////////////////////////////////////////////////////////////////  
////////////////////////////////////////////////////////////////////////
public static int ostatni(String[] wejs){
for(int n=0;n<wejs.length;n++){
if(wejs[n]==null)return n-1;  }
return wejs.length-1;
}
//////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/*
public static void main(String[] args) {
String[] abc;
Scanner klawiatura = new Scanner(System.in);
System.out.print("ONP by Dominik Szczypta.\nEnter the Equation separating every element:\n");
String[] kl = klawiatura.nextLine().split(" ");
abc = new String[100];
abc = onp(kl);
for(int m=0;m<abc.length;m++)
System.out.print(abc[m]+" ");
    }*/
}
