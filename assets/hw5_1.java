/*
 * Title: hw5_1.java
 * Abstract: This program 
 
 * Author: Christian Salas
 * ID: 5483
 * Date: 12/08/2020
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Heap { 
    int[] heap;
    int[] input;
    int maxSize;
    int heapSize; // This is going to be the size that's given from user
    int maxHeapSize = 1000; // setting to a large number for insert. TODO: Fix the insert method. 
    
    public Heap(int maxSize) { 
        this.heapSize = 0;
        this.maxSize = maxSize;
        this.heap = new int[this.maxHeapSize];
        this.input = new int[this.maxHeapSize];
    }
    
    public void insert(int ele) { 
        // if (heapSize == maxSize) { 
        //     this.maxSize++;
        //     int[] updatedHeap = new int[this.maxSize];
        //     updatedHeap = heap;
        //     heap = new int[this.maxSize];
        //     this.heap[heapSize] = ele;
        //     heapSize++;
        // }
        // else {
            this.heap[heapSize] = ele;
            this.input[heapSize] = ele;
            this.bubbleUp();
            heapSize++;
        // }
    }
    
    public void remove(int ele) { 
        int parent = 0;
        int delVal = 0;
        int idx = 0;
        
        for (int i=0; i < maxSize; i++) { 
            if (ele == heap[i])
                idx = i;
        }
        delVal = heap[idx];
        
        maxSize--;
        heapSize--;
        
        parent = idx/2;
        
        if (idx == 1 || heap[parent] < heap[idx])
            this.sinkDown();
        else
            this.bubbleUp();
         
        
    }
    
    public void bubbleUp() { 
        int idx = this.heapSize;
        int element = this.heap[idx];
        
        while (idx > 0) { 
            int parentIdx = (int)Math.floor((idx-1)/2);
            int parent = this.heap[parentIdx];
            if(element <= parent) break;
            this.heap[parentIdx] = element;
            this.heap[idx] = parent;
            idx = parentIdx;
        }
    }
    

    
    // public boolean insert(int ele) { 
    //     if (heapSize == maxSize) return false;
    //     heap[++heapSize] = ele;
    //     input[heapSize] = ele;
    //     int pos = heapSize;
    //     while (pos!=1 && ele > heap[pos/2]) { 
    //         heap[pos] = heap[pos/2];
    //         pos /= 2;
    //     }
    //     heap[pos] = ele;

    //     return true;
    //  }
     
     public void deleteMax() { 
        int max = this.heap[0];
        int end = this.heap[--maxSize];

        if (this.maxSize > 0) { 
            this.heap[0] = end;
            this.sinkDown();
        }
        // System.out.println(max);
     }
     
    public void update(int idx, int ele) { 
        this.heap[idx-1] = ele;
        int nextEle = this.heap[1];
        
        this.heap[1] = ele;
        this.heap[idx-1] = nextEle;
        this.sinkDown();
    }
     
     public void sinkDown() { 
        int idx = 0;
        int length = this.heapSize;
        int element = this.heap[0];
        
        while(true) { 
            int leftChildIdx = 2 * idx+1;
            int rightChildIdx = 2 * idx+2;
            int leftChild = 0;
            int rightChild = 0;
            int swap = -1; 
            
            if(leftChildIdx < length) { 
                leftChild = this.heap[leftChildIdx];
                if (leftChild > element) { 
                    swap = leftChildIdx;   
                }
            }
            
            if(rightChildIdx < length) {
                rightChild = this.heap[rightChildIdx];
                if ((swap == -1 && rightChild > element) || (swap != -1 && rightChild > leftChild)) { 
                    swap = rightChildIdx;
                }
            }
            
            if (swap == -1) break;
            this.heap[idx] = this.heap[swap];
            this.heap[swap] = element;
            idx = swap;
        }
     }
    
    public void isHeap() { 
        if (Arrays.equals(this.heap, this.input)) 
            System.out.println("This is a heap.");   
        else
            System.out.println("This is NOT a heap.");   
    }
    
    public void displayMax() { 
        System.out.println(this.heap[0]);   
    }
    
    public void display() { 
        for (int i = 0; i < this.maxSize; i++)
            System.out.print(this.heap[i] + " ");
        System.out.println("");
    }
    
}

class hw5_1 { 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] input = new int[n];
        Heap heap = new Heap(n);
        
        for(int i = 0; i < n; i++) {
            int l = scan.nextInt();
            heap.insert(l);
        }
        int op = scan.nextInt();
        
        String arr[]= new String[n]; 
        for(int i = 0; i <= op; i++) {
            arr[i] = scan.nextLine();
        }
        System.out.println("");
        heap.isHeap();
        
        
        for (int i = 0; i <= op; i++) { 
            String split[] = arr[i].split(" ");
            if (split.length > 1) { 
                if (split[0].equals("update")) { 
                    heap.update(Integer.parseInt(split[1]), Integer.parseInt(split[2]));   
                }
                else if (split[0].equals("remove")) { 
                    heap.remove(Integer.parseInt(split[1]));
                }
                else if (split[0].equals("insert")) { 
                    heap.insert(Integer.parseInt(split[1]));   
                }
             }
             else { 
                if (split[0].equals("display")) { 
                    heap.display();
                }
                else if (split[0].equals("deleteMax")) { 
                    heap.deleteMax();  
                }
                else if (split[0].equals("displayMax")) { 
                    heap.displayMax();   
                }
             }
        }
        
    }
}