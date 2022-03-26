/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: aremu
 *
 * Created on 18 de marzo de 2022, 17:02
 * Actualizado 22 de marzo 2022 01:02
 * Actualizado 25 de marzo 2022 01:02
 * Actualizado 25 de marzo 2022 01:02 -Remotamente
 
 */


#include <stdlib.h>
#include <stdio.h>
#include <iostream>
#include<time.h>
#include<ctime>
//#include<windows.h>
#include <bits/stdc++.h>

using namespace std;
#define maxDigitos 10    //establece el tam max de la cadena que contiene el archivo
//#define posicionesArr 2000000//estable el tamaño maximo del arreglo a buscar
#define posicionesArr 1000000//estable el tamaño maximo del arreglo a buscar
#define Datos "./datos.txt"
int arr[posicionesArr];


using namespace std;
//#define maxDigitos 10  //establece el tam max de de digitos por cada numero
//#define posicionesArr 10000000//estable el tamaño maximo del arreglo a ordenar


//realiza el swap 
void swap(int *xp, int *yp){ 
    int temp = *xp; 
    *xp = *yp; 
    *yp = temp; 
} 
  
void selectionSort(int arr[], int n){ 
    int i, j, min_idx; 
  
    for (i = 0; i < n-1; i++) 
    { 
        // encuentra el minimo en el arreglo
        min_idx = i; 
        for (j = i+1; j < n; j++) 
        if (arr[j] < arr[min_idx]) 
            min_idx = j; 
  
        // hace Swap para ir ordenando de menor a mayor
        swap(&arr[min_idx], &arr[i]); 
    } 
} 


void vaciarArr(){
    for(int i; i<posicionesArr; i++){
        arr[i]=0;
    }
}

char *imprimirHoraActual()
{
	time_t rawtime;

  time (&rawtime);
  //printf ("\nLa hora actual: %s", ctime (&rawtime));
  char *Hora=ctime (&rawtime);
  return Hora;
}

void leeArchivo(){
    int renglon =0;
    FILE* fichero;
    char str[maxDigitos]; //vaariable de cadena para leer linea x linea el archivo
        //abrimos el archivo de forma secuencial
        //fichero = fopen("C:\\Users\\aremu\\Documents\\NetBeansProjects\\Selection\\datos.txt", "r"); 
		fichero = fopen("./datos.txt", "r"); 
        if(fichero!=NULL){
            //el archivo se logro abrir con exito y empieza lectura
            
            while(fichero!=NULL && renglon < posicionesArr){
            //baremos el archivo hasta llegar al final o que no se exceda el numero de renglones
                fgets(str, maxDigitos, fichero);
                arr[renglon]=atoi(str); //convertimos la cadena del renglon en un int
                renglon++;
            }
        }
        else{
            printf("archivo datos.txt, no se pudo abrir");
        }
        fclose(fichero);//cerramos el archivo
}

int CreaArchivo(){
    
    int valor;
    int renglon =0;
    int num;
    int limiteInferior, limiteSuperior;
    FILE* fichero;
    char str[maxDigitos]; //variable de cadena para escribir linea x linea el archivo
    
    /*************************************************************************/
    puts("/****************************************************/");
    printf("Indica el limite inferior a generar: ");
    scanf("%d", &limiteInferior);
    printf("Indica el limite superior a generar: ");
    scanf("%d", &limiteSuperior);
    puts("/****************************************************/");
    /*************************************************************************/
        //abrimos el archivo de forma secuencial
		//./datos.txt
        //fichero = fopen("C:\\Users\\aremu\\Documents\\NetBeansProjects\\Selection\\datos.txt", "w"); 
		fichero = fopen(Datos, "w"); 
        if(fichero!=NULL){
            //el archivo se logro abrir con exito y empieza lectura
            srand(time(NULL));
            //ciclo para generar numeros random desde el 0 en adelante
            for(int c = 0; c < posicionesArr; c++){
                num = limiteInferior + rand() % (limiteSuperior - limiteInferior);  //numeros random del 0-1000
                sprintf(str, "%d\n", num);
                //itoa(num, str, maxDigitos);// convierte el numero en string se cambia porque no es compatible con todos los compiladores
                fputs(str, fichero);   //envia string al fichero
            }
            fclose(fichero);//cerramos el archivo
            return 1;
        }
        else{
            printf("archivo %s, no pudo ser creado",Datos);
            fclose(fichero);//cerramos el archivo
            return 0;
        }
        
}


//imprime los numeros del arreglo
void imprimirNumeros(){
    for(auto const& value : arr)
        cout << value << "; ";
    cout << endl;
}
  
// Driver program to test above functions 
int main() { 
    unsigned inicio, fin; //variable para almacenar la hora de proceso
    system("clear");//Borrado de pantalla
    vaciarArr();    //inicializa arreglo
    int ResArchivo;
	ResArchivo= CreaArchivo();  //creamos fichero
    
     //Se procesa solo cuando el arcivo se creo con exito
    if (ResArchivo == 1) { 
        inicio=clock(); // inicio del tiempo del proceso a cronometrar
    
        leeArchivo();   //barremos arrchivo

        //puts("Numeros random con los que se lleno el archivo datos.txt");
        //imprimirNumeros();

        char *HoraInicial=imprimirHoraActual(); //Almacena en un apuntador la hora actual
        puts("\n\n******************************************************************\n");
        puts("Numeros ordenados por al algortimo de seleccion");

        int n = sizeof(arr)/sizeof(arr[0]); 
        selectionSort(arr, n); 
        fin=clock(); //fin del tiempo del proceso
        cout << "Arreglo ordenado: \n"; 
        imprimirNumeros();

        puts("\n fin del algortimo de seleccion");


        //finalmente colocamos los tiempos que le tomaron al algoritmo de seleccion en ejecutarse

        double time = (double(fin-inicio)/CLOCKS_PER_SEC);
        printf("\nHora Inicial:%s",HoraInicial);
        cout<<"\n Tiempo transcurrido del inicio: "<<inicio;
        cout<<"\n Tiempo transcurrido del Fin: "<<fin;
        cout<<"\n Tiempo transcurrido del proceso: "<<time<<" segundos";
        printf("\nHora Final:%s",imprimirHoraActual());
    }
    return 0; 
}
  
