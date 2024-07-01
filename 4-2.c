#include <stdio.h>
#include <stdlib.h>

void printIntersection(int *arr1, int n1, int *arr2, int n2);

int main() {

    int *arr1, *arr2, n1=0, n2=0;

    FILE *f1 = fopen("f2_1.txt", "r");
    FILE *f2 = fopen("f2_2.txt", "r");

    if (f1 == NULL || f2 == NULL) {
        printf("파일을 열 수 없습니다.");
        exit(1);
    }

    fscanf(f1, "%d", &n1);
    arr1 = (int*)malloc(n1 * sizeof(int));
    for (int i = 0; i < n1; i++) {
        fscanf(f1, "%d", &arr1[i]);
    }

    fscanf(f2, "%d", &n2);
    arr2 = (int*)malloc(n2 * sizeof(int));
    for (int i = 0; i < n2; i++) {
        fscanf(f2, "%d", &arr2[i]);
    }

    printIntersection(arr1, n1, arr2, n2);

    free(arr1);
    free(arr2);
    fclose(f1);
    fclose(f2);

    return 0;
}

void printIntersection(int *arr1, int n1, int *arr2, int n2) {
    int i = 0, j = 0;

    while (i < n1 && j < n2) {
        if (arr1[i] < arr2[j]) {
            i++;
        }
        else if (arr1[i] > arr2[j]) {
            j++;
        }
        else {
            printf("%d ", arr1[i]);
            i++;
            j++;
        }
    }
}

