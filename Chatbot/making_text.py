n = []
flag = 1

for i in range(161):
    input1 = input()
    inputList = input1.split()
    for s in inputList :
        if s == '##':
            continue
        elif s[-1] == '.':
            continue
        elif flag == 1 :
            flag = 0
            continue
        else :
            flag = 1
            n.append(s)


print(n)
for w in n :
    print(w)
