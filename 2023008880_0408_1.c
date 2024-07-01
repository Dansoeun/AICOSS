#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int coef;
    int exp;
    struct Node *next;
} Node;

Node* ReadPoly(const char *fname);
Node* AddPoly(Node *poly1, Node *poly2);
void PrintPoly(Node *poly);
void FreePolyl(Node *poly);

int main() {
    Node *poly1 = ReadPoly("in11.txt");
    Node *poly2 = ReadPoly("in12.txt");

    Node *result = AddPoly(poly1, poly2);

    PrintPoly(result);

    FreePolyl(poly1);
    FreePolyl(poly2);
    FreePolyl(result);

    return 0;
}


void FreePolyl(Node *poly) {
    while (poly != NULL) {
        Node *temp = poly;
        poly = poly->next;
        free(temp);
    }
}


void PrintPoly(Node *poly) {
    while (poly != NULL) {
        printf("%d %d ", poly->coef, poly->exp);
        poly = poly->next;
    }
    printf("\n");
}


Node* ReadPoly(const char *fname) {
    FILE *file = fopen(fname, "r");
    if (file == NULL) {
        printf("열 수 없음\n", fname);
        exit(1);
    }

    Node *head = NULL;
    Node *tail = NULL;

    int coeff, exp;
    while (fscanf(file, "%d %d", &coeff, &exp) != EOF) {
        Node *nNode = (Node*) malloc(sizeof(Node));
        nNode->coef = coeff;
        nNode->exp = exp;
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

Node* AddPoly(Node *poly1, Node *poly2) {
    Node *result = NULL;
    Node *tail = NULL;

    while (poly1 != NULL && poly2 != NULL) {
        int coeff, exp;
        if (poly1->exp == poly2->exp) {
            coeff = poly1->coef + poly2->coef;
            exp = poly1->exp;
            poly1 = poly1->next;
            poly2 = poly2->next;
        } else if (poly1->exp > poly2->exp) {
            coeff = poly1->coef;
            exp = poly1->exp;
            poly1 = poly1->next;
        } else {
            coeff = poly2->coef;
            exp = poly2->exp;
            poly2 = poly2->next;
        }

        if (coeff != 0) {
            Node *nNode = (Node*) malloc(sizeof(Node));
            nNode->coef = coeff;
            nNode->exp = exp;
            nNode->next = NULL;

            if (result == NULL) {
                result = nNode;
                tail = nNode;
            } else {
                tail->next = nNode;
                tail = nNode;
            }
        }
    }

    while (poly1 != NULL) {
        Node *nNode = (Node*) malloc(sizeof(Node));
        nNode->coef = poly1->coef;
        nNode->exp = poly1->exp;
        nNode->next = NULL;

        if (result == NULL) {
            result = nNode;
            tail = nNode;
        } else {
            tail->next = nNode;
            tail = nNode;
        }

        poly1 = poly1->next;
    }

    while (poly2 != NULL) {
        Node *nNode = (Node*) malloc(sizeof(Node));
        nNode->coef = poly2->coef;
        nNode->exp = poly2->exp;
        nNode->next = NULL;

        if (result == NULL) {
            result = nNode;
            tail = nNode;
        } else {
            tail->next = nNode;
            tail = nNode;
        }

        poly2 = poly2->next;
    }

    return result;
}
