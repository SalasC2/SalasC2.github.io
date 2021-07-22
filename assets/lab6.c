// Name: Christian Salas
// Date: 04/14/20
// Title: Lab6 – Producer Consumer Problem
// Description: A Producer Consumer example of consuming the alphabet with threads in C

#include <stdio.h>
#include <unistd.h>
#include <pthread.h> 
#include <semaphore.h> 

#define max_buffer 27

int buffer[max_buffer];
pthread_t producer_thread;
pthread_t consumer_thread;
sem_t mutex, empty, full;
char alphabet_a = 97; // a
char alphabet_z = 122; // z 


void *producer(void *arg) {
    char alphabet_a; // start alphabet counting at a (97) 
    int counter = 0;
    printf("Producing Alphabet\n");
    sleep(1);
    // Loop from a-z (97-122)
    for (alphabet_a = 97; alphabet_a <= alphabet_z; alphabet_a++) {
        sem_wait(&empty);
        sem_wait(&mutex); 
        counter++;
        buffer[counter] = alphabet_a;
        printf("Producing %c\n", alphabet_a);
        sem_post(&mutex); 
        sem_post(&full);

    }
}

void *consumer(void *arg) {
    char alphabet_a; // start alphabet counting at a (97) 
    int counter = 0;
    sleep(2);
    printf("Consuming Alphabet\n");
    // Loop from a-z (97-122)
    for (alphabet_a = 97; alphabet_a <= alphabet_z; alphabet_a++) {
        sem_wait(&full);
        sem_wait(&mutex); 
        counter++;
        int b = buffer[counter];
        sleep(1);
        sem_post(&mutex);
        sem_post(&empty);
        printf("Consuming %c\n", b);
    }
}

int main() {
    sem_init(&empty, 0, max_buffer);
    sem_init(&full, 0, 1);
    sem_init(&mutex, 0, 2);
    pthread_create(&producer_thread, NULL, producer, 0);
    pthread_create(&consumer_thread, NULL, consumer, 0);
    pthread_join(producer_thread, NULL);
    pthread_join(consumer_thread, NULL);
    printf("Main thread done.\n");
    sem_destroy(&mutex); //drop the semaphone when finished. 
    sem_destroy(&full); //drop the semaphone when finished. 
    sem_destroy(&empty); //drop the semaphone when finished. 
    return 0;
}
