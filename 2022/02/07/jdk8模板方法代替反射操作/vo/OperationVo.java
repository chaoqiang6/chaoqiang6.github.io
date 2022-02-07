package lamdademo.vo;

public class OperationVo {

    private boolean passable;

    private boolean rejectable;

    public void reset(){
        passable = false;
        rejectable = false;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isRejectable() {
        return rejectable;
    }

    public void setRejectable(boolean rejectable) {
        this.rejectable = rejectable;
    }

    @Override
    public String toString() {
        return "OperationVo{" +
                "passable=" + passable +
                ", rejectable=" + rejectable +
                '}';
    }
}
