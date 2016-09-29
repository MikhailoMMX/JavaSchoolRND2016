package ru.microbyte.xml;


@Profiling
public class PaperPrinter implements Printer{

    @Publisher("O`Reily")
    private String publisher;

    private String year;

    public PaperPrinter() {
        System.out.println("I'm created");
    }

    @Override
    public void out(PrintData printData) {
        System.out.println("Paper printer: " + printData.getData() + ", Publisher: " + publisher + " year " + year);
    }

    public void setYear(String year) {
        this.year = year;
    }
}
