package guava;

import java.util.Arrays;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class StringUtils {

	public static void main(String[] args) {

		String numbers = Joiner.on(";").join("One", "Two", "Three", "Four");
		System.out.println(String.join(";", Arrays.asList("One", "Two", "Three", "Four")));// One;Two;Three;Four
		System.out.println(numbers);// One;Two;Three;Four

		/** 使用skipNulls(),可以忽略null值。避免parts中有null值时，抛出NPE异常。 */
		String numbers1 = Joiner.on(";").skipNulls().join("One", "Two", "Three", null, "Five");
		System.out.println(numbers1);// One;Two;Three;Five

		/** 使用useForNull(),可以将part中的null值替换为指定字符。 */
		String numbers2 = Joiner.on(";").useForNull("NULL").join("One", "Two", "Three", null, "Five");
		System.out.println(numbers2);// One;Two;Three;NULL;Five

		/** omitEmptyStrings去空,trimResults去除前后空格 */
		Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split("One 1,Two ,  ,,Three,Four");
		String numbers3 = Joiner.on(";").join(split);
		System.out.println(numbers3);// One;Two;Three;Four
		
	}
}
