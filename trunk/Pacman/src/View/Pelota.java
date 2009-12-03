package View;



import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class Pelota implements ObjetoVivo, Posicionable {

	public Pelota(){
		this.sentidoX = 1;
		this.sentidoY = 1;
		this.x = 20;
		this.y = 20;
	}
	
	public Pelota(int xInicial, int yInicial){
		if(xInicial < 0){
			this.sentidoX = -1;
			this.x = Math.abs(xInicial);
		}
		if(yInicial < 0){
			this.sentidoY = -1;
			this.y = Math.abs(yInicial);
		}
		this.sentidoX = 1;
		this.sentidoY = 1;
		this.x = xInicial;
		this.y = yInicial;
	}
	
	/* (non-Javadoc)
	 * @see ar.uba.fi.algo3.ejemplos.pelotas.modelo.Obstaculo#getX()
	 */
	public int getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see ar.uba.fi.algo3.ejemplos.pelotas.modelo.Obstaculo#getY()
	 */
	public int getY() {
		return y;
	}

	public void vivir() {
		x += this.sentidoX;
		x += this.sentidoX;
		y +=this.sentidoY;
		if(x >= this.mesa.getAncho() || x < 0){
			this.sentidoX = -1 * this.sentidoX;
		}
		if(y >= this.mesa.getAlto() || y < 21){
			this.sentidoY = -1 * this.sentidoY;
		}
		
		/*Iterator<Obstaculo> iteradorDeObstaculos = mesa.iterator();
		while(iteradorDeObstaculos.hasNext()){
			Obstaculo otroObstaculo = iteradorDeObstaculos.next();
			if(!this.equals(otroObstaculo)){
				if(distancia(otroObstaculo)<5){
					if( distancia (this.getX(),otroObstaculo.getX()) < 2)
						this.sentidoX = -1 * this.sentidoX;
					
					if( distancia (this.getY(),otroObstaculo.getY()) < 2){
						this.sentidoY = -1 * this.sentidoY;
					}
				}
			}
		}*/
	}

	/*private double distancia(Obstaculo obstaculo){
		double x = this.getX() - obstaculo.getX();
		double y = this.getY() - obstaculo.getY();
		return Math.sqrt( (x*x) + (y*y));
	}*/


	
	private int x;
	private int y;
	private Mesa mesa;
	private int sentidoX;
	private int sentidoY;
	
	
	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	

}
