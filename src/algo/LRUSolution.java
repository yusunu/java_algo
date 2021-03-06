package algo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

/**
 * 変形LRU作成
 *
 * @author c0040268
 */
public class LRUSolution {

	public static void main(String[] args) throws java.lang.Exception {
		var lc = new LRU();
		try (var br = new Scanner(System.in)) {
			// add|get|evict|remove|exit[ key[ value]]
			String cmd = br.nextLine();
			for (; !Optional.ofNullable(cmd).orElse("exit").equals("exit"); cmd = br.nextLine()) {
				lc.exeCommand(cmd);
			}
		}

		// Sysoutすれば、結果をCode Platformで判断
	}

	static class LRU {

		private Map<Integer, Integer> map = new LinkedHashMap<>();

		public void exeCommand(String cmd) {
			String[] splitStr = cmd.split(" ");
			switch (splitStr[0]) {
			case "add":
				this.add(Integer.valueOf(splitStr[1]), Integer.valueOf(splitStr[2]));
				break;
			case "get":
				System.out.println(this.get(Integer.valueOf(splitStr[1])));
				break;
			case "evict":
				this.evict();
				break;
			case "remove":
				System.out.println(this.remove(Integer.valueOf(splitStr[1])));
				break;
			default:
				break;
			}
		}

		public void add(Integer key, Integer value) {
			this.map.put(key, value);
		}

		public Integer get(Integer key) {

			Integer value = null;
			try {
				value = this.map.get(key);
				if (value != null) {
					this.map.remove(key);
					this.map.put(key, value);
				}
			} catch (Exception e) {
				// do nothing
			}
			return value != null ? value : -1;
		}

		public Integer remove(Integer key) {
			Integer value = this.map.remove(key);
			return value != null ? value : -1;
		}

		public void evict() {
			Set<Integer> keys = this.map.keySet();
			if (keys.size() > 0) {
				Integer key = keys.iterator().next();
				this.map.remove(key);
			}
		}
	}
}
