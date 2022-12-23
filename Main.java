
import java.util.Random;
import java.util.Scanner;// библ для ввода с клавиатуры
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Main {


    static double f(double x)
    {
        double y = 3 * Math.exp(x * x * x) * Math.cos(x);
       // double y =  Math.exp(x /2) +((Math.exp((x+1)/2))+ (Math.Exp(-(x+1)/2)))/2;


        return y;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    } // функция для округления


    static Polinom Lagrang(double[] x)
    {



        int n = x.length;
        double[] zero = new double[n];
        for (int i = 0; i < n; i++)
        {
            zero[i] = 0;
        }
        Polinom P = new Polinom(zero);// для инициализации нулевой масииво


        double []d = new double[n];// не обязательно
        Polinom[] l = new Polinom[n];

        for (int i = 0; i < n; i++)
        {
            d[i] = 1;
            for (int k = 0; k < n; k++)
            {
                if (i == k) { continue; }


                d[i] *= (x[i] - x[k]);
            }
        } // не обязательно


        Polinom lx = new Polinom(1,0);

        {
            for (int i = 0; i < n; i++) {
                l[i] = new Polinom(1);

                for (int k = 0; k < n; k++) {
                    if (i == k) {
                        continue;
                    }

                    // l[i] *= (lx - x[k])*(1/(x[i] - x[k]));

                    l[i] = Polinom.Prod(l[i], Polinom.Prod(Polinom.Sub(lx, x[k]), 1 / (x[i] - x[k])));


                }
            } // расчет эл малое


            for (int i = 0; i < n; i++)
            {

               // l[i].Print();
               //P += l[i] * f(x[i]);
                P=Polinom.Sum(P,Polinom.Prod(l[i],f(x[i])));
            }

            System.out.println("////////////////////////////////////////// проверка начало");
            for (int i = 0; i < n; i++) {
                System.out.print("значение функциив узле(" + round(f(x[i]), 6) + " )");
                System.out.print(" значение полинома в узлов точке( " + round(P.value(P, x[i]), 6) + ")");
                System.out.print("разность( " + round(P.value(P, x[i]) - f(x[i]), 4) + ")");
                System.out.println();

            }
            System.out.println("////////////////////////////////////////// проверка конец");
         /*   double[] z = {-0.89, -0.42, -0.26};
            for (int i = 0; i < 3; i++) {
                System.out.println("значение функциив точке( " + round(f(z[i]), 6) + ")");
                System.out.println(" значение полинома в узлов точке( " + round(P.value(P, z[i]), 6) + ")");
                System.out.println("разность( " + round(P.value(P, z[i]) - f(z[i]), 4) + ")");
                System.out.println();
            }*/ // для оценки разности значений полинома и функции в произвольной точке

        }




        return P;
    }

    static   void Test(Polinom P,Polinom Q)
    {
        Polinom Res =new Polinom();
        double[]A={0,1,2,1};
        double[]B={0,1,1};
        System.out.println("P :");
        P.Print6();
        System.out.println("P :");
        Q.Print6();
        {
            System.out.println("Cумма ");
            Res =Polinom.Sum(P,Q);
            Res.Print6();
        }
        {
            System.out.println("Разность ");
            Res =Polinom.Sub(P,Q);
            Res.Print6();
        }
        {
            System.out.println("Произведение ");
            Res =Polinom.Sub(P,Q);
            Res.Print6();
        }
        {
            System.out.println("P^3");

            Res=Polinom.pow(P,3);
            Res.Print6();
            System.out.println("P^5");
            Res =P.pow(Q,5);
            Res.Print6();

        }
        {
            System.out.println("деление с остатком полинома Р на Q");
            Polinom[]Result =new  Polinom[2];
            Result =P.DelOst(P,Q);
            System.out.println("частное:");
            Result[0].Print6();
            System.out.println("остаток:");
            Result[1].Print6();

        }
        {
            System.out.println("Случайный полином третьей степени");
            Res =Polinom.Random(3);
            Res.Print6();

        }
        {// тест полинома лагранжа

            System.out.println();
            System.out.println("Полином Лагранжа по узловым точкам для функции f(x) = 3 * exp(x^3) * cos(x);");
            double[] x = new double[5];

            System.out.println("Узловые точки: ");
            for (int i = 0; i < 5; i++)
            {
                x[i] = -1+0.5*i;//

                System.out.print(x[i]);
            }
            System.out.println();


           Res= Lagrang(x);
            System.out.println("Найденный полином Лагранжа для функции f(x): ");
            Res.Print5();
        }

    }

    private static Scanner inn = new Scanner(System.in);// метод для ввода с клавиатуры

    public static <string> void main(String[] args) {


        double[]A={1,2,1};
        double[]B={1,1};

        Polinom P=new Polinom(A);
        Polinom Q=new Polinom(B);


        Test(P,Q);






















    }
}

