package recursao;

public class MetodosRecursivos {

   public long calcularFatorial(int n) {
      long result = 1;
      if (n == 0) {

      } else {
         result = n * calcularFatorial(n - 1);
      }
      System.out.println(n + "! = " + result);
      return result;
   }

   public int calcularFibonacci(int n) {
      int result = 1;
      if (n == 0) {
         return 0;
      } else if (n == 1 || n == 2) {
         return 1;
      } else {
         result = calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
      }
      return result;
   }

   public int countNotNull(Object[] array) {
      int result = 0;
      //Para fazer
      return result;
   }

   public long potenciaDe2(int expoente) {
      long result = 1;
      if (expoente == 1) {
         return 2;
      } else {
         result = 2 * potenciaDe2(expoente - 1);
      }
      return result;
   }

   public double progressaoAritmetica(double termoInicial, double razao, int n) {
      double result = termoInicial;
      if (n == 1) {
    	  
      } else {
    	  result = razao + progressaoAritmetica(termoInicial, razao, n - 1);
      }
      return result;
   }

   public double progressaoGeometrica(double termoInicial, double razao, int n) {
	   double result = termoInicial;
	   if (n == 1) {
		   
	   } else {
		   result = razao * progressaoGeometrica(termoInicial, razao, n -1);
	   }
	   return result;
   }
}
