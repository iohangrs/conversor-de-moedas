package conversor.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientMoeda {
    public static double obterTaxa(String apiKey, String moedaBase, String moedaAlvo) throws Exception {
        String endpoint = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, moedaBase);
        URL url = new URL(endpoint);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;
        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }
        leitor.close();

        Gson gson = new Gson();
        RespostaAPI respostaAPI = gson.fromJson(resposta.toString(), RespostaAPI.class);

        if (!respostaAPI.conversion_rates.containsKey(moedaAlvo)) {
            throw new Exception("Moeda de destino não encontrada.");
        }

        return respostaAPI.conversion_rates.get(moedaAlvo);
    }
}