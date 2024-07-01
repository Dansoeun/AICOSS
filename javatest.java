아래의 주어진 Class 다이어그램과 main() 코드를 이용하여 아래와 같이
출력하는 3개의 Class(Music, Classic, Pop)를 구현하시오. 

public class Main {
 public static void main(String[] args) {
 Music musicList[] = new Music[3];
 musicList[0] = new Music("흥부전", 1700);
 musicList[1] = new Classic("캐논", 1732, "파핼벨");
 musicList[2] = new Pop("바람이 분다", 2004, "이소라");
 for (Music m : musicList){
 System.out.println(m);
 }
 }
}

import java.util.Scanner;
class Music{
    public String name;
    public int year;

    public Music(String name,int year) {
        this.name=name;
        this.year=year;
    }

    @Override
    public String toString()
    {
        return "Music [곡명="+name+", 연도="+year+"]";
    }
}

class Classic extends Music{
    private String composer;

    public Classic(String name,int year,String composer){
        super(name, year);
        this.composer=composer;
    }

    @Override
    public String toString() {
        return "Classic [곡명="+name+", 연도="+year+", 작곡가="+composer+"]";
    }
}

class Pop extends Music{
    private String singer;

    public Pop(String name,int year,String singer){
        super(name, year);
        this.singer=singer;
    }

    @Override
    public String toString() {
        return "Pop [곡명="+name+", 연도="+year+", 가수="+singer+"]";
    }
}

Music Chart 기능 구현하기
아래의 주어진 Class 다이어그램과 프로그램 설명, 1번 문제에서 생성한 3개의
클래스를 이용하는 음악 차트 클래스(MusicChart)를 구현하시오. 

Classic 클래스와 Pop 클래스는 Music 클래스를 상속받는다. (다이어그램 참조)
MusicChart의 add 함수는 Music 클래스를 받아 MusicList에 넣는다.
Ex) musicChart.add(new Music(“흥부전”, 1700));
출력은 MusicChart 클래스만을 이용해서 출력한다.
1을 입력 받으면 Music, 2를 입력 받으면 Classic, 3을 입력 받으면 Pop을 입력받는다.
-1을 입력 받을 시에는 프로그램을 종료한다.
곡 명의 경우 한 줄로 입력을 받으며, 곡 명에는 띄어쓰기가 포함되지 않는다.

import java.util.ArrayList;

public class MusicChart
{
    private ArrayList<Music> musicList;
    public MusicChart()
    {
        musicList=new ArrayList<>();
    }
    public void add(Music m)
    {
        musicList.add(m);
    }

    @Override
    public String toString()
    {
        String result ="";
        for(Music music : musicList)
        {
            result+=music+"\n";
        }
        return result;
    }
}

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 Interface를 이용하여 추가기능 구현하기
주어진 코드와 다이어그램, 실행 결과를 참고하여, MusicChart 코드에 Interface Playable을
새롭게 추가하고, Play()를 구현하시오.

import javax.swing.*;
import java.util.*;
import  java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MusicChart musicChart = new MusicChart();

        while (true) {
            int choose = sc.nextInt();
            sc.nextLine();

            if (choose == -1) {
                break;
            }

            switch (choose) {
                case 1:
                    String name = sc.next();
                    int year = sc.nextInt();
                    musicChart.add(new Music(name, year));
                    break;
                case 2:
                    String name1 = sc.next();
                    int year1 = sc.nextInt();
                    String composer = sc.next();
                    musicChart.add(new Classic(name1, year1, composer));
                    break;
                case 3:
                    String name11 = sc.next();
                    int year11 = sc.nextInt();
                    String singer = sc.next();
                    musicChart.add(new Pop(name11, year11, singer));
                    break;
            }
        }
        for(Music music: musicChart.getMusicList())
            music.play();
    }
}

import java.util.Scanner;
class Music implements Playable{
    public String name;
    public int year;

    public Music(String name,int year) {
        this.name=name;
        this.year=year;
    }

