package helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.Invoice;
import models.InvoiceItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class PdfHelper {
    Document doc;
    PdfWriter writer;
    Invoice invoice;

    public PdfHelper(Invoice invoice, String documentAddress) {
        this.invoice = invoice;
        try{
            File file = new File(documentAddress);
            if(!file.exists()){
                file.createNewFile();
            }
            this.doc = new Document(PageSize.A4);
            this.writer = PdfWriter.getInstance(this.doc,new FileOutputStream(documentAddress));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private Paragraph title(String txt){
        Paragraph p = new Paragraph(new Phrase(2f,txt,FontFactory.getFont(FontFactory.COURIER,15.8f)));
        p.setAlignment(Element.ALIGN_CENTER);
        return p;
    }

    private PdfPCell cell(String txt){
        PdfPCell cell = new PdfPCell(new Phrase(0f,txt,FontFactory.getFont(FontFactory.COURIER)));
        cell.setPaddingLeft(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPCell headCell(String txt){
        PdfPCell cell = new PdfPCell(new Phrase(1f,txt,FontFactory.getFont(FontFactory.COURIER_BOLD)));
        cell.setPaddingLeft(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
    private PdfPCell voidCell(){
        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        return cell;
    }

    private PdfPTable table() throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        float[] widths = {10f,30f,10f,10f,10f};
        table.setWidths(widths);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        Iterator<InvoiceItem> it = invoice.items.iterator();
        PdfPCell numberHead =  this.headCell("#");
        table.addCell(numberHead);
        PdfPCell nameHead =  this.headCell("Name");
        table.addCell(nameHead);
        PdfPCell feeHead =  this.headCell("Qty.");
        table.addCell(feeHead);
        PdfPCell priceHead = this.headCell("Unit Price");
        table.addCell(priceHead);
        PdfPCell totalPriceHead = this.headCell("Total Price");
        table.addCell(totalPriceHead);
        int i=0;
        while (it.hasNext()){
            i++;
            InvoiceItem item = it.next();
            PdfPCell number =  this.cell(String.valueOf(i));
            table.addCell(number);
            PdfPCell name =  this.cell(item.product.name);
            table.addCell(name);
            PdfPCell fee =  this.cell(String.valueOf(item.quantity));
            table.addCell(fee);
            PdfPCell price = this.cell(String.valueOf(item.price));
            table.addCell(price);
            PdfPCell totalPrice = this.cell(String.valueOf(item.getTotalPrice()));
            table.addCell(totalPrice);
        }
        PdfPCell footerTitle =  this.cell("Total:");
        table.addCell(footerTitle);
        PdfPCell footer =  this.cell(String.valueOf(invoice.getTotalCost()));
        table.addCell(footer);
        table.addCell(this.voidCell());
        table.addCell(this.voidCell());
        table.addCell(this.voidCell());
        return table;

    }

    public void printInvoice(){
        try {
            doc.open();
            doc.addAuthor("Bill Manager Software");
            doc.addCreationDate();
            doc.addCreator("Nima Afshaer");
            doc.addTitle("Invoice #"+invoice.id);
            doc.add(this.title("Costumer : "+invoice.costumerName + "@ "+invoice.date.toString()));
            doc.add(this.table());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.close();
        writer.close();
    }
}
