
import java.util.*;
public class ONPv3 {
public static String[][] priorytet = new String[][]{{"("},{"+","-",")"},{"*","/"},{"^","sqrt"},{"sin","cos","tg","ctg","neg"}};
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
for(int n=0;n<wejs.length;n++){
 
    if(sprawdz(wejs[n])==-1){ // jesli zmienna
    wyjsc[b+1] = wejs[n];
    b++;
    }
    else if(a==-1 && sprawdz(wejs[n])>=0){//jesli spojnik i pusty stos 
    stos[0]=wejs[n];
    a++;
    }
    else if(a>=0 && sprawdz(wejs[n])>=0 && sprawdz(wejs[n])<=sprawdz(stos[a])){//jesli spojnik ma mniejszy prio 
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
    else if(a>=0 && sprawdz(wejs[n])>=0 && sprawdz(wejs[n])>sprawdz(stos[a])){//jesli spojnik ma wiekszy prio 
    stos[a+1]=wejs[n];
    a++;
    }
    while(a>0 && sprawdz(stos[a-1])>=sprawdz(stos[a]) && stos[a].compareTo("(")!=0){
    wyjsc[b+1] = stos[a-1];
    stos[a-1]=stos[a];
    stos[a]=null;
    b++; 
    a--;
    }
    if(a>=1 && stos[a-1].compareTo("(")==0 && stos[a].compareTo(")")==0){
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
return wyjsc;
}/*
public static void main(String[] args) {
String[] abc,aaa;
        //aaa = new String[];
//{"a","+","b","*","3","-","z","^","2"};
//{"3","*","(","a","+","5","*","b",")","-","3","*","x","^","2"};
//{"(","sin","a","+","3","*","y","*","z",")","/","(","NEG","a","+","b","*","c","^","3",")"};
/*
Scanner klawiatura = new Scanner(System.in);
System.out.print("#####################\n##To ONP conversion##\n#####################\nby Dominik Szczypta.\nDozwolone znaki: ( + - ) * / ^ SQRT sin cos tg ctg NEG\nEnter the equation separating every element:\n");
String[] kl = klawiatura.nextLine().split(" ");
abc = new String[100];
abc = onp(kl);
int m = 0;
while(abc[m]!=null){
System.out.print(abc[m]+" ");
m++;
}

}*/
}
