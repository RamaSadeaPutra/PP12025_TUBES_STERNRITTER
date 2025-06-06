package MaintenanceTravel;


public class StrukturList {
    private Node HEAD;

    public StrukturList() {
        this.HEAD = null;
    }

    public void addHead(Kendaraan data) {
        Node newNode = new Node(data);
        newNode.setNext(HEAD);
        HEAD = newNode;
    }

    public void addTail(Kendaraan data) {
        Node newNode = new Node(data);
        if (HEAD == null) {
            HEAD = newNode;
        } else {
            Node curNode = HEAD;
            while (curNode.getNext() != null) {
                curNode = curNode.getNext();
            }
            curNode.setNext(newNode);
        }
    }

    public void addMid(Kendaraan data, int posisi) {
        if (posisi <= 1 || HEAD == null) {
            addHead(data);
        } else {
            Node newNode = new Node(data);
            Node curNode = HEAD;
            int index = 1;

            
            while (curNode.getNext() != null && index < posisi - 1) {
                curNode = curNode.getNext();
                index++;
            }
            
            
            if (curNode.getNext() == null && index < posisi -1) { 
                addTail(data); 
            } else {
                newNode.setNext(curNode.getNext());
                curNode.setNext(newNode);
            }
        }
    }

   