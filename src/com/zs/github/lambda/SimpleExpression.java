package com.zs.github.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.*;

/**
 * @author zhangsai
 *      1.不需要参数,返回值为 5  
		() -> 5;
		  
		2. 接收一个参数(数字类型),返回其2倍的值  
		x -> 2 * x;
		  
		3. 接受2个参数(数字),并返回他们的差值  
		(x, y) -> x – y  
		  
		4. 接收2个int型整数,返回他们的和  
		(int x, int y) -> x + y  
		  
		5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
		(String s) -> System.out.print(s);
 */
public class SimpleExpression {

	public static void main(String[] args) {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
			       "Stanislas Wawrinka",  
			       "David Ferrer","Roger Federer",  
			       "Andy Murray","Tomas Berdych",  
			       "Juan Martin Del Potro"};  
			List<String> players =  Arrays.asList(atp);  
			// 使用 lambda 表达式以及函数操作(functional operation)  
			//players.forEach((player) -> System.out.print(player + "; "));  
			   
			// 在 Java 8 中使用双冒号操作符(double colon operator)  
			//players.forEach(System.out::println);
			/*players.stream()
			       .filter((p) -> (p.contains("R")))
			       .limit(2)
			       .sorted((p,p2)-> (p.compareTo(p2)))//从小到大
			       .forEach((p) -> System.out.printf("%s;", p));*/
			
			//statistics();
			collectTest();
	}

	private static void statistics() {
		//计算 count, min, max, sum, and average for numbers  
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);  
		IntSummaryStatistics stats = numbers  
		          .stream()  
		          .mapToInt((x) -> x)  
		          .summaryStatistics();  
		  
		System.out.println("List中最大的数字 : " + stats.getMax());  
		System.out.println("List中最小的数字 : " + stats.getMin());  
		System.out.println("所有数字的总和   : " + stats.getSum());  
		System.out.println("所有数字的平均值 : " + stats.getAverage()); 
	}
	
	private static void collectTest(){
		List<Person> javaProgrammers = new ArrayList<Person>() {  
			  {  
			    add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));  
			    add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));  
			    add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));  
			    add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));  
			    add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));  
			    add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));  
			    add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));  
			    add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));  
			    add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));  
			    add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));  
			  }  
			}; 
			
			List<Person> sortedJavaProgrammers = javaProgrammers  
			          .stream()  
			          .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))  
			          .limit(5)
			          .collect(Collectors.toList());
			
			sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));  
	
	
			System.out.println("工资最高的 Java programmer:");  
			Person person = javaProgrammers  
			          .stream()  
			          .max((p, p2) -> (p.getSalary() - p2.getSalary()))  
			          .get(); 
			  
			System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary()); 
	
			
			System.out.println("将 PHP programmers 的 first name 拼接成字符串:");  
			String phpDevelopers = javaProgrammers  
			          .stream()  
			          .map(Person::getFirstName)  
			          .collect(Collectors.joining(" ; ")); // 在进一步的操作中可以作为标记(token)     
			
			System.out.println(phpDevelopers);
			
			System.out.println("将 Java programmers 的 first name 存放到 Set:");  
			Set<String> javaDevFirstName = javaProgrammers  
			          .stream()  
			          .map(Person::getFirstName)  
			          .collect(Collectors.toSet());  
			javaDevFirstName.forEach((p)->System.out.printf("%s %n",p));
			  
			System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");  
			TreeSet<String> javaDevLastName = javaProgrammers  
			          .stream()  
			          .map(Person::getLastName)  
			          .collect(Collectors.toCollection(TreeSet::new)); 
			javaDevLastName.forEach((p)->System.out.printf("%s %n",p));
	}

	private static void ComparatorTest(String[] players){
		// 1.1 使用匿名内部类根据 name 排序 players  
		Arrays.sort(players, new Comparator<String>() {  
		    @Override  
		    public int compare(String s1, String s2) {  
		        return (s1.compareTo(s2)); //()
		    }  
		}); 
		
		//使用 lambda expression 排序 players
		Comparator<String> sortByNameLenght = (String s1, String s2) -> (s1.compareTo(s2));  
		Arrays.sort(players, sortByNameLenght);
		
		// 也可以采用如下形式:  
		Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2))); 
	}

	private static void thread(){
		// 1.1使用匿名内部类  
		new Thread(new Runnable() {  
		    @Override  
		    public void run() {  
		        System.out.println("Hello world !");  
		    }  
		}).start();  
		  
		// 1.2使用 lambda expression  
		new Thread(() -> System.out.println("Hello world !")).start();  
		  
		// 2.1使用匿名内部类  
		Runnable race1 = new Runnable() {  
		    @Override  
		    public void run() {  
		        System.out.println("Hello world !");  
		    }  
		};  
		  
		// 2.2使用 lambda expression  
		Runnable race2 = () -> System.out.println("Hello world !");  
		   
		// 直接调用 run 方法(没开新线程哦!)  
		race1.run();  
		race2.run(); 
	}
	
}
