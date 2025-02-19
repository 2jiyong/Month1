public class Register {
    private int value;
    private boolean initialized = false;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        initialized = true;
        this.value = value;
    }

    public boolean isInitialized(){
        return initialized;
    }
}
