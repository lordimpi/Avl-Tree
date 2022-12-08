/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public abstract class Arbol {
    protected Nodo raiz;	
	public Arbol(){
		raiz=null;
	}	
	public Nodo getRaiz() {
		return raiz;
	}
	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}
	public static int factorEquilibrio(Nodo arbol){
		if (arbol==null)	
			return 0;
		else
			return (arbol.getFactorEquilibrio()+1);
	}	
	public String preOrden(Nodo arbol){
		if(arbol!=null){
			return " "+arbol.getElemento()+preOrden(arbol.getIzq())+preOrden(arbol.getDer());
		}
		return "";
	}
	public String inOrden(Nodo arbol){
		if(arbol!=null){
			return inOrden(arbol.getIzq())+" "+arbol.getElemento()+inOrden(arbol.getDer());
		}
		return "";
	}
	public String postOrden(Nodo arbol){
		if(arbol!=null)	{
			return postOrden(arbol.getIzq())+postOrden(arbol.getDer())+" "+arbol.getElemento();
		}
		return "";
	}		
	public String porNiveles()
	{	
		if(raiz!=null){
			asignarNiveles(raiz,0);
			return porNiveles(raiz,0,raiz.getFactorEquilibrio());	
		}
		return "";		
	}
	public String porNiveles(Nodo arbol,int nivelActual,int nivelMaximo){
		if(arbol!=null){
			if(nivelActual<=nivelMaximo){				
				return imprimirNivelSolo(arbol,nivelActual)+porNiveles(arbol,nivelActual+1, nivelMaximo);
			}
		}
		return "";
	}
	private String imprimirNivelSolo(Nodo arbol, int nivel){
		if(arbol!=null){
			if(arbol.getNivel()==nivel){
				return " "+arbol.getElemento();		
			}
			else{
				return imprimirNivelSolo(arbol.getIzq(), nivel)+imprimirNivelSolo(arbol.getDer(), nivel);	
			}
		}		
		return "";
	}
	public int cantidadNodos(Nodo nodo){
		if(nodo!=null){
			return 1+cantidadNodos(nodo.getIzq())+cantidadNodos(nodo.getDer());
		}
		return 0;
	}
	public void asignarNiveles(Nodo arbol,int nivel)
	{	
		if(arbol!=null){	
			asignarNiveles(arbol.getIzq(),nivel+1);	
			arbol.setNivel(nivel);	
			asignarNiveles(arbol.getDer(),nivel+1);
		}
	}
	public String Hojas(Nodo arbol){
		if(arbol!=null){
			if(arbol.esHoja()){
				return " "+arbol.getElemento();
			}
			return Hojas(arbol.getIzq())+Hojas(arbol.getDer());
		}
		return "";		
	}
	public String interior(Nodo arbol){
		return interiores(arbol.getIzq())+interiores(arbol.getDer());
	}
	public String interiores(Nodo arbol){
		if(arbol!=null){
			if(!arbol.esHoja()){
				return " "+arbol.getElemento();
			}
			return interiores(arbol.getIzq())+interiores(arbol.getDer());
		}
		return "";
	}
	public void exterior(Nodo arbol){
		if(arbol.getIzq()!=null){
			todosIzq(arbol.getIzq());	
		}
		System.out.print(" "+arbol.getElemento());
		if(arbol.getDer()!=null){
			todosDer(arbol.getDer());
		}
	}
	public void todosIzq(Nodo arbol){
		if(arbol!=null){
			todosIzq(arbol.getIzq());
			System.out.print(" "+arbol.getElemento());
		}		
	}
	public void todosDer(Nodo arbol){
		if(arbol!=null){
			System.out.print(" "+arbol.getElemento());
			todosDer(arbol.getDer());
		}		
	}
	public void infoArbol(){
		if(raiz!=null){
			asignarNiveles(raiz, 0);
			asignarPadre(raiz);
			System.out.println("--------------------------RECORRIDOS--------------------------");
			System.out.println("PreOrden:"+preOrden(raiz));
			System.out.println("InOrden:"+inOrden(raiz));
			System.out.println("PostOrden:"+postOrden(raiz));
			System.out.println("Niveles:"+porNiveles());
			System.out.print("Camino euclidiano:");
			caminoEuclidiano(raiz);
			System.out.println();
			System.out.println("--------------------------INFO ARBOL--------------------------");
			System.out.println("Raiz: "+raiz.getElemento());
			System.out.println("Cantidad Nodos: "+cantidadNodos(raiz));
			System.out.println("SubArbol izquierdo(Preorden):"+preOrden(raiz.getIzq()));
			System.out.println("SubArbol derecho(Preorden):"+preOrden(raiz.getDer()));
			System.out.println("Nodos hoja:"+Hojas(raiz));
			System.out.println("Nodos interiores:"+interior(raiz));
			System.out.print("Nodos Exteriores");
			exterior(raiz);
			System.out.println();
			System.out.println("\nNodos primos:");
			primos(raiz);
			System.out.println("--------------------------------------------------------------");	
			System.out.println("-------------------------INFO NIVELES-------------------------");
			elementos(raiz, 0, raiz.getFactorEquilibrio());
			System.out.println("--------------------------------------------------------------");
		}else{
			System.out.println("No existe arbol para mostrar");
		}
	
	}
	public void elementos(Nodo arbol,int nivelActual,int nivelMaximo){
		if(arbol!=null){
			if(nivelActual<=nivelMaximo){
				elementosNivel(nivelActual);
				elementos(arbol, (nivelActual+1), nivelMaximo);
			}
		}
	}
	public void elementosNivel(int nivel){
		int cantidad=cantidad(nivel, raiz);
		System.out.println("Nivel: "+nivel);
		if(raiz.getElemento() instanceof Integer){
			int suma=suma(nivel, raiz);
			System.out.println("   Suma: "+suma);	
		}
		System.out.println("   Cantidad: "+cantidad);
		System.out.println("   Nodos del nivel:"+imprimirNivelSolo(raiz, nivel));		
	}
	public int cantidad(int nivel,Nodo nodo){
		int cantidad=0;
		if(nodo!=null){
			if(nodo.getNivel()==nivel){
				cantidad=cantidad+1;
			}
			return cantidad+cantidad(nivel, nodo.getIzq())+cantidad(nivel, nodo.getDer());
		}
		return 0;
	}
	public int suma(int nivel,Nodo nodo){
		int suma=0;
		if(nodo!=null){
			if(nodo.getNivel()==nivel){
				suma=suma+(int)nodo.getElemento();
			}
			return suma+suma(nivel, nodo.getIzq())+suma(nivel, nodo.getDer());
		}
		return 0;
	}
	public void asignarPadre(Nodo nodo){
		if(nodo!=null){
			if(nodo.getIzq()!=null){
				nodo.getIzq().setPadre(nodo);
			}
			if(nodo.getDer()!=null){
				nodo.getDer().setPadre(nodo);
			}
			asignarPadre(nodo.getIzq());
			asignarPadre(nodo.getDer());
		}
	} 
	public void primos(Nodo nodo){
		if(nodo!=null){
			primos(nodo.getIzq());
			if(nodo.getNivel()>=2){
				if(nodo.getTio()!=null){
					System.out.print("   Nodo "+nodo.getElemento()+":");
					if(nodo.getTio().getIzq()!=null){
						System.out.print(" "+nodo.getTio().getIzq().getElemento());
					}
					if(nodo.getTio().getDer()!=null){
						System.out.print(","+nodo.getTio().getDer().getElemento());
					}
					if(nodo.getTio().getIzq()==null && nodo.getTio().getDer()==null){
						System.out.print(" No tiene primos");
					}
					System.out.println();
				}else{
					System.out.println("   Nodo "+nodo.getElemento()+": No tiene primos");
				}				
			}
			else{
				System.out.println("   Nodo "+nodo.getElemento()+": No tiene primos");
			}
			primos(nodo.getDer());
		}
	}
	public Nodo buscar(Comparable elemento){
		Nodo nodo=buscar(elemento,raiz);
		return nodo;
	}
	public Nodo buscar(Comparable elemento,Nodo nodo){
		if(raiz==null){
			return null;
		}else if(elemento.compareTo(nodo.getElemento())==0){
			return nodo;
		}else if(elemento.compareTo(nodo.getElemento())<0 && nodo.getIzq()!=null){
			return buscar(elemento,nodo.getIzq());
		}else if(elemento.compareTo(nodo.getElemento())>0 && nodo.getDer()!=null){
			return buscar(elemento,nodo.getDer());
		}	
		return null;
	}
	public String buscarNodo(Comparable elemento){
		asignarNiveles(raiz, 0);
		String busqueda="";
		Nodo nodo=buscar(elemento,raiz);
		if(nodo!=null){
			busqueda = "El nodo existe \n Nivel: "+nodo.getNivel();
			return busqueda;
		}
		JOptionPane.showMessageDialog(null,"El nodo no se encuentra en el arbol","Error",0);
		return busqueda;
	}
	public boolean remover(Comparable elemento){
		Nodo nodo=buscar(elemento,raiz);
		if(nodo!=null){
			if(nodo==raiz && raiz.esHoja()){
				JOptionPane.showMessageDialog(null,"El valor fue eliminado correctamente","Notificacion",1);
				raiz=null;
				return true;
			}
			boolean r=remover(nodo);
			asignarNiveles(raiz, 0);
			if(r==true){
				JOptionPane.showMessageDialog(null,"El valor fue eliminado correctamente","Notificacion",1);
				asignarPadre(raiz);
				return r;
			}else{
				JOptionPane.showMessageDialog(null,"El valor a eliminar en el arbol no existe","Error",0);
				asignarPadre(raiz);
				return r;
			}

		}
		JOptionPane.showMessageDialog(null,"El valor a eliminar en el arbol no existe","Error",0);
		return false;
	}
	public boolean remover(Nodo nodo){
		asignarPadre(raiz);
		boolean derE=nodo.getDer()!=null?true:false;
		boolean izqE=nodo.getIzq()!=null?true:false;
		
		if(!derE && !izqE){
			return remover1(nodo);
		}
		if((!derE && izqE)||(derE && !izqE)){
			return remover2(nodo);
		}
		if(derE && izqE){
			return remover3(nodo);
		}
		return false;
	}
	public void insertar(Comparable elemento ) {
		if(buscar(elemento)==null){
			raiz = insertar(raiz,elemento);
			JOptionPane.showMessageDialog(null,"Se ingreso el valor exitosamente","Notificacion",1);
		}else{
			JOptionPane.showMessageDialog(null,"El valor ingresado ("+elemento+") ya existe en el arbol","Error",0);
		}
	}
	public static int mFE( int alturaIzquierdo, int alturaDerecho ) {
		if (alturaIzquierdo > alturaDerecho)	
			return alturaIzquierdo;
		else
			return alturaDerecho;
	}
	public Nodo masDer(Nodo nodo){
		if(nodo.getDer()!=null){
			return masDer(nodo.getDer());
		}
		return nodo;
	}
	public Nodo masIzq(Nodo nodo){
		if(nodo.getIzq()!=null){
			return masDer(nodo.getIzq());
		}
		return nodo;
	}
	public void caminoEuclidiano(Nodo nodo){
		if(nodo!=null){
			if(nodo.esHoja()){
				System.out.print(" "+nodo.getElemento());	
			}else{
				System.out.print(" "+nodo.getElemento());
				if(nodo.getIzq()!=null){
					caminoEuclidiano(nodo.getIzq());
					System.out.print(" "+nodo.getElemento());
				}
				if(nodo.getDer()!=null){
					caminoEuclidiano(nodo.getDer());
					System.out.print(" "+nodo.getElemento());		
				}
			}
		}
	}	
	public abstract Nodo insertar(Nodo nodo,Comparable elemento);
	public abstract boolean remover1(Nodo nodo);
	public abstract boolean remover2(Nodo nodo);
	public abstract boolean remover3(Nodo nodo);
}
