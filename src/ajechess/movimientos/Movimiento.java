package ajechess.movimientos;

import ajechess.posicion.Posicion;

public class Movimiento {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public Movimiento(int x,int y){
        setX(x);
        setY(y);
    }

    @Override
    public boolean equals(Object o){
        Movimiento m=(Movimiento)o;
        return ((getX()==m.getX())&&(getY()==m.getY()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.y;
        return hash;
    }

    public static Movimiento getMovimiento(Posicion posicionInicial,Posicion posicionFinal){
        return new Movimiento(posicionFinal.getX()-posicionInicial.getX(),posicionFinal.getY()-posicionInicial.getY());
    }

    public Movimiento getPaso(){
        int tempX;
        int tempY;
        if(getX()>0){
            tempX=1;
        }else if(getX()<0){
            tempX=-1;
        }else{
            tempX=0;
        }
        if(getY()>0){
            tempY=1;
        }else if(getY()<0){
            tempY=-1;
        }else{
            tempY=0;
        }
        return new Movimiento(tempX,tempY);
    }

    public Movimiento movimientoInverso(){
        return new Movimiento(-getX(),-getY());
    }
}
