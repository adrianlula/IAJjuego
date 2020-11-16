package cuadrooo;

import java.util.ArrayList;
import java.util.Arrays;

public class cuadro {

	public static void main(String[] args) {
		int[][] inicial={{4,1,3},{2,0,6},{7,5,8}};
        int[][] solucion={{1,2,3},{4,5,6},{7,8,0}};
       nodo inicio=new nodo(inicial);
      nodo sol= buscarsolucion(inicio,solucion);
      while(sol.padre!=null){
      	  imprimirestado(sol.getestado());
    	  System.out.println("::::::::::::");
    	  sol=sol.padre;
      }
      imprimirestado(inicio.getestado());
	}
	
	
	public static nodo  buscarsolucion(nodo inicio,int[][] solucion){
		ArrayList<nodo> expandidos=new ArrayList<nodo>();
		expandidos.add(inicio);
		int movimientos=0;
		ArrayList<nodo> visitados=new ArrayList<nodo>();
		
		while(expandidos.size()!=0){
			nodo revisar=expandidos.remove(0);
			imprimirestado(revisar.getestado());
			int [] pocicioncero=ubicarnumero(revisar.getestado(),0);
			System.out.println(pocicioncero[0]+","+pocicioncero[1]);
			System.out.println("movimientos realizados: "+(movimientos++));
			if(Arrays.deepEquals(revisar.getestado(),solucion)){
				System.out.println("resuelto encontrado");
				return revisar;
			}
			ArrayList<nodo> hijos=new ArrayList<nodo>();
			visitados.add(revisar);
			
			if(pocicioncero[0]!=0){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int arriba=hijo.getestado()[pocicioncero[0]-1][pocicioncero[1]];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=arriba;
				hijo.getestado()[pocicioncero[0]-1][pocicioncero[1]]=0;
				if(!estaenvisitados(visitados,hijo))
				expandidos.add(hijo);
				hijos.add(hijo);
			}
			if(pocicioncero[0]!=2){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int abajo=hijo.getestado()[pocicioncero[0]+1][pocicioncero[1]];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=abajo;
				hijo.getestado()[pocicioncero[0]+1][pocicioncero[1]]=0;
				if(!estaenvisitados(visitados,hijo))
				expandidos.add(hijo);
				hijos.add(hijo);
				
			}
			
			if(pocicioncero[1]!=0){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int izquierda=hijo.getestado()[pocicioncero[0]][pocicioncero[1]-1];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=izquierda;
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]-1]=0;
				if(!estaenvisitados(visitados,hijo))
				expandidos.add(hijo);
				hijos.add(hijo);
			}
			if(pocicioncero[1]!=2){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int derecha=hijo.getestado()[pocicioncero[0]][pocicioncero[1]+1];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=derecha;
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]+1]=0;
				if(!estaenvisitados(visitados,hijo))
				expandidos.add(hijo);
				hijos.add(hijo);
			}
			
			revisar.sethijos(hijos);
			
		}
		return null;
	}
	
	private static boolean estaenvisitados(ArrayList<nodo> visitados, nodo hijo) {
		for(nodo v:visitados){
			if(Arrays.deepEquals(v.getestado(), hijo.getestado())){
				return true;
			}
		}
		return false;
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


	private static int[] ubicarnumero(int[][] estado,int n) {
	int[] posicion=new int[2];
		for(int i=0;i<estado.length;i++){
			for(int j=0;j<estado.length;j++){
				if(estado[i][j]==0){
					posicion[0]=i;
					posicion[1]=j;
					}
			}
		}
		System.out.println("unicasion");
		return posicion;
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
