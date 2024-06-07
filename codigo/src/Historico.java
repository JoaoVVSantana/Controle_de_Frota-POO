package src;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Historico {
    public Map<Integer, List<Rota>> historicoDeRotas;
    public static final Map<Integer,String> meses;


    static{
        meses = new HashMap<>();
        meses.put(1,"Janeiro");
        meses.put(2,"Fevereiro");
        meses.put(3,"Março");   
        meses.put(4,"Abril");
        meses.put(5,"Maio");
        meses.put(6,"Junho");
        meses.put(7,"Julho");
        meses.put(8,"Agosto");
        meses.put(9,"Setembro");
        meses.put(10,"Outubro");
        meses.put(11,"Novembro");
        meses.put(12,"Dezembro");
    }

    public Historico(){
        historicoDeRotas = new HashMap<>();
    }

    public void addHistorico(int mes, Rota rota){
        List<Rota> lista = historicoDeRotas.getOrDefault(mes, new LinkedList<>());
        lista.add(rota);
        historicoDeRotas.put(mes,lista);
    }

    public String exibirHistorico(){
        StringBuilder resultado = new StringBuilder();

        historicoDeRotas.forEach((mes, rotas) -> {
            resultado.append("Histórico para mês ").append(meses.get(mes)).append(":\n");

            
            for (Rota rota : rotas) {
                resultado.append(String.format("   Distância: %.2f, Data: %s\n", rota.getQuilometragem(), rota.getData().dataFormatada()));
            }

            resultado.append("\n");
        });

        return resultado.toString();

    }


}
