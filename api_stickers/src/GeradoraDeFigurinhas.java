import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        // leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("images/image_batman.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@.jpg").openStream();
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memória com transparência e tamanho novo

        int largura = imageOriginal.getWidth();
        int altura = imageOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        // configurar a fonte


        Font fonte = new Font(Font.SERIF, Font.BOLD, 90);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        
        graphics.drawString("TOPZERA", 500, novaAltura - 100);

        // escrever a nova imagem em um arquivo

        try {
            ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
        } catch (Exception e) {
                String path = "saida/figurinha.png";
                Files.createDirectories(Paths.get(path));
        }
        

    }

    /*public static void main(String[] args) throws Exception {
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        geradora.cria();
    }*/
    
}