    @Override
    public void play()
    {
        System.out.println("Music "+"'"+name+"'"+"을(를) 연주합니다.");
    }
}

class Classic extends Music{
    private String composer;

    public Classic(String name,int year,String composer){
        super(name, year);
        this.composer=composer;
    }

    @Override
    public void play()
    {
        System.out.println("Classic "+"'"+name+"'"+"을(를) 연주합니다.");
    }
}

class Pop extends Music{
    private String singer;

    public Pop(String name,int year,String singer){
        super(name, year);
        this.singer=singer;
    }

    @Override
    public void play()
    {
        System.out.println("Pop "+"'"+name+"'"+"을(를) 연주합니다.");
    }
}

interface Playable {
    void play();
}

---------------------------------------------------------------------

ArrayBlockingQueue를 사용한 Concurrency 만들기
강의자료 Fig.23.9.Buffer,Fig.23.10.Producer,Fig.23.11.Consumer,
Fig.23.14.BlockingBuffer, 그리고 Fig.23.15.BlockingBufferTest의 main
메소드를 참조하여 아래와 같은 결과가 나오도록 Relationship을 만들어보자. (단, 출력은 Consumer의 run 메소드에서만 실행한다.)

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class main
{
    public static void main(String[] args) throws InterruptedException
    {
        Scanner sc=new Scanner(System.in);
        int startval= sc.nextInt();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Buffer sharedLocation = new BlockingBuffer();
        executorService.execute(new Producer(sharedLocation,startval));
        executorService.execute(new Consumer(sharedLocation));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}

public class Unsynchrobuffer
{
    private int buffer=-1;

    public void blockingPut(int value) throws InterruptedException
    {
        buffer=value;
    }

    public int blockingGet() throws InterruptedException
    {
        return buffer;
    }
}

import java.security.SecureRandom;
public class Producer implements Runnable {
    private static final SecureRandom rnd = new SecureRandom();
    private final Buffer sharedLocation;
    private int startval;

    public Producer(Buffer sharedLocation, int startval) {
        this.sharedLocation = sharedLocation;
        this.startval = startval;
    }
    public void run() {
        try {
            int sum = startval;
            sharedLocation.blockingPut(sum);
            Thread.sleep(rnd.nextInt(3000));

            sum += startval+1;
            sharedLocation.blockingPut(sum);
            Thread.sleep(rnd.nextInt(3000));

            sum += startval+2;
            sharedLocation.blockingPut(sum);
            Thread.sleep(rnd.nextInt(3000));

        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}

import java.security.SecureRandom;
public class Consumer implements Runnable
{
    private static final SecureRandom ran = new SecureRandom();
    private final Buffer sharedLocation;

    public Consumer(Buffer sharedLocation)
    {
        this.sharedLocation = sharedLocation;
    }
    public void run() {
        try {
            for (int cnt = 1; cnt <= 3; cnt++) {
                int value = sharedLocation.blockingGet();
                System.out.printf("%d\n", value);
                Thread.sleep(ran.nextInt(3000));
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}

public interface Buffer
{
    public void blockingPut(int value) throws InterruptedException;
    public int blockingGet() throws InterruptedException;
}

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer
{
    private final ArrayBlockingQueue<Integer> buffer;

    public BlockingBuffer()
    {
        buffer = new ArrayBlockingQueue<>(10);
    }

    public void blockingPut(int value) throws InterruptedException
    {
        buffer.put(value);
    }

    public int blockingGet() throws  InterruptedException
    {
        return buffer.take();
    }
}

------------------------------------------------------------------------
SynchronizedDataSharing 구현해보기
강의자료 Fig23.6ArrayWriter,Fig.23.7SharedArrayTest의 main 메소드, Fig.23.8 SimpleArray를 참고하여 아래와 같은 결과가 나오도록 구현해보자.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc=new Scanner(System.in);
        int firstval=sc.nextInt();
        int secondval=sc.nextInt();
        SimAry sharedSimAry = new SimAry(6);
        ArrayWriter writer1 = new ArrayWriter(firstval, sharedSimAry);
        ArrayWriter writer2 = new ArrayWriter(secondval, sharedSimAry);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writer1);
        executorService.execute(writer2);

        executorService.shutdown();
        try {
            boolean taskend = executorService.awaitTermination(1, TimeUnit.MINUTES);
            if (taskend) {
                System.out.printf("Sum of SimpleArray:%d", sharedSimAry.getsum());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}


import java.security.SecureRandom;
import java.util.Arrays;

public class SimAry
{
    private int sum=0;
    private static final SecureRandom trans = new SecureRandom();
    private int[] array;
    private int writeIndex=0;

    public SimAry(int size)
    {
        array = new int[size];
    }

    public synchronized void add(int value)
    {
        int position=writeIndex;
        try
        {
            Thread.sleep(trans.nextInt(500));
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        array[position]=value;
        System.out.printf("Saved at element %d.",position);
        System.out.printf("\n");
        sum+=value;
        ++writeIndex;
    }
    public String toString()
    {
        return Arrays.toString(array);
    }

    public int getsum()
    {
        return sum;
    }
}

import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayWriter implements Runnable
{
    private final SimAry sharedSimAry;
    private final int startVal;

    public ArrayWriter(int value, SimAry array)
    {
        startVal = value;
        sharedSimAry = array;
    }

    public void run()
    {
        for(int i=startVal; i<startVal+3; i++)
        {
            sharedSimAry.add(i);

        }
    }
}

------------------------------------------------------------------

단어가 중복된 횟수 출력하기
시그널 ‘EOF’ (Windows에서 Ctrl+Z) 가 입력될 때까지 한 문단을 입력받아서, 해당 문단에서
중복해서 나타나는 단어들을 출력해보자. 단어의 대소문자는 무시하며 2회이상 중복해서
나타나는 단어들을 사전 순서대로 “단어 횟수” 형태로 출력해보자

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> wordcnt = new TreeMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("EOF")) {
                break;
            }

            line=line.replace(".", " ");
            String[] allword = line.toLowerCase().split(" ");
            for (String word : allword) {
                if (!word.isEmpty()) {
                    boolean duple = false;
                    for(String key: wordcnt.keySet()) {
                        if(key.equals(word)) {
                            wordcnt.put(key,wordcnt.get(key)+1);
                            duple = true;
                            break;
                        }
                    }
                    if(!duple) {
                        wordcnt.put(word,1);
                    }
                }
            }
        }

        for (String word : wordcnt.keySet()) {
            int i=wordcnt.get(word);
            if (i>=2) {
                System.out.println(word+" "+i);
            }
        }
    }
}

----------------------------------------------------------------

합집합, 교집합 출력하기
각각 0이 입력될 때까지, 두 집합을 입력한 다음, 두 집합의 합집합과 교집합을
각각 오름차순으로 정렬하여 아래와 같이 출력해보자. (Set을 사용하시오)

mport java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //집합
        Set<Integer> set = new TreeSet<>();
        Set<Integer> settt = new TreeSet<>();

        //0이 될때까지 무한 반복
        while (true) {
            int a = sc.nextInt();
            if (a == 0) {
                break;
            }
            set.add(a); //집합에 넣어
        }
        while (true) {
            int c = sc.nextInt();
            if (c == 0) {
                break;
            }
            settt.add(c);
        }

        Set<Integer> union = new TreeSet<>(set);
        union.addAll(settt);//집어넣는거 합집합
        Set<Integer> inter = new TreeSet<>();
        for(Integer i : set) {
            if(settt.contains(i))//settt이 i 포함 하면 추가
                inter.add(i);
        }
        System.out.println(union);
        System.out.println(inter);
    }
}

--------------------------------------------------------------------

최대, 최소값, palindrome 여부 검사
0이 입력될 때까지 정수를 입력 받은 다음, 해당 배열의 최대값, 최소값, palindrome(정방향/역방향 순열이 동일한 경우)인지 여부를 출력해보자. (Collection을 사용하시오)

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (true) {
            int a = sc.nextInt();
            if (a == 0) {
                break;
            }
            list.add(a);
        }
        int max = Collections.max(list);
        int min = Collections.min(list);
        boolean Pal = true;
        for (int i = 0; i < list.size()/ 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - i - 1))) {
                Pal = false;
                break;
            }
        }
        if (Pal==true) {
            System.out.printf("%d\n%d\nYes", max, min);
        } else {
            System.out.printf("%d\n%d\nNo", max, min);
        }
    }
}

