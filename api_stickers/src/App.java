import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        // executar conexão HTTP e buscar os top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // vamos extrair só os dados que realmente interessam (título, poster, classificação)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.printf("\u001b[37;1m \u001b[44;1mTítulo do Filme:\u001b[m"+" "+filme.get("title")+"\n");
            System.out.printf("\u001b[37;1m \u001b[46;1mPoster:\u001b[m"+" "+filme.get("image")+"\n");
            double rating = Double.parseDouble(filme.get("imDbRating"));
            System.out.printf("\u001b[37;1m \u001b[42;1mEstrelas:\u001b[m");
            for(int i = 0; i < rating; ++i){
                System.out.printf(" \u2B50");
            }
            System.out.println("\n");
        }

    }
}
