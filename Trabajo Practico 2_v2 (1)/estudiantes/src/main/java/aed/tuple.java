package aed;

public class tuple<t1,t2> {
    private t1 _primero;
    private t2 _segundo;


    public tuple(t1 primero,t2 segundo){
        _primero = primero;
        _segundo = segundo;

    }





    public t1 primero(){
        return _primero;
    }

    public t2 segundo(){
        return _segundo;
    }


    @Override
    public String toString(){
        StringBuffer sbuffer = new StringBuffer();

        sbuffer.append("<");
        sbuffer.append(this.primero().toString());
        sbuffer.append(",");
        sbuffer.append(this.segundo().toString());

        return sbuffer.toString();
    }

    
}