----------------------------------------------------------------

연락처 클래스 만들기
아래의 주어진 ClassDiagram과 mainmethod를 참조하여 한 사람의 이름, 전화번호, 이메일을 저장하는 연락처 클래스를 구현하시오.

// main method
public static void eg1() {
Contact a = new Contact("aa", "000", "aa@a.com");
Scanner s = new Scanner(System.in);
a.setName(s.nextLine());
a.setPhone(s.nextLine());
a.setEmail(s.nextLine());
System.out.println(a);
}

class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "이름 : "+name+"\n전화번호 : "+phone+"\n이메일 : "+email;
    }

}

-----------------------------------------------------------------------

주소록 클래스 만들기
아래의 주어진 ClassDiagram과 Method 설명, 1번 문제에서 생성한 연락처를
저장할 수 있는 주소록 클래스를 구현하시오

// Method 설명
- addContact(con):ContactBook에 Contactcon을 추가한다.
- removeContact(num):ContactBook의 num번째에 저장된 Contact를 삭제한다.
- size():ContactBook 안에 들어 있는 Contact의 수를 반환한다.
- get(index:int):ContactBook의 index번째 Contact를 반환한다. 

public static void eg2(){
    ContactBook contactBook = new ContactBook();
    Scanner s=new Scanner(System.in);
    int choice=0,index;

    while(choice!=5){
        choice = s.nextInt();
        try{
            switch(choice){
            case1:
            {
                contactBook.addContact(newContact(s.next(), s.next(),s.next()));
                break;
            }
            case2:
            {
                index=s.nextInt();
                contactBook.removeContact(index);
                break;
            }
            case3:
            {
                System.out.println(contactBook.size());
                break;
            }
            case4:
            {
                index=s.nextInt();
                System.out.println(contactBook.get(index));
            }
            }
        }catch(IndexOutOfBoundsExceptione){
            System.out.println("IndexOutOfBoundsException");
        }
    }
    for(inti=0;i<contactBook.size();i++){
            System.out.println(contactBook.get(i));
    }
}

