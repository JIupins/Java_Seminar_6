public class Laptop {
    private String processor;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Laptop(String processor, int ram, int hdd, String os, String color) {
        this.processor = processor;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getProcessor() {
        return processor;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop: " +
                "Processor=" + processor +
                ", RAM=" + ram +
                ", HDD=" + hdd +
                ", OS=" + os  +
                ", color=" + color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Laptop)) {
            return false;
        }
        Laptop notebook = (Laptop) obj;
        return processor == notebook.processor && ram == notebook.ram && hdd == notebook.hdd && os.equals(notebook.os)&& color.equals(notebook.color);
    }

    @Override
    public int hashCode() {
        return 5 * processor.hashCode() + 7 * ram + 9 * hdd + 11 * os.hashCode() + 15 * color.hashCode();
    }
}