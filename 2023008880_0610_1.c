#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char word[50];
    int CoVal;
    struct Node *next;
} Node;

typedef struct {
    Node **table;
    int size;
} HashTable;

int Hash(char *word, int D);
HashTable* MHashTable(int size);
int CalVal(char *word);
void WordIn(HashTable *hashTable, char *word);
Node* SearhWord(HashTable *hashTable, char *word);
void PrintfHash(HashTable *hashTable);

int main() {
    int D;
    scanf("%d", &D);

    HashTable *hashTable = MHashTable(D);

    FILE *InF = fopen("in.txt", "r");
    char word[50];
    if (InF == NULL) {
        printf("in.txt열기 오류\n");
        exit(1);
    }
    while (fscanf(InF, "%s", word) != EOF) {
        WordIn(hashTable, word);
    }
    fclose(InF);

    FILE *SeF = fopen("search.txt", "r");
    if (SeF == NULL) {
        printf("search.txt열기 오류\n");
        exit(1);
    }
    while (fscanf(SeF, "%s", word) != EOF) {
        Node *result = SearhWord(hashTable, word);
        if (result) {
            printf("S\n");
        } else {
            printf("E\n");
        }
    }
    fclose(SeF);

    PrintfHash(hashTable);
    for (int i = 0; i < hashTable->size; i++) {
        Node *now = hashTable->table[i];
        while (now) {
            Node *temp = now;
            now = now->next;
            free(temp);
        }
    }
    free(hashTable->table);
    free(hashTable);

    return 0;
}

HashTable* MHashTable(int size) {
    HashTable *hashTable = (HashTable*) malloc(sizeof(HashTable));
    hashTable->size = size;
    hashTable->table = (Node**) malloc(size * sizeof(Node*));
    for (int i = 0; i < size; i++) {
        hashTable->table[i] = NULL;
    }
    return hashTable;
}

int Hash(char *word, int D) {
    int sum = 0;
    while (*word) {
        sum += *word;
        word++;
    }
    return sum % D;
}

int CalVal(char *word) {
    int sum = 0;
    while (*word) {
        sum += *word;
        word++;
    }
    return sum;
}

Node* SearhWord(HashTable *hashTable, char *word) {
    int index = Hash(word, hashTable->size);
    Node *now = hashTable->table[index];
    while (now) {
        if (strcmp(now->word, word) == 0) {
            return now;
        }
        now = now->next;
    }
    return NULL;
}

void WordIn(HashTable *hashTable, char *word) {
    int index = Hash(word, hashTable->size);
    Node *newNode = (Node*) malloc(sizeof(Node));
    strcpy(newNode->word, word);
    newNode->CoVal = CalVal(word);
    newNode->next = hashTable->table[index];
    hashTable->table[index] = newNode;
}

void PrintfHash(HashTable *hashTable) {
    for (int i = 0; i < hashTable->size; i++) {
        printf("%d:", i);
        Node *now = hashTable->table[i];
        while (now) {
            printf(" %s (%d)", now->word, now->CoVal);
            now = now->next;
        }
        printf("\n");
    }
}
