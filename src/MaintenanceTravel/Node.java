package MaintenanceTravel;

public class Node {
    private Kendaraan data;
    private Node next;

    public Node(Kendaraan data) {
        this.data = data;
        this.next = null;
    }

    public Kendaraan getData() {
        return data;
    }

    public void setData(Kendaraan data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}