
import java.util.*;
public class ONPv4 {
public static String[][] priorytet = new String[][]{
{"("},
    {"then"},
    {"else"},
    {")"},
    {"==="},
    {"or"},
    {"and"},
    {"<",">",">=","<=","!=","=="},
    {"+","-"},
    {"*","/","NEG"},
    {"^","SQRT"}
};
///ONP z nawiasami
   
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
stos = new String[wejs.length+1];
int a,b;
    a = -1;//ostatni(stos)
    b = -1;//ostatni(wyjsc)
    boolean warunek = false;
for(int n=0;n<wejs.length;n++){
    if(b>0 && wejs[n].compareTo("then")==0){
    while(a>=0){ //wypisywanie calego stosu
    wyjsc[b+1] = stos[a];
    stos[a]=null;
    b++;
    a--;
    }
    wyjsc[b+1] = "SW";
    b++;
    warunek = true;    
    }
    else if(b>0 && wejs[n].compareTo("else")==0){
    while(a>=0){ //wypisywanie calego stosu
    wyjsc[b+1] = stos[a];
    stos[a]=null;
    b++;
    a--;
    }
    wyjsc[b+1] = "SB";
    b++;
    warunek = true;    
    
    }
    else if(sprawdz(wejs[n])==-1 && wejs[n].compareTo("if")!=0){ // jesli zmienna
    wyjsc[b+1] = wejs[n];
    b++;
    }
    else if(a==-1 && sprawdz(wejs[n])>=0 && wejs[n].compareTo("if")!=0){//jesli spojnik i pusty stos 
    stos[0]=wejs[n];
    a++;
    }
    else if(a>=0 && sprawdz(wejs[n])>=0 && sprawdz(wejs[n])<=sprawdz(stos[a]) && wejs[n].compareTo("if")!=0){//jesli spojnik ma mniejszy prio 
    if(wejs[n].compareTo("(")!=0){
    wyjsc[b+1] = stos[a];
    b++;
    stos[a]=wejs[n];
    }
    else{
    stos[a+1]=wejs[n];
    a++;
    }
    }
    else if(a>=0 && sprawdz(wejs[n])>=0 && sprawdz(wejs[n])>sprawdz(stos[a]) && wejs[n].compareTo("if")!=0){//jesli spojnik ma wiekszy prio 
    stos[a+1]=wejs[n];
    a++;
    }
    while(a>0 && sprawdz(stos[a-1])>=sprawdz(stos[a]) && stos[a].compareTo("(")!=0 && wejs[n].compareTo("if")!=0){
    wyjsc[b+1] = stos[a-1];
    stos[a-1]=stos[a];
    stos[a]=null;
    b++; 
    a--;
    }
    if(a>=1 && stos[a-1].compareTo("(")==0 && stos[a].compareTo(")")==0 && wejs[n].compareTo("if")!=0){
    stos[a-1]=null;
    stos[a]=null; 
    a=a-2;
    }
    }
    while(a>=0){ //wypisywanie calego stosu
    wyjsc[b+1] = stos[a];
    stos[a]=null;
    b++;
    a--;
    }
    
    int sb=0,koniec=0;
    for(int i=wyjsc.length-1;i>=0;i--){
    if(wyjsc[i]!=null){
    if(wyjsc[i].compareTo("SB")==0){
    wyjsc[i] = "SB"+"("+Integer.toString(koniec+1)+")"; 
    sb=i;
    }
    else if( wyjsc[i].compareTo("SW")==0){
    wyjsc[i] = "SW"+"("+Integer.toString(sb+2)+")";
    }
    
    }
    else
    koniec = i;   
    }
    /*for(int i=0;i<wyjsc.length;i++){
    if(wyjsc[])    
    }*/
return wyjsc;
}/*
public static void main(String[] args) {
String[] abc,aaa;
//
//{"if","x",">","0","then","y","/","x","else","y","/","(","x","+","1",")"};
        //aaa = new String[];
        //{"a","+","b","*","3","-","z","^","2"};
//{"3","*","(","a","+","5","*","b",")","-","3","*","x","^","2"};
//{"(","sin","a","+","3","*","y","*","z",")","/","(","NEG","a","+","b","*","c","^","3",")"};
Scanner klawiatura = new Scanner(System.in);
System.out.print("#####################\n##To ONP conversion##\n#####################\nby Dominik Szczypta.\nEnter the equation separating every element:\n");
String[] kl = klawiatura.nextLine().split(" ");
abc = new String[100];
abc = onp(kl);
int m = 0;
while(m<abc.length && abc[m]!=null ){
System.out.print(abc[m]+" ");
m++;
}
}*/
}
