package cuadro2;


import java.util.ArrayList;
import java.util.Arrays;





public class cuadro{

	public static void main(String[] args) {
		int[][] inicial={{0,2,3},{1,4,6},{7,5,8}};
        int[][] solucion={{1,2,3},{4,5,6},{7,8,0}};
       nodo inicio=new nodo(inicial);
      nodo sol= buscarsolucion(inicio,solucion);
     
	}
	public static nodo  buscarsolucion(nodo inicio,int[][] solucion){
		ArrayList<nodo> expandidos=new ArrayList<nodo>();
		expandidos.add(inicio);
		int movimientos=0;
		ArrayList<nodo> visitados=new ArrayList<nodo>();
		
		while(expandidos.size()!=0){
			nodo revisar=expandidos.remove(0);
			imprimirestado(revisar.getestado());
			int i=contar(revisar.getestado());
			System.out.println("distancia"+i);
			int [] pocicioncero=pocicionnumero(revisar.getestado(),0);
			System.out.println("movimientos realizados: "+(movimientos++));
			if(Arrays.deepEquals(revisar.getestado(),solucion)){
				System.out.println("resuelto encontrado");
				return revisar;
			}
			ArrayList<nodo> hijos=new ArrayList<nodo>();
			visitados.add(revisar);
			ArrayList<nodo> creados=new ArrayList<nodo>();
			if(pocicioncero[0]!=0){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int arriba=hijo.getestado()[pocicioncero[0]-1][pocicioncero[1]];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=arriba;
				hijo.getestado()[pocicioncero[0]-1][pocicioncero[1]]=0;
				
				creados.add(hijo);
				hijos.add(hijo);
			}
			if(pocicioncero[0]!=2){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int abajo=hijo.getestado()[pocicioncero[0]+1][pocicioncero[1]];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=abajo;
				hijo.getestado()[pocicioncero[0]+1][pocicioncero[1]]=0;
				
				creados.add(hijo);
				hijos.add(hijo);
			}
			
			if(pocicioncero[1]!=0){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int izquierda=hijo.getestado()[pocicioncero[0]][pocicioncero[1]-1];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=izquierda;
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]-1]=0;
				
				creados.add(hijo);
				hijos.add(hijo);
			}
			if(pocicioncero[1]!=2){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int derecha=hijo.getestado()[pocicioncero[0]][pocicioncero[1]+1];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=derecha;
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]+1]=0;
				
				creados.add(hijo);
				hijos.add(hijo);
			}
			while(creados.size()!=0){
				nodo creado1=creados.remove(0);
				nodo creado2=creados.remove(0);
				int contarcreado1,contarcreado2;
				contarcreado1=contar(creado1.getestado());
				contarcreado2=contar(creado2.getestado());
				if(contarcreado1==contarcreado2){
					if(creados.size()==0){
						expandidos.add(creado1);
						expandidos.add(creado2);
					}else{
						creados.add(creado1);
						creados.add(creado2);
					}
						
				}else if(contarcreado1<contarcreado2){
					if(creados.size()==0){
						expandidos.add(creado1);
					}else{
						creados.add(creado1);
					}
				}else if(contarcreado1>contarcreado2){
					if(creados.size()==0){
						expandidos.add(creado2);
					}else{
						creados.add(creado2);
					}
				}
					
			}
			revisar.sethijos(hijos);
		}
		return null;
	}
	
	private static int[] pocicionnumero(int[][] estado,int x) {
		int[] posicion=new int[2];
			for(int i=0;i<estado.length;i++){
				for(int j=0;j<estado.length;j++){
					if(estado[i][j]==x){
						posicion[0]=i;
						posicion[1]=j;
						}
				}
			}
			return posicion;
			}
	private static int contar(int[][] estadoanalizar) {
		int num=0;
		ArrayList<int[]> numeros=new ArrayList<int[]>();
		ArrayList<int[]> numerossolucion=new ArrayList<int[]>();
		for(int i=1;i<9;i++){
			int [] numero=new int[2];
			numero=pocicionnumero(estadoanalizar,i);
			
		  numeros.add(numero);
		}
		int [] numero00=new int[2];
		numero00=pocicionnumero(estadoanalizar,0);
		 numeros.add(numero00);
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					int [] numerosol=new int[2];
					numerosol[0]=j;
					numerosol[1]=k;
					
					numerossolucion.add(numerosol);
				}
				}
			
			for(int i=0;i<9;i++){
				int [] numeroanalisar=new int[2];
				int [] numerosdesoluciones=new int[2];
				numeroanalisar=numeros.remove(0);
				numerosdesoluciones=numerossolucion.remove(0);
				if((numeroanalisar[0]-numerosdesoluciones[0])<0){
				num=num-(numeroanalisar[0]-numerosdesoluciones[0]);
			}else 
				num=num+(numeroanalisar[0]-numerosdesoluciones[0]);
				if((numeroanalisar[1]-numerosdesoluciones[1])<0){
					num=num-(numeroanalisar[1]-numerosdesoluciones[1]);
				}else 
					num=num+(numeroanalisar[1]-numerosdesoluciones[1]);
				
				}
		return num;
	}
	private static int[][] clonar(int[][] estado) {
		int[][] clon=new int[estado.length][estado.length];
		for(int i=0;i<estado.length;i++){
			for(int j=0;j<estado.length;j++){
			clon[i][j]=estado[i][j];
			}
		}
		return clon;
	}
	public static void imprimirestado(int[][] estado){
		for(int i=0;i<estado.length;i++){
			for(int j=0;j<estado.length;j++){
				System.out.print("["+estado[i][j]+"]");
			}
			System.out.println("");
		}
	}

}
