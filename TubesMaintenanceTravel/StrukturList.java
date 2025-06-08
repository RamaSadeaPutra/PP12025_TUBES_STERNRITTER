package TubesMaintenanceTravel;

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

         public void removeHead() {
        if (HEAD == null) {
            System.out.println("List kosong.");
        } else {
            Node temp = HEAD;
            HEAD = HEAD.getNext();
            dispose(temp);
            System.out.println("Data di head berhasil dihapus.");
        }
    }

    public void removeTail() {
        if (HEAD == null) {
            System.out.println("List kosong.");
        } else if (HEAD.getNext() == null) { 
            dispose(HEAD);
            HEAD = null;
            System.out.println("Satu-satunya elemen berhasil dihapus.");
        } else {
            Node curNode = HEAD;
            Node prevNode = null;

            while (curNode.getNext() != null) {
                prevNode = curNode;
                curNode = curNode.getNext();
            }

            prevNode.setNext(null);
            dispose(curNode);
            System.out.println("Data di tail berhasil dihapus.");
        }
    }

    public void removeMid(int posisi) {
        if (HEAD == null) {
            System.out.println("List kosong.");
        } else if (posisi <= 1) {
            removeHead();
        } else {
            Node curNode = HEAD;
            Node prevNode = null;
            int index = 1;

            while (curNode != null && index < posisi) {
                prevNode = curNode;
                curNode = curNode.getNext();
                index++;
            }

            if (curNode == null) {
                System.out.println("Posisi melebihi panjang list atau tidak valid.");
            } else {
                prevNode.setNext(curNode.getNext());
                dispose(curNode);
                System.out.println("Data di posisi " + posisi + " berhasil dihapus.");
            }
        }
    }

     public int size() {
        int count = 0;
        Node curNode = HEAD;
        while (curNode != null) {
            count++;
            curNode = curNode.getNext();
        }
        return count;
    }


    public boolean find(String noPol) {
        Node curNode = HEAD;
        while (curNode != null) {
            if (curNode.getData().getNoPol().equalsIgnoreCase(noPol)) {
                System.out.println("Data ditemukan:\n" + curNode.getData());
                return true;
            }
            curNode = curNode.getNext();
        }
        System.out.println("Data dengan NoPol '" + noPol + "' tidak ditemukan.");
        return false;
    }

public void clear() {
        while (HEAD != null) {
            Node temp = HEAD;
            HEAD = HEAD.getNext();
            dispose(temp);
        }
        System.out.println("Semua data berhasil dihapus.");
    }

    private void dispose(Node node) {
        node.setNext(null);
        node = null; 
    }

    public void displayElement() {
        if (HEAD == null) {
            System.out.println("List kosong.");
            return;
        }


        
    }

    
   
