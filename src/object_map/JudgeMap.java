package object_map;

import java.util.concurrent.ConcurrentHashMap;

import abstract_class.Judge;
import system.NormalJudge;


public class JudgeMap extends ConcurrentHashMap<String, Judge>{
	public Judge get(final Object key) {
		Judge ret = super.get(key);
		if (ret == null) {
			ret = new NormalJudge();
			this.put((String) key, ret);
		}
		return ret;
	}
}
