public class MyPair<X, Y> {
    private X x;
    private Y y;

    public MyPair() {
        this.x = null;
        this.y = null;
    }

    public void setX(X newX) {
        this.x = newX;
    }

    public void setY(Y newY) {
        this.y = newY;
    }

    public X getX(){
        return this.x;
    }

    public Y getY(){
        return this.y;
    }
}
