
package grafo;

import java.util.Scanner;

public class EntradaUsuario {

    public static double getNumero(String descricao, boolean vazio){
        Scanner teclado = new Scanner(System.in);
        double resultado = -1;
        boolean flag = false;
        while(!flag){
            try{
                System.out.print(descricao);
                String resultado_aux = teclado.nextLine();
                resultado = 0;
                if(!resultado_aux.equals(""))
                    resultado = Double.parseDouble(resultado_aux);
                if(vazio || resultado >= 0)
                    flag = true;
            }catch(Exception e){}
            if(!flag)
                System.out.println("Número inválido");
        }
        return resultado;
    }

    public static String getString(String descricao, boolean vazio){
        Scanner teclado = new Scanner(System.in);
        String resultado = "";
        boolean flag = false;
        while(!flag){
            try{
                System.out.print(descricao);
                resultado = teclado.nextLine();
                if(vazio || !resultado.equals(""))
                    flag = true;
            }catch(Exception e){}
            if(!flag)
                System.out.println("String inválido");
        }
        return resultado;
    }
}
