public class Equation {
    private float a;
    private  float b;

    public Equation(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float calculate(Operation operation){
        switch (operation){
            case ADDITION -> {
                return a+b;
            }
            case SUBTRACT -> {
                return a-b;
            }
            case MULTIPLICATION -> {
                return a*b;
            }
            case DIVISION -> {
                if(b == 0)
                    throw new ArithmeticException("Błąd - dzielenie przez zero");
                else
                    return a/b;
            }
            default -> throw new IllegalArgumentException("Błąd - zły operator");
        }
    }
}
