package assignment1;

import java.util.Scanner;

//--------------------------------------------
// 計算機プログラムを作成
//--------------------------------------------
/* 目的：
 * コンソール（標準入力）から２つの数値と演算子を入力させ、結果を表示する。
 * （if文や演算処理の基本を学ぶ）
 * */

public class Calculator {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("数値１を入力してください。：");
		double num1 = scanner.nextDouble();
		
		System.out.print("数値２を入力してください。：");
		double num2 = scanner.nextDouble();
		
		System.out.print("演算子を入力してください。（+, -, *, /）：");
		String operator = scanner.next();
		
		double result = 0;
		boolean validOperator = true;
		
		if (operator.equals("+")) {
			result = num1 + num2;
		} else if (operator.equals("-")) {
			result = num1 - num2;
		} else if (operator.equals("*")) {
			result  = num1 * num2;
		} else if (operator.equals("/")) {
			if (num2 == 0) {
				System.out.println("エラー： 0で悪ことはできません。");
				validOperator = false;
			} else {
				result = num1 / num2;
			}
		} else {
			System.out.println("エラー： 無効な演算子です。");
			validOperator = false;
		}
		
		if (validOperator) {
			System.out.println("計算結果：" + result);
		}
		
		scanner.close();
	}
}
