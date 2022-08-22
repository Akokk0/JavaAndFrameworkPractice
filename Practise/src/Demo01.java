public class Demo01 {
    public static void main(String[] args) {
        invokecalc(10, 20,(int a, int b) -> {
            return a + b;
        });
    }

    public static void invokecalc(int a, int b, Calculator c) {
        int sum = c.calc(a, b);
        System.out.println(sum);
    }
}
