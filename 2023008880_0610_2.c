#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char word[50];
    int CoVal;
    struct Node *left, *right;
} Node;

typedef struct {
    Node **table;
    int size;
    int count; // 현재 해시 테이블에 저장된 요소 수
} HashTable;

int Hash(char *word, int D);
HashTable* MHashTable(int size);
int CalVal(char *word);
void WordIn(HashTable *hashTable, char *word);
Node* SearhWord(HashTable *hashTable, char *word);
void PrintfHash(HashTable *hashTable);
void Rehash(HashTable **hashTable);
Node* insertBST(Node* node, char *word);
Node* searchBST(Node* node, char *word);
void printBST(Node* node);
void freeBST(Node* node);
void rehashBST(HashTable *newHashTable, Node *node);

int main() {
    int D;
    scanf("%d", &D);

    HashTable *hashTable = MHashTable(D);

    FILE *InF = fopen("in.txt", "r");
    char word[50];
    if (InF == NULL) {
        printf("in.txt 열기 오류\n");
        exit(1);
    }
    while (fscanf(InF, "%s", word) != EOF) {
        WordIn(hashTable, word);
    }
    fclose(InF);

    FILE *SeF = fopen("search.txt", "r");
    if (SeF == NULL) {
        printf("search.txt 열기 오류\n");
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
        freeBST(hashTable->table[i]);
    }
    free(hashTable->table);
    free(hashTable);

    return 0;
}

HashTable* MHashTable(int size) {
    HashTable *hashTable = (HashTable*) malloc(sizeof(HashTable));
    hashTable->size = size;
    hashTable->table = (Node**) malloc(size * sizeof(Node*));
    hashTable->count = 0;
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
    return searchBST(hashTable->table[index], word);
}

void WordIn(HashTable *hashTable, char *word) {
    if ((float)hashTable->count / hashTable->size > 0.75) {
        Rehash(&hashTable);
    }
    int index = Hash(word, hashTable->size);
    hashTable->table[index] = insertBST(hashTable->table[index], word);
    hashTable->count++;
}

void PrintfHash(HashTable *hashTable) {
    for (int i = 0; i < hashTable->size; i++) {
        printf("%d:", i);
        if (hashTable->table[i]) {
            printBST(hashTable->table[i]);
        }
        printf("\n");
    }
}

Node* insertBST(Node* node, char *word) {
    if (node == NULL) {
        node = (Node*) malloc(sizeof(Node));
        strcpy(node->word, word);
        node->CoVal = CalVal(word);
        node->left = node->right = NULL;
    } else if (strcmp(word, node->word) < 0) {
        node->left = insertBST(node->left, word);
    } else {
        node->right = insertBST(node->right, word);
    }
    return node;
}

Node* searchBST(Node* node, char *word) {
    if (node == NULL || strcmp(word, node->word) == 0) {
        return node;
    }
    if (strcmp(word, node->word) < 0) {
        return searchBST(node->left, word);
    } else {
        return searchBST(node->right, word);
    }
}

void printBST(Node* node) {
    if (node) {
        printBST(node->left);
        printf(" %s (%d)", node->word, node->CoVal);
        printBST(node->right);
    }
}

void freeBST(Node* node) {
    if (node) {
        freeBST(node->left);
        freeBST(node->right);
        free(node);
    }
}

void Rehash(HashTable **oldHashTable) {
    int newSize = (*oldHashTable)->size * 2 + 1;
    HashTable *newHashTable = MHashTable(newSize);

    for (int i = 0; i < (*oldHashTable)->size; i++) {
        rehashBST(newHashTable, (*oldHashTable)->table[i]);
    }

    for (int i = 0; i < (*oldHashTable)->size; i++) {
        freeBST((*oldHashTable)->table[i]);
    }
    free((*oldHashTable)->table);
    *oldHashTable = newHashTable;
}

void rehashBST(HashTable *newHashTable, Node *node) {
    if (node) {
        WordIn(newHashTable, node->word);
        rehashBST(newHashTable, node->left);
        rehashBST(newHashTable, node->right);
    }
}