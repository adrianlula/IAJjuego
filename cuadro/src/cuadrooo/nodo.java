package cuadrooo;
import java.util.ArrayList;;
public class nodo {
int estado[][];
ArrayList<nodo> hijos=new ArrayList<nodo>() ;
nodo padre;
public nodo(int[][] estado){
	this.estado=estado;
	hijos=null;
	padre=null;
}
public int [][]getestado(){
	return estado;
}
public void setestado(int[][] estado){
	this.estado=estado;
}
public ArrayList<nodo> gethijos(){
	return hijos;
}
public void sethijos(ArrayList<nodo> hijos){
	this.hijos=hijos;
	if(hijos!=null){
		for(nodo h:hijos){
			h.padre=this;
		}
	}
}
public nodo getpadre(){
	return padre;
}
public void setpadre(nodo padre){
	this.padre=padre;
}

}
