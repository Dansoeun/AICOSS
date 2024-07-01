#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

Node* readList(char *fname);
Node* UList(Node *list1, Node *list2);
void PrintList(Node *list);
void freeList(Node *list);

int main() {
    Node *list1 = readList("in3.txt");
    Node *list2 = readList("in4.txt");

    Node *res = UList(list1, list2);

    PrintList(res);

    freeList(list1);
    freeList(list2);
    freeList(res);

    return 0;
}

void PrintList(Node *list) {
    while (list != NULL) {
        printf("%d ", list->data);
        list = list->next;
    }
    printf("\n");
}

void freeList(Node *list) {
    while (list != NULL) {
        Node *temp = list;
        list = list->next;
        free(temp);
    }
}


Node* readList(char *fname) {
    FILE *file = fopen(fname, "r");
    if (file == NULL) {
        printf("파일 열 수 없음\n", fname);
        exit(1);
    }

    Node *head = NULL;
    Node *tail = NULL;

    int num;
    while (fscanf(file, "%d", &num) != EOF) {
        Node *nNode = (Node*) malloc(sizeof(Node));
        nNode->data = num;
        nNode->next = NULL;

        if (head == NULL) {
            head = nNode;
            tail = nNode;
        } else {
            tail->next = nNode;
            tail = nNode;
        }
    }

    fclose(file);
    return head;
}

Node* UList(Node *list1, Node *list2) {
    Node *res = NULL;
    Node *tail = NULL;

    while (list1 != NULL && list2 != NULL) {
        int val;
        if (list1->data == list2->data) {
            val = list1->data;
            list1 = list1->next;
            list2 = list2->next;
        } else if (list1->data < list2->data) {
            val = list1->data;
            list1 = list1->next;
        } else {
            val = list2->data;
            list2 = list2->next;
        }

        if (res == NULL) {
            res = (Node*) malloc(sizeof(Node));
            res->data = val;
            res->next = NULL;
            tail = res;
        } else if (tail->data != val) { // 중복 방지
            Node *nNode = (Node*) malloc(sizeof(Node));
            nNode->data = val;
            nNode->next = NULL;
            tail->next = nNode;
            tail = nNode;
        }
    }

    while (list1 != NULL) {
        if (tail == NULL || tail->data != list1->data) {
            Node *nNode = (Node*) malloc(sizeof(Node));
            nNode->data = list1->data;
            nNode->next = NULL;
            if (tail == NULL) {
                res = nNode;
                tail = nNode;
            } else {
                tail->next = nNode;
                tail = nNode;
            }
        }
        list1 = list1->next;
    }

    while (list2 != NULL) {
        if (tail == NULL || tail->data != list2->data) {
            Node *nNode = (Node*) malloc(sizeof(Node));
            nNode->data = list2->data;
            nNode->next = NULL;
            if (tail == NULL) {
                res = nNode;
                tail = nNode;
            } else {
                tail->next = nNode;
                tail = nNode;
            }
        }
        list2 = list2->next;
    }

    return res;
}