import java.util.ArrayList;
import java.util.Scanner;
class ContactBook {
    private ArrayList<Contact> contacts;
    public ContactBook(){
        contacts = new ArrayList<>();
    }

    public void addContact(Contact con){
        contacts.add(con);
    }

    public void removeContact(int num){
        contacts.remove(num);
    }

    public int size()
    {
        return contacts.size();
    }

    public Contact get(int index)
    {
        return contacts.get(index);
    }
}

------------------------------------------------------------------------

//Exception Handling연습
본 프로그램은 Menu 형태로 구성됩니다.
먼저, 0으로 초기화 한 정수 10개를 담을 수 있는 Array를 선언합니다.
그리고, 메뉴를 반복적으로 입력 받습니다.
- 1을 입력할 시, Input에 0이 입력될 때까지 정수로 입력을 받아 배열에 저장합니다.
이 때, 정수가 아닌 값이 입력된다면(InputMismatchException) "입력 값이 잘못되었음"을
출력합니다.
- 2를 입력할 시, 배열의 모든 값을 출력합니다.
- 3을 입력할 시, Input을 받고 Input에 해당하는 Array[Index]를 출력합니다.
이 때, Index에 10이상의 값이 입력된다면(ArrayIndexOutOfBoundsException) "배열
범위 초과"를 출력합니다.
- 4를 입력할 시, 전체 Array의 평균 값을 출력합니다. 이 때, 전체 Array의 길이가 0일
경우(ArithmeticException) "0으로 나눌 수 없음"을 출력합니다.
- 5를 입력할 시, 프로그램이 종료됩니다.
- 이 외 다른 값이 입력되었을 때는 "입력 값이 잘못되었음"을 출력합니다.

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] ary = new int[10];
        Scanner sc = new Scanner(System.in);
        int cnt = 0;

        while (true) {
            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("입력 값이 잘못되었음");
                sc.nextLine();
            }
            switch (choice) {
                case 1:
                    try {
                        while (true) {
                            int num = sc.nextInt();
                            if (num == 0)
                                break;
                            ary[cnt++] = num;
                            if (cnt >= ary.length)
                                throw new ArrayIndexOutOfBoundsException();
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("입력 값이 잘못되었음");
                        sc.nextLine();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("배열 범위 초과");
                        break;
                    }
                    break;
                case 2:
                    for (int i = 0; i < ary.length; i++) {
                        System.out.print(ary[i] + " ");
                    }
                    System.out.println();
                    break;
                case 3:
                    int index;
                    try {
                        index = sc.nextInt();
                        if (index < 0 || index >= ary.length) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        System.out.println(ary[index]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("배열 범위 초과");
                        break;
                    }
                    break;
                case 4:
                    try {
                        if (cnt == 0) {
                            throw new ArithmeticException("0으로 나눌 수 없음");
                        }
                        int sum = 0;
                        for (int i = 0; i < cnt; i++) {
                            sum += ary[i];
                        }
                        double average = (double) sum / cnt;
                        System.out.println(average);
                    }
                    catch (ArithmeticException e) {
                        System.out.println("0으로 나눌 수 없음");
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}

------------------------------------------------------------

아래의 주어진 main코드와 결과 화면을 참고하여 문자열을 압축하고, 압축
해제하는 myZip과 myUnZip메소드를 Zip 클래스를 만들어서 구현하시오.
//Main Method(메소드명을 제외하고 수정불가)
public static void main(String[] args) {
Scanner s = new Scanner(System.in);
String text1=s.nextLine();
Zip z = new Zip();
String text2 = z.myZip(text1);
String text3 = z.myUnZip(text2);
System.out.println(text1);
System.out.println(text2);
System.out.println(text3);
}

class Zip {
    public String myZip(String text) {
        StringBuilder zip = new StringBuilder();

        int cnt = 1;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == text.charAt(i - 1)) {
                cnt++;
            } else {

                zip.append(text.charAt(i - 1));
                zip.append(cnt);
                cnt = 1;
            }
        }
        zip.append(text.charAt(text.length() - 1));
        zip.append(cnt);

        return zip.toString();
    }

    public String myUnZip(String zipText) {
        StringBuilder dezip = new StringBuilder();

        for (int j = 0; j < zipText.length(); j += 2) {
            char ch = zipText.charAt(j);
            int cnt = Character.getNumericValue(zipText.charAt(j + 1));

            for (int i = 0; i < cnt; i++)
            {
                dezip.append(ch);
            }
        }
        return dezip.toString();
    }
}

-------------------------------------------------
핸드폰 GUI 만들기
오른쪽에 주어진 GUI를 참고하고 아래의 주어진 변수를 모두 사용하여
MyPhone 클래스를 구현하라.

// 사용할 변수들
private JPanel lcdJPanel;
// 위쪽의 출력 창
private JTextArea lcdJTextArea;
// 출력창에 위치할 텍스트 상자
private String lcdOutput = "";
// 텍스트 상자에 출력될 문자열을 임시로 저장
private JPanel keyJPanel;
// 아래쪽의 버튼 창
private JButton keyJButton[];
// 버튼창에 위치할 15 개의 JButton 배열

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        MyPhone myphone = new MyPhone();
        myphone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myphone.setSize(400, 650);
        myphone.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPhone extends JFrame{
    private JPanel lcdJPanel;
    private JTextArea lcdJTextArea;
    private JPanel keyJPanel;
    private JButton keyJButton[];

    public MyPhone() {
        setTitle("MyPhone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lcdJPanel = new JPanel();
        lcdJPanel.setLayout(new BorderLayout());
        lcdJTextArea = new JTextArea(10, 15);
        lcdJTextArea.setEditable(false);
        lcdJPanel.add(lcdJTextArea, BorderLayout.CENTER);
        add(lcdJPanel, BorderLayout.NORTH);


        keyJPanel = new JPanel();
        keyJPanel.setLayout(new GridLayout(5, 3));
        keyJButton = new JButton[15];

        keyJButton[0] = new JButton("Send");
        keyJPanel.add(keyJButton[0]);
        keyJButton[1] = new JButton("clr");
        keyJPanel.add(keyJButton[1]);
        keyJButton[2] = new JButton("End");
        keyJPanel.add(keyJButton[2]);

        for (int i = 3; i < keyJButton.length-3; i++) {
            keyJButton[i - 1] = new JButton(String.valueOf(i-2));
            keyJPanel.add(keyJButton[i - 1]);
        }
        keyJButton[12] = new JButton("*");
        keyJPanel.add(keyJButton[12]);

        keyJButton[13] = new JButton("0");
        keyJPanel.add(keyJButton[13]);

        keyJButton[14] = new JButton("#");
        keyJPanel.add(keyJButton[14]);

        add(keyJPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    }

    폰 기능 추가

    import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPhone extends JFrame{
    private JPanel lcdJPanel;
    private JTextArea lcdJTextArea;
    private String lcdOutput = "";
    private JPanel keyJPanel;
    private JButton keyJButton[];

    public MyPhone() {
        setTitle("MyPhone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        lcdJPanel = new JPanel();
        lcdJPanel.setLayout(new BorderLayout());
        lcdJTextArea = new JTextArea(10, 15);
        lcdJTextArea.setEditable(false);
        lcdJPanel.add(lcdJTextArea, BorderLayout.CENTER);
        add(lcdJPanel, BorderLayout.NORTH);


        keyJPanel = new JPanel();
        keyJPanel.setLayout(new GridLayout(5, 3));
        keyJButton = new JButton[15];

        keyJButton[0] = new JButton("Send");
        keyJButton[0].addActionListener(new Keylisten2());
        keyJPanel.add(keyJButton[0]);
        keyJButton[1] = new JButton("clr");
        keyJButton[1].addActionListener(new Keylisten2());
        keyJPanel.add(keyJButton[1]);
        keyJButton[2] = new JButton("End");
        keyJButton[2].addActionListener(new Keylisten2());
        keyJPanel.add(keyJButton[2]);

        for (int i = 3; i < keyJButton.length-3; i++) {
            keyJButton[i - 1] = new JButton(String.valueOf(i-2));
            keyJButton[i - 1].addActionListener(new Keylisten1());
            keyJPanel.add(keyJButton[i - 1]);
        }
        keyJButton[12] = new JButton("*");
        keyJButton[12].addActionListener(new Keylisten1());
        keyJPanel.add(keyJButton[12]);

        keyJButton[13] = new JButton("0");
        keyJButton[13].addActionListener(new Keylisten1());
        keyJPanel.add(keyJButton[13]);

        keyJButton[14] = new JButton("#");
        keyJButton[14].addActionListener(new Keylisten1());
        keyJPanel.add(keyJButton[14]);

        add(keyJPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    class Keylisten1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String btnText = btn.getText();
            lcdOutput += btnText;
            lcdJTextArea.setText(lcdOutput);
        }
    }

    class Keylisten2 implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("Send")){
                lcdOutput+="\n전화 거는 중...";
                lcdJTextArea.setText(lcdOutput);
            }
            else if (btn.getText().equals("clr")){
                if (lcdOutput.length()>0){
                    lcdOutput = lcdOutput.substring(0, lcdOutput.length()-1);
                    lcdJTextArea.setText(lcdOutput);
                }
            }
            else if (btn.getText().equals("End")){
                lcdOutput = "";
                lcdJTextArea.setText(lcdOutput);
            }
        }
    }
}

------------------------------------------------------