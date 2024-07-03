package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import model.MovimentacaoFinanceira;
import model.MovimentacaoFinanceiraDAO;
import model.TreinoAplicacaoDAO;

public class RelatorioDAO {

    private static long serial_num_relatorio = 0;
    public long getNumRelatorio()
    {
        serial_num_relatorio = ++RelatorioDAO.serial_num_relatorio;
        return RelatorioDAO.serial_num_relatorio;
    }

    MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO = new MovimentacaoFinanceiraDAO();
    TreinoAplicacaoDAO treinoAplicacaoDAO = new TreinoAplicacaoDAO();

    public static final String caminhoArquivo = "relatorios/";

    public void createRelatorioFinanceiro()
    {
        List<MovimentacaoFinanceira> movimentacoesFinanceiras = movimentacaoFinanceiraDAO.showMovimentacoesFinanceiras(null);

        BigDecimal total_entrada = BigDecimal.ZERO;
        BigDecimal total_saida = BigDecimal.ZERO;
        BigDecimal saldo = BigDecimal.ZERO;

        StringBuilder transacoes = new StringBuilder("Transações");

        for(MovimentacaoFinanceira mf : movimentacoesFinanceiras)
        {
            if(mf.getTipoMovimentacaoFinanceira().equals("Entrada"))
            {
                total_entrada = total_entrada.add(mf.getValorMovimentacaoFinanceiroBigD());
            }
            else if(mf.getTipoMovimentacaoFinanceira().equals("Saída"))
            {
                total_saida = total_saida.add(mf.getValorMovimentacaoFinanceiroBigD());
            }
            
            transacoes.append("\nValor: " + mf.getValorMovimentacaoFinanceiro());
            transacoes.append("\nTipo: " + mf.getTipoMovimentacaoFinanceira());
            transacoes.append("\nDescrição: " + mf.getDescricaoMovimentacaoFinanceira());
            transacoes.append("\n");
        }

        saldo = saldo.add(total_entrada);
        saldo = saldo.subtract(total_saida);

        // System.out.println("\n" + total_entrada);
        // System.out.println("\n" + total_saida);
        // System.out.println("\n" + saldo);
        // System.out.println("\n" + transacoes);

        String nome_arq = "relatorio" + getNumRelatorio();
        try (FileOutputStream file = new FileOutputStream(new File(caminhoArquivo + nome_arq + ".pdf"))) {

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(350);
            table.setLockedWidth(true);
            table.setWidths(new float[]{2, 1});
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Relatório Financeiro"));
            cell.setColspan(2);
            table.addCell(cell);
            table.addCell("Entrada");
            table.addCell("R$ " + total_entrada.toString());

            table.addCell("Saída");
            table.addCell("R$ " + total_saida.toString());

            table.addCell("Saldo");
            table.addCell("R$ " + saldo.toString());
            document.add(table);

            document.add(new Paragraph(transacoes.toString(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            
            document.close();

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /* 
    public void createFichaTreino()
    {
        List<TreinoAplicacao> treinoAplicacoes = movimentacaoFinanceiraDAO.showMovimentacoesFinanceiras(null);

        BigDecimal total_entrada = BigDecimal.ZERO;
        BigDecimal total_saida = BigDecimal.ZERO;
        BigDecimal saldo = BigDecimal.ZERO;

        StringBuilder transacoes = new StringBuilder("Transações");

        for(TreinoAplicacao ta : treinoAplicacoes)
        {
            if(ta.getTipoMovimentacaoFinanceira().equals("Entrada"))
            {
                total_entrada = total_entrada.add(ta.getValorMovimentacaoFinanceiroBigD());
            }
            else if(ta.getTipoMovimentacaoFinanceira().equals("Saída"))
            {
                total_saida = total_saida.add(ta.getValorMovimentacaoFinanceiroBigD());
            }
            
            transacoes.append("\nValor: " + ta.getValorMovimentacaoFinanceiro());
            transacoes.append("\nTipo: " + ta.getTipoMovimentacaoFinanceira());
            transacoes.append("\nDescrição: " + ta.getDescricaoMovimentacaoFinanceira());
            transacoes.append("\n");
        }

        saldo = saldo.add(total_entrada);
        saldo = saldo.subtract(total_saida);

        // System.out.println("\n" + total_entrada);
        // System.out.println("\n" + total_saida);
        // System.out.println("\n" + saldo);
        // System.out.println("\n" + transacoes);

        String nome_arq = "relatorio" + getNumRelatorio();
        try (FileOutputStream file = new FileOutputStream(new File(caminhoArquivo + nome_arq + ".pdf"))) {

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(350);
            table.setLockedWidth(true);
            table.setWidths(new float[]{2, 1});
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Relatório Financeiro"));
            cell.setColspan(2);
            table.addCell(cell);
            table.addCell("Entrada");
            table.addCell("R$ " + total_entrada.toString());

            table.addCell("Saída");
            table.addCell("R$ " + total_saida.toString());

            table.addCell("Saldo");
            table.addCell("R$ " + saldo.toString());
            document.add(table);

            document.add(new Paragraph(transacoes.toString(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            
            document.close();

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    */

}
