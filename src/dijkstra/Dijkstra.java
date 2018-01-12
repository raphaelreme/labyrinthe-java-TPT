package dijkstra;
import interfaces.*;


public final class Dijkstra {
	
	private static PreviousInterface dijkstra(GraphInterface g,
			VertexInterface r, ASetInterface a,
			PiInterface pi, PreviousInterface previous){
		
		//Init
		a.add(r);
		VertexInterface pivot = r;
		
		for (VertexInterface v : g.getVertices()){
			pi.setPi(v, -1);
		}
		pi.setPi(pivot, 0);
		
		//Boucle principale
		for (int i = 0; i<g.getSize(); i++){
			//Maj autour du pivot
			for (VertexInterface v : g.getNext(pivot)){
				int p = pi.getPi(pivot) + g.getWeight(pivot, v);
				int piv = pi.getPi(v);
				if (!a.isIn(v) && (piv == -1 || p < piv)) {
					pi.setPi(v, p);
					previous.setPrev(v, pivot);
				}
			}
			
			//Recherche du prochain pivot
			int min = -1;
			for (VertexInterface v : g.getVertices()){
				int piv = pi.getPi(v);
				if (!a.isIn(v) && piv != -1 && (min == -1 || piv < min)){
					min = piv;
					pivot = v;
				}
			}
			
			if (min == -1) {
				break;
			}
			
			a.add(pivot);
			
		}
				
		return previous;
		
		
	}
	
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r, ASetInterface a){
		return dijkstra(g,r,a,new Pi(),new Previous());
	}
}
