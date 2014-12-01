public class Complex {
    public static void main(String[] args) {
        Complex c1 = new Complex(6, 3);
        Complex c2 = new Complex(10, 8);
        
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c1 + c2: " + (c1.add(c2)));
        System.out.println("c1 - c2: " + (c1.subtract(c2)));
        System.out.println("c1 * c2: " + (c1.multiply(c2)));
        System.out.println("c1 / c2: " + (c1.dividedBy(c2)));
    }
    
    double _a, _b;
    public Complex() { }
    public Complex(double a, double b) {
        _a = a;
        _b = b;
    }
    
    public double getReal() { return _a; }
    public double getImaginary() { return _b; }
    
    public void setReal(double a) { _a = a; }
    public void setImaginary(double b) { _b = b; }
    
    public Complex conjugate() {
        return new Complex(this.getReal(), -this.getImaginary());
    }
    
    public Complex add(double a, double b) { return this.add(new Complex(a, b)); }
    public Complex add(Complex op) {
        return new Complex(this.getReal() + op.getReal(), this.getImaginary() + op.getImaginary());
    }
    
    public Complex subtract(double a, double b) { return this.subtract(new Complex(a, b)); }
    public Complex subtract(Complex op) {
        return new Complex(this.getReal() - op.getReal(), this.getImaginary() - op.getImaginary());
    }
    
    public Complex multiply(double a, double b) { return this.multiply(new Complex(a, b)); }
    public Complex multiply(Complex op) {
        double a = this.getReal(); double b = this.getImaginary();
        double c = op.getReal(); double d = op.getImaginary();
        return new Complex(a * c - b * d, a * d + b * c);
    }
    
    public Complex dividedBy(double a, double b) { return this.dividedBy(new Complex(a, b)); }
    public Complex dividedBy(Complex op) {
        double a = this.getReal(); double b = this.getImaginary();
        double c = op.getReal(); double d = op.getImaginary();
        return new Complex((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d)); //Optimization should be done by compiler.
    }
    
    public Complex square() {
        return this.multiply(this);
    }
    
    public String toString() {
        // You forgot to set a precision for this toString function, didn't you?
        if (this.getImaginary() == 0.0) return String.format("%.1f", this.getReal());
        if (this.getImaginary() > 0) return String.format("%.1f+%.1fi", this.getReal(), this.getImaginary());
        if (this.getImaginary() < 0) return String.format("%.1f%.1fi", this.getReal(), this.getImaginary());
        return ""; //Should never go to this line.
    }
}