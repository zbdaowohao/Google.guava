package guava;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

public class CollectionUtils {

	public static void main(String[] args) {

		/** 可变Map */
		Map<Integer, String> map = Maps.newHashMap();
		map.put(1, "oNe");
		map.put(2, "Two");
		map.forEach((k, v) -> {
			System.out.println("key : " + k + "; value : " + v.toLowerCase());
		});// key : 1; value : one||key : 2; value : two

		Multiset<String> multiset = HashMultiset.create();
		multiset.add("a", 3);
		multiset.add("b", 5);
		multiset.add("c", 1);
		System.out.println(multiset);// [a x 3, b x 5, c]
		ImmutableMultiset<String> highestCountFirst = Multisets.copyHighestCountFirst(multiset);
		System.out.println(highestCountFirst);// [b x 5, a x 3, c]

		/** 可变List */
		List<Integer> list = Lists.newArrayList(1, 2);
		list.add(3);
		List<Integer> newList = list.stream().map((item) -> {
			return item + 1;
		}).collect(Collectors.toList());
		newList.forEach(System.out::println);// {2, 3, 4}
		List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
		List<Integer> countDown = Lists.reverse(countUp);// 返回给定List的反转视图
		List<List<Integer>> parts = Lists.partition(countUp, 2);// 把List按指定大小分割
		System.out.println(countUp);// [1, 2, 3, 4, 5]
		System.out.println(countDown); // [5, 4, 3, 2, 1]
		System.out.println(parts);// [[1, 2], [3, 4], [5]]

		Set<Integer> set = Sets.newHashSet(1, 2);
		set.add(2);
		set.add(3);
		set.forEach(System.out::println);// {1, 2, 3}
		Set<Integer> set1 = Sets.newHashSet(2, 3, 4);
		System.out.println(Sets.intersection(set, set1));// [2, 3] 取交集
		System.out.println(Sets.union(set, set1));// [1, 2, 3, 4] 取并集
		System.out.println(Sets.difference(set, set1));// [1] 返回Parma1中有但是parm2中没有的
		System.out.println(Sets.symmetricDifference(set, set1));// [1, 4] 返回不同的

		/** 定长List */
		ImmutableList<String> immutaleList = ImmutableList.of("1", "2", "3");
		// finalList.add("4"); 定长被final修饰此处会报错
		/** Use builder pattern */
		ImmutableList<String> immutaleList2 = new ImmutableList.Builder<String>().add("4").add("5").add("6").build();
		/** 定长有序不重复 */
		ImmutableSortedSet<String> immutableSet = ImmutableSortedSet.of("1", "2", "3", "3", "4", "5");
		System.out.println(immutaleList);// [1, 2, 3]
		System.out.println(immutaleList2);// [4, 5, 6]
		System.out.println(immutableSet);// [1, 2, 3, 4, 5]

		/** 定长Map */
		ImmutableMap<Integer, String> immutaleMap = ImmutableMap.of(1, "day", 2, "night");
		ImmutableMap<Integer, String> immutaleMap2 = new ImmutableMap.Builder<Integer, String>().put(3, "day")
				.put(4, "night").build();
		System.out.println(immutaleMap);// {1=day, 2=night}
		System.out.println(immutaleMap2);// {3=day, 4=night}

		/**
		 * 翻转映射
		 */
		/** V是个List */
		ArrayListMultimap<String, Integer> arrayMultimap = ArrayListMultimap.create();
		arrayMultimap.putAll("b", Ints.asList(2, 4, 6));
		arrayMultimap.putAll("a", Ints.asList(4, 2, 1));
		arrayMultimap.putAll("c", Ints.asList(2, 5, 3));
		System.out.println(arrayMultimap);// {a=[4, 2, 1], b=[2, 4, 6], c=[2, 5, 3]}
		//TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(arrayMultimap, TreeMultimap<String, Integer>.create());
		/** V是一个值 */
		SetMultimap<String, Integer> multimap = Multimaps.forMap(ImmutableMap.of("a", 1, "b", 1, "c", 2));
		System.out.println(multimap);// {a=[1], b=[1], c=[2]}
		//Multimap<Integer, String> inverse2 = Multimaps.invertFrom(multimap, HashMultimap<Integer, String>.create());
		// inverse：[1 => {"a","b"}, 2 => {"c"}]

		/**
		 * com.google.common.primitices包下的
		 * Ints,Doubles,Floats,Shorts,Bytes以及Bools等工具类操作基本类型的数据
		 */
		System.out.println(Ints.compare(1, 5));// -1
		int[] array = { 1, 2, 3 };
		int[] array1 = { 3, 4, 5 };
		System.out.println(Ints.contains(array, 6));// false
		System.out.println(Ints.indexOf(array, 5));// -1
		System.out.println(Ints.max(array));// 3
		System.out.println(Ints.min(array));// 1
		System.out.println(Ints.asList(Ints.concat(array, array1)));// [1, 2, 3, 3, 4, 5]

	}
}
